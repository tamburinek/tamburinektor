package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizDto;
import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class QuizMapperTest {

    @Mock
    private QuizQuestionMapper mockQuizQuestionMapper;

    private QuizMapper quizMapperUnderTest;


    @BeforeEach
    void setUp() {
        quizMapperUnderTest = new QuizMapper(mockQuizQuestionMapper);
    }


    @Test
    void testToDto() {
        // Setup
        final Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        quiz.setCreatedBy(createdBy);
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quiz.setQuestions(List.of(quizQuestion));

        // Configure QuizQuestionMapper.toDto(...).
        final QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setQuestion("question");
        quizQuestionDto.setRight("right");
        quizQuestionDto.setWrong1("wrong1");
        quizQuestionDto.setWrong2("wrong2");
        quizQuestionDto.setWrong3("wrong3");
        when(mockQuizQuestionMapper.toDto(any(QuizQuestion.class))).thenReturn(quizQuestionDto);

        // Run the test
        final QuizDto result = quizMapperUnderTest.toDto(quiz);

        // Verify the results
    }


    @Test
    void testToQuiz() {
        // Setup
        final QuizDto quizDto = new QuizDto();
        quizDto.setId(0L);
        quizDto.setName("name");
        final QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setQuestion("question");
        quizDto.setQuestions(List.of(quizQuestionDto));
        quizDto.setLectureType("name");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure QuizQuestionMapper.toQuestion(...).
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quizQuestion.setRightAnswer("rightAnswer");
        quizQuestion.setWrongAnswers(List.of("value"));
        when(mockQuizQuestionMapper.toQuestion(any(QuizQuestionDto.class))).thenReturn(quizQuestion);

        // Run the test
        final Quiz result = quizMapperUnderTest.toQuiz(quizDto, user);

        // Verify the results
    }
}
