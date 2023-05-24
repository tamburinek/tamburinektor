package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class QuizQuestionMapperTest {

    private QuizQuestionMapper quizQuestionMapperUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        quizQuestionMapperUnderTest = new QuizQuestionMapper();
    }


    @Test
    void testToDto() throws Exception {
        // Setup
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quizQuestion.setRightAnswer("right");
        quizQuestion.setWrongAnswers(List.of("value"));

        // Run the test
        final QuizQuestionDto result = quizQuestionMapperUnderTest.toDto(quizQuestion);

        // Verify the results
    }


    @Test
    void testToQuestion() {
        // Setup
        final QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setQuestion("question");
        quizQuestionDto.setRight("right");
        quizQuestionDto.setWrong1("wrong1");
        quizQuestionDto.setWrong2("wrong2");
        quizQuestionDto.setWrong3("wrong3");

        // Run the test
        final QuizQuestion result = quizQuestionMapperUnderTest.toQuestion(quizQuestionDto);

        // Verify the results
    }
}
