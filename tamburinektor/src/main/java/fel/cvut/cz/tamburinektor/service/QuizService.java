package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.dao.QuizQuestionRepository;
import fel.cvut.cz.tamburinektor.dao.QuizRepository;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuizQuestionRepository quizQuestionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizQuestionRepository quizQuestionRepository) {
        this.quizRepository = quizRepository;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    public void createQuiz(Quiz quiz){
        quizQuestionRepository.saveAll(quiz.getQuestions());
        quizRepository.save(quiz);
    }
}
