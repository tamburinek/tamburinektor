package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.TaskDto;
import fel.cvut.cz.tamburinektor.mappers.TaskMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import fel.cvut.cz.tamburinektor.service.TaskService;
import fel.cvut.cz.tamburinektor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService mockTaskService;
    @MockBean
    private TaskMapper mockTaskMapper;
    @MockBean
    private UserService mockUserService;


    @Test
    void testCreateTask() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure TaskMapper.toTask(...).
        final Task task = new Task();
        task.setId(0L);
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        when(mockTaskMapper.toTask(any(TaskDto.class), any(User.class))).thenReturn(task);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/task")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockTaskService).createTask(any(Task.class));
    }


    @Test
    void testGetAllTasksByUser() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure TaskService.getAllByUser(...).
        final Task task = new Task();
        task.setId(0L);
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        final List<Task> tasks = List.of(task);
        when(mockTaskService.getAllByUser(any(User.class))).thenReturn(tasks);

        // Configure TaskMapper.toDto(...).
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setQuestion("question");
        taskDto.setAnswer("answer");
        taskDto.setQuestionImage("questionImage");
        taskDto.setAnswerImage("answerImage");
        when(mockTaskMapper.toDto(any(Task.class))).thenReturn(taskDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/task")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllTasksByUser_TaskServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockTaskService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/task")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetTaskById() throws Exception {
        // Setup
        // Configure TaskService.getById(...).
        final Task task = new Task();
        task.setId(0L);
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        when(mockTaskService.getById(0L)).thenReturn(task);

        // Configure TaskMapper.toDto(...).
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setQuestion("question");
        taskDto.setAnswer("answer");
        taskDto.setQuestionImage("questionImage");
        taskDto.setAnswerImage("answerImage");
        when(mockTaskMapper.toDto(any(Task.class))).thenReturn(taskDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/task/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateQuestion() throws Exception {
        // Setup
        // Configure TaskService.getById(...).
        final Task task = new Task();
        task.setId(0L);
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        when(mockTaskService.getById(0L)).thenReturn(task);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/task/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockTaskService).createTask(any(Task.class));
    }
}
