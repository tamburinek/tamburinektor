package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.TaskRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository mockTaskRepository;

    private TaskService taskServiceUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        taskServiceUnderTest = new TaskService(mockTaskRepository);
    }


    @Test
    void testCreateTask() {
        // Setup
        final Task task = new Task();
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");

        // Run the test
        taskServiceUnderTest.createTask(task);

        // Verify the results
        verify(mockTaskRepository).save(any(Task.class));
    }


    @Test
    void testGetAllByUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure TaskRepository.getAllByCreatedBy(...).
        final Task task = new Task();
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        final List<Task> tasks = List.of(task);
        when(mockTaskRepository.getAllByCreatedBy(any(User.class))).thenReturn(tasks);

        // Run the test
        final List<Task> result = taskServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_TaskRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockTaskRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Task> result = taskServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure TaskRepository.getById(...).
        final Task task = new Task();
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");
        when(mockTaskRepository.getById(0L)).thenReturn(task);

        // Run the test
        final Task result = taskServiceUnderTest.getById(0L);

        // Verify the results
    }
}
