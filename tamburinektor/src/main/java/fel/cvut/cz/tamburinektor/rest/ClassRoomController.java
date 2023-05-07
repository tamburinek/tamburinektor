package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ClassRoomDto;
import fel.cvut.cz.tamburinektor.mappers.ClassRoomMapper;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import fel.cvut.cz.tamburinektor.service.ClassRoomService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@Slf4j
@CrossOrigin
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    private final ClassRoomMapper mapper;

    private final UserService userService;

    @Autowired
    public ClassRoomController(ClassRoomService classRoomService, ClassRoomMapper mapper, UserService userService) {
        this.classRoomService = classRoomService;
        this.mapper = mapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/class", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClass(@RequestBody ClassRoomDto dto){
        User user = userService.getCurrentUser();
        Classroom classroom = mapper.toClassRoom(dto, user);
        classRoomService.createOrUpdateClass(classroom);
        log.info("New class created {} by {}", classroom.getName(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/class/{id}", classroom.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/class/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getClassCount(){
        User user = userService.getCurrentUser();
        return classRoomService.getCountByUser(user);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/class/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClassRoomDto getLastClass(){
        User user = userService.getCurrentUser();
        Classroom room = classRoomService.getLastClass(user);
        return room != null ? mapper.toDto(classRoomService.getLastClass(user)) : null;
    }
}
