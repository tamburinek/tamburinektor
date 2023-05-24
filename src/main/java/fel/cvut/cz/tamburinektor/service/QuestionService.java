package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.QuestionRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void createQuestion(Question question){
        questionRepository.save(question);
    }

    public List<Question> getAllByUser(User user){
        return questionRepository.getAllByCreatedBy(user);
    }

    public Question getById(Long id){
        return questionRepository.getById(id);
    }
}
