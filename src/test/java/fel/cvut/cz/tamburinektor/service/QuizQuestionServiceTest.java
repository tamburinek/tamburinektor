package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuizQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class QuizQuestionServiceTest {

    @Mock
    private QuizQuestionRepository mockQuestionRepository;

    private QuizQuestionService quizQuestionServiceUnderTest;


    @BeforeEach
    void setUp() {
        quizQuestionServiceUnderTest = new QuizQuestionService(mockQuestionRepository);
    }
}
