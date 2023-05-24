package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuestionAnswerRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class QuestionAnswerServiceTest {

    @Mock
    private QuestionAnswerRepository mockQuestionAnswerRepository;

    private QuestionAnswerService questionAnswerServiceUnderTest;


    @BeforeEach
    void setUp() {
        questionAnswerServiceUnderTest = new QuestionAnswerService(mockQuestionAnswerRepository);
    }


    @Test
    void testCreateAnswer() {
        // Setup
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        answer.setMessage("message");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        answer.setCreatedBy(createdBy);

        // Run the test
        questionAnswerServiceUnderTest.createAnswer(answer);

        // Verify the results
        verify(mockQuestionAnswerRepository).save(any(QuestionAnswer.class));
    }
}
