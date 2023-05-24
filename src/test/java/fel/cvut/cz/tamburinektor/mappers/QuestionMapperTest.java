package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuestionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class QuestionMapperTest {

    private QuestionMapper questionMapperUnderTest;


    @BeforeEach
    void setUp() {
        questionMapperUnderTest = new QuestionMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Question question = new Question();
        question.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        question.setCreatedBy(createdBy);
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);

        // Run the test
        final QuestionDto result = questionMapperUnderTest.toDto(question);

        // Verify the results
    }


    @Test
    void testToQuestion() {
        // Setup
        final QuestionDto questionDto = new QuestionDto();
        questionDto.setId(0L);
        questionDto.setQuestionText("question");
        questionDto.setAnonymous(false);
        questionDto.setLectureType("name");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Question result = questionMapperUnderTest.toQuestion(questionDto, user);

        // Verify the results
    }
}
