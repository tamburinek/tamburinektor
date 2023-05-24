package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuizQuestionRepository;
import fel.cvut.cz.tamburinektor.dao.QuizRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
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
class QuizServiceTest {

    @Mock
    private QuizRepository mockQuizRepository;
    @Mock
    private QuizQuestionRepository mockQuizQuestionRepository;

    private QuizService quizServiceUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        quizServiceUnderTest = new QuizService(mockQuizRepository, mockQuizQuestionRepository);
    }


    @Test
    void testCreateQuiz() {
        // Setup
        final Quiz quiz = new Quiz();
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quizQuestion.setRightAnswer("rightAnswer");
        quiz.setQuestions(List.of(quizQuestion));

        // Run the test
        quizServiceUnderTest.createQuiz(quiz);

        // Verify the results
        // Confirm QuizQuestionRepository.saveAll(...).
        final QuizQuestion quizQuestion1 = new QuizQuestion();
        quizQuestion1.setId(0L);
        quizQuestion1.setQuestion("question");
        quizQuestion1.setRightAnswer("rightAnswer");
        quizQuestion1.setWrongAnswers(List.of("value"));
        final List<QuizQuestion> entities = List.of(quizQuestion1);
        verify(mockQuizQuestionRepository).saveAll(entities);
        verify(mockQuizRepository).save(any(Quiz.class));
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

        // Configure QuizRepository.getAllByCreatedBy(...).
        final Quiz quiz = new Quiz();
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quizQuestion.setRightAnswer("rightAnswer");
        quiz.setQuestions(List.of(quizQuestion));
        final List<Quiz> quizzes = List.of(quiz);
        when(mockQuizRepository.getAllByCreatedBy(any(User.class))).thenReturn(quizzes);

        // Run the test
        final List<Quiz> result = quizServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_QuizRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockQuizRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Quiz> result = quizServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure QuizRepository.getQuizById(...).
        final Quiz quiz = new Quiz();
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quizQuestion.setRightAnswer("rightAnswer");
        quiz.setQuestions(List.of(quizQuestion));
        when(mockQuizRepository.getQuizById(0L)).thenReturn(quiz);

        // Run the test
        final Quiz result = quizServiceUnderTest.getById(0L);

        // Verify the results
    }
}
