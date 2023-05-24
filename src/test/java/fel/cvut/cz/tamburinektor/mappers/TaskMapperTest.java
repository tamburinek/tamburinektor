package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.TaskDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TaskMapperTest {

    private TaskMapper taskMapperUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        taskMapperUnderTest = new TaskMapper();
    }


    @Test
    void testToDto() throws Exception {
        // Setup
        final Task task = new Task();
        task.setId(0L);
        task.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        task.setCreatedBy(createdBy);
        task.setQuestion("question");
        task.setAnswer("answer");
        task.setQuestionImage("questionImage");
        task.setAnswerImage("answerImage");

        // Run the test
        final TaskDto result = taskMapperUnderTest.toDto(task);

        // Verify the results
    }


    @Test
    void testToTask() {
        // Setup
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setQuestion("question");
        taskDto.setAnswer("answer");
        taskDto.setQuestionImage("questionImage");
        taskDto.setAnswerImage("answerImage");
        taskDto.setLectureType("name");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Task result = taskMapperUnderTest.toTask(taskDto, user);

        // Verify the results
    }
}
