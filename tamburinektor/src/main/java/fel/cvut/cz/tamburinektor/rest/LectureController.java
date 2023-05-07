package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.mappers.LectureMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import fel.cvut.cz.tamburinektor.service.LectureEntityService;
import fel.cvut.cz.tamburinektor.service.LectureService;
import fel.cvut.cz.tamburinektor.service.UserService;
import fel.cvut.cz.tamburinektor.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class LectureController {

    private final LectureService lectureService;

    private final UserService userService;

    private final LectureMapper mapper;

    private final LectureEntityService lectureEntityService;

    @Autowired
    public LectureController(LectureService lectureService, UserService userService, LectureMapper mapper, LectureEntityService lectureEntityService) {
        this.lectureService = lectureService;
        this.userService = userService;
        this.mapper = mapper;
        this.lectureEntityService = lectureEntityService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/lecture", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuiz(@RequestBody LectureDto lectureDto){
        User user = userService.getCurrentUser();
        List<LectureEntity> entities = getAllEntitiesFromDto(lectureDto.getLectureEntities());
        Lecture lecture = mapper.toLecture(lectureDto, user, entities);
        lectureService.createOrUpdateLecture(lecture);
        log.info("New lecture created {} by {}", lecture.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/lecture/{id}", lecture.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    private List<LectureEntity> getAllEntitiesFromDto(List<LectureEntityDto> dtos){
        List<LectureEntity> entities = new LinkedList<>();
        dtos.forEach(dto -> entities.add(lectureEntityService.getEntityById(dto.getId())));
        return entities;
    }
}
