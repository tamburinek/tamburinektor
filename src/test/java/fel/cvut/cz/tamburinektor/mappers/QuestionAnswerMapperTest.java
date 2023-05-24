package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class QuestionAnswerMapperTest {

    private QuestionAnswerMapper questionAnswerMapperUnderTest;


    @BeforeEach
    void setUp() {
        questionAnswerMapperUnderTest = new QuestionAnswerMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final QuestionAnswer testResult = new QuestionAnswer();
        testResult.setId(0L);
        testResult.setMessage("answer");
        final User createdBy = new User();
        createdBy.setFirstName("firstName");
        createdBy.setLastName("lastName");
        testResult.setCreatedBy(createdBy);

        // Run the test
        final QuestionAnswerDto result = questionAnswerMapperUnderTest.toDto(testResult);

        // Verify the results
    }


    @Test
    void testToQuestionAnswer() {
        // Setup
        final QuestionAnswerDto testResultDto = new QuestionAnswerDto();
        testResultDto.setId(0L);
        testResultDto.setQuestionId(0L);
        testResultDto.setAnswer("answer");
        testResultDto.setUser("user");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final QuestionAnswer result = questionAnswerMapperUnderTest.toQuestionAnswer(testResultDto, user);

        // Verify the results
    }
}
