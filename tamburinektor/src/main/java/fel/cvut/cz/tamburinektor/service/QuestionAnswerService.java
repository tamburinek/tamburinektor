package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuestionAnswerRepository;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
    }


    public void createAnswer(QuestionAnswer answer) {
        questionAnswerRepository.save(answer);
    }
}
