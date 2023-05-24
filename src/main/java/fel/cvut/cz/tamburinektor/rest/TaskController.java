package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.TaskDto;
import fel.cvut.cz.tamburinektor.mappers.TaskMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import fel.cvut.cz.tamburinektor.service.TaskService;
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
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper, UserService userService) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        User user = userService.getCurrentUser();
        Task task = taskMapper.toTask(taskDto, user);
        taskService.createTask(task);
        log.info("New task created {} by {}", task.getQuestion(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/task/{id}", task.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getAllTasksByUser(){
        User user = userService.getCurrentUser();
        List<Task> tasks = taskService.getAllByUser(user);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }


    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTaskById(@PathVariable Long id){
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody TaskDto dto){
        Task task = taskService.getById(id);
        task.setQuestion(dto.getQuestion());
        task.setAnswer(dto.getAnswer());
        task.setQuestionImage(dto.getQuestionImage());
        task.setAnswerImage(dto.getAnswerImage());
        taskService.createTask(task);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
