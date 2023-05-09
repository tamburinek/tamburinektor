package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.AssignmentDto;
import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.mappers.AssignmentMapper;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import fel.cvut.cz.tamburinektor.service.AssignmentService;
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

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class AssignmentController {

    private final AssignmentService assignmentService;

    private final AssignmentMapper mapper;

    private final UserService userService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService, AssignmentMapper mapper, UserService userService) {
        this.assignmentService = assignmentService;
        this.mapper = mapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/assignment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDto dto){
        User user = userService.getCurrentUser();
        Assignment assignment = mapper.toAssignment(dto, user);
        assignmentService.createOrUploadAssignment(assignment);
        log.info("New assignment created {} by {}", assignment.getQuestion(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/assignment/{id}", assignment.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/assignment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentDto dto){
        Assignment assignment = assignmentService.getById(dto.getId());
        assignment.setQuestion(dto.getQuestion());
        assignment.setImageLink(dto.getImageLink());
        assignment.setRightAnswers(dto.getRightAnswers());
        assignment.setWrongAnswers(dto.getWrongAnswers());
        assignment.setOpenQuestion(dto.isOpenQuestion());
        assignmentService.createOrUploadAssignment(assignment);
        log.info("Assignment updated {}", assignment.getQuestion());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/assignment/{id}", assignment.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/assignment/close", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssignmentDto> getAllAssignmentByMeClose(){
        User user = userService.getCurrentUser();
        List<Assignment> assignments = assignmentService.getAllByUserClosed(user);
        return assignments.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/assignment/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssignmentDto> getAllAssignmentByMeOpen(){
        User user = userService.getCurrentUser();
        List<Assignment> assignments = assignmentService.getAllByUserOpen(user);
        return assignments.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/assignment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentDto getAssignmentById(@PathVariable Long id){
        Assignment assignment = assignmentService.getById(id);
        return mapper.toDto(assignment);
    }
}
