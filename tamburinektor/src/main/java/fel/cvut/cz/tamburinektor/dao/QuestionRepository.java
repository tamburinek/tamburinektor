package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.lecture.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
