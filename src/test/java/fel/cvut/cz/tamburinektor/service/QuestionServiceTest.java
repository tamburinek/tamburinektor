package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuestionRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
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
class QuestionServiceTest {

    @Mock
    private QuestionRepository mockQuestionRepository;

    private QuestionService questionServiceUnderTest;


    @BeforeEach
    void setUp() {
        questionServiceUnderTest = new QuestionService(mockQuestionRepository);
    }


    @Test
    void testCreateQuestion() {
        // Setup
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));

        // Run the test
        questionServiceUnderTest.createQuestion(question);

        // Verify the results
        verify(mockQuestionRepository).save(any(Question.class));
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

        // Configure QuestionRepository.getAllByCreatedBy(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        final List<Question> questions = List.of(question);
        when(mockQuestionRepository.getAllByCreatedBy(any(User.class))).thenReturn(questions);

        // Run the test
        final List<Question> result = questionServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_QuestionRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockQuestionRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Question> result = questionServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure QuestionRepository.getById(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        when(mockQuestionRepository.getById(0L)).thenReturn(question);

        // Run the test
        final Question result = questionServiceUnderTest.getById(0L);

        // Verify the results
    }
}
