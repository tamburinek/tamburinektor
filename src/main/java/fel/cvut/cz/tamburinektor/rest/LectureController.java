package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.LectureActiveDto;
import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.mappers.LectureEntityMapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class LectureController {

    private final LectureService lectureService;

    private final UserService userService;

    private final LectureMapper mapper;

    private final LectureEntityMapper entityMapper;

    private final LectureEntityService lectureEntityService;

    @Autowired
    public LectureController(LectureService lectureService, UserService userService, LectureMapper mapper, LectureEntityMapper entityMapper, LectureEntityService lectureEntityService) {
        this.lectureService = lectureService;
        this.userService = userService;
        this.mapper = mapper;
        this.entityMapper = entityMapper;
        this.lectureEntityService = lectureEntityService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/lecture", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLecture(@RequestBody LectureDto lectureDto){
        User user = userService.getCurrentUser();
        List<LectureEntity> entities = getAllEntitiesFromDto(lectureDto.getLectureEntities());
        Lecture lecture = mapper.toLecture(lectureDto, user, entities);
        lectureService.createOrUpdateLecture(lecture);
        log.info("New lecture created {} by {}", lecture.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/lecture/{id}", lecture.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/lecture", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LectureDto> getAllLecturesByMe(){
        User user = userService.getCurrentUser();
        List<LectureDto> result = new LinkedList<>();

        List<Lecture> lectures = lectureService.getAllLecturesByUser(user);
        for (Lecture lecture : lectures) {
            List<LectureEntityDto> entityDtos = getAllDtosFromPojo(lecture.getLectureEntities());
            result.add(mapper.toDto(lecture, entityDtos));
        }
        return result;
    }

    @GetMapping(value = "/lecture/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LectureDto getLectureById(@PathVariable Long id){
        Lecture lecture = lectureService.getById(id);
        List<LectureEntityDto> dtos = lecture.getLectureEntities().stream().map(entityMapper::toDto).collect(Collectors.toList());
        return mapper.toDto(lecture, dtos);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/lecture/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLecture(@PathVariable Long id, @RequestBody LectureDto lectureDto){
        List<LectureEntity> entities = getAllEntitiesFromDto(lectureDto.getLectureEntities());
        Lecture lecture = lectureService.getById(id);
        lecture.setDescription(lectureDto.getDescription());
        lecture.setLectureEntities(entities);
        lectureService.createOrUpdateLecture(lecture);
        log.info("Lecture updated {}", lecture.getDescription());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/lecture/{id}", lecture.getId());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/lecture/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLastLectureName(){
        User user = userService.getCurrentUser();
        Lecture lecture = lectureService.getLast(user);
        if (lecture == null){
            return null;
        }
        return lecture.getDescription();
    }

    @GetMapping(value = "/lecture/{id}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getLectureIsActive(@PathVariable Long id){
        Lecture lecture = lectureService.getById(id);
        return lecture.isOpen();
    }

    @PostMapping(value = "/lecture/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setActiveForLecture(@PathVariable Long id, @RequestBody LectureActiveDto dto){
        Lecture lecture = lectureService.getById(id);
        lecture.setOpen(dto.isActive());
        lectureService.createOrUpdateLecture(lecture);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/lecture/{id}/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getActiveEntityIndex(@PathVariable Long id){
        Lecture lecture = lectureService.getById(id);
        if (!lecture.isOpen()){
            return 0L;
        }
        return lecture.getLastEntity().getId();
    }

    private List<LectureEntity> getAllEntitiesFromDto(List<LectureEntityDto> dtos){
        List<LectureEntity> entities = new LinkedList<>();
        dtos.forEach(dto -> entities.add(lectureEntityService.getEntityById(dto.getId())));
        return entities;
    }

    private List<LectureEntityDto> getAllDtosFromPojo(List<LectureEntity> entities){
        List<LectureEntityDto> result = new LinkedList<>();
        entities.forEach(lectureEntity -> result.add(entityMapper.toDto(lectureEntity)));
        return result;
    }
}
