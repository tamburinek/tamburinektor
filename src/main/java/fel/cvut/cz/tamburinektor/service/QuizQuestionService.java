package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuizQuestionService {

    private final QuizQuestionRepository questionRepository;

    @Autowired
    public QuizQuestionService(QuizQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
