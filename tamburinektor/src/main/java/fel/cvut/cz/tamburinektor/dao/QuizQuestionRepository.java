package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Integer> {

}
