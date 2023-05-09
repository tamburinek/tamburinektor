package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {

}
