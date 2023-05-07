package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ClassRoomDto;
import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.DTO.UsernameDto;
import fel.cvut.cz.tamburinektor.mappers.ClassRoomMapper;
import fel.cvut.cz.tamburinektor.mappers.LectureMapper;
import fel.cvut.cz.tamburinektor.mappers.UsernameMapper;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.service.ClassRoomService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping
@Slf4j
@CrossOrigin
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    private final ClassRoomMapper mapper;

    private final UserService userService;

    private final UsernameMapper usernameMapper;

    private final LectureService lectureService;

    private final LectureMapper lectureMapper;

    @Autowired
    public ClassRoomController(ClassRoomService classRoomService, ClassRoomMapper mapper, UserService userService, UsernameMapper usernameMapper, LectureService lectureService, LectureMapper lectureMapper) {
        this.classRoomService = classRoomService;
        this.mapper = mapper;
        this.userService = userService;
        this.usernameMapper = usernameMapper;
        this.lectureService = lectureService;
        this.lectureMapper = lectureMapper;
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
    @GetMapping(value = "/class", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClassRoomDto> getClassesByUser(){
        User user = userService.getCurrentUser();
        List<Classroom> classes = classRoomService.getClassesCreatedBy(user);
        return classes.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/class/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClassRoomDto getLastClass(){
        User user = userService.getCurrentUser();
        Classroom room = classRoomService.getLastClass(user);
        return room != null ? mapper.toDto(classRoomService.getLastClass(user)) : null;
    }


    @GetMapping(value = "/class/{id}/lecture", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LectureDto> getLecturesFromClass(@PathVariable Long id){
        Classroom classroom = classRoomService.getClassById(id);
        return classroom.getLectures().stream()
                .map(lecture -> lectureMapper.toDto(lecture, null)).collect(Collectors.toList());
    }

    //todo
    @GetMapping(value = "/class/{id}/tests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestDto> getTestsFromClass(@PathVariable Long id){
        return null;
    }


    @GetMapping(value = "/class/{id}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsernameDto> getUsersByClassId(@PathVariable Long id){
        Classroom classroom = classRoomService.getClassById(id);
        return classroom.getUsers().stream()
                .map((user -> usernameMapper.toDto(user.getFirstName(), user.getLastName()))).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/class/{id}/lecture/{lectureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLectureToClass(@PathVariable Long id, @PathVariable Long lectureId){
        Classroom classroom = classRoomService.getClassById(id);
        Lecture lecture = lectureService.getById(lectureId);
        classRoomService.addLectureToClass(classroom, lecture);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @DeleteMapping(value = "/class/{id}/lecture/{lectureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLectureFromClass(@PathVariable Long id, @PathVariable Long lectureId){
        Classroom classroom = classRoomService.getClassById(id);
        Lecture lecture = lectureService.getById(lectureId);
        classRoomService.removeLectureFromClass(classroom, lecture);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
