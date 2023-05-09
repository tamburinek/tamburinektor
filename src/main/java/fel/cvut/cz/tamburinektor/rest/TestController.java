package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.mappers.TestMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import fel.cvut.cz.tamburinektor.model.test.Test;
import fel.cvut.cz.tamburinektor.service.AssignmentService;
import fel.cvut.cz.tamburinektor.service.TestService;
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
public class TestController {

    private final TestService testService;

    private final UserService userService;

    private final TestMapper mapper;

    private final AssignmentService assignmentService;

    @Autowired
    public TestController(TestService testService, UserService userService, TestMapper mapper, AssignmentService assignmentService) {
        this.testService = testService;
        this.userService = userService;
        this.mapper = mapper;
        this.assignmentService = assignmentService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTest(@RequestBody TestDto dto){
        User user = userService.getCurrentUser();
        List<Assignment> assignments = getAllEntitiesFromDto(dto.getAssignments());
        Test test = mapper.toTest(dto, user, assignments);
        testService.createOrUpdateTest(test);
        log.info("New test created {} by {}", test.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/test/{id}", test.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/test/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTest(@PathVariable Long id, @RequestBody TestDto dto){
        Test test = testService.getById(id);
        test.setDescription(dto.getDescription());
        List<Assignment> assignments = getAllEntitiesFromDto(dto.getAssignments());
        test.setAssignments(assignments);
        testService.createOrUpdateTest(test);
        log.info("Test updated {}", test.getDescription());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/test/{id}", test.getId());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestDto> getAllMyTests(){
        User user = userService.getCurrentUser();
        List<Test> tests = testService.getAllByUser(user);
        return tests.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/test/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TestDto getTestById(@PathVariable Long id){
        Test test = testService.getById(id);
        return mapper.toDto(test);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/test/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public TestDto getLastTestByMe(){
        User user = userService.getCurrentUser();
        Test test = testService.getLastByUser(user);
        if (test == null){
            return null;
        }
        return mapper.toDto(test);
    }

    private List<Assignment> getAllEntitiesFromDto(List<Long> ids){
        List<Assignment> helper = new LinkedList<>();
        ids.forEach(id -> helper.add(assignmentService.getById(id)));
        return helper;
    }
}
