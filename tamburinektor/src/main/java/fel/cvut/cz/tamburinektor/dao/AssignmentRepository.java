package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    List<Assignment> getAllByCreateByAndAndOpenQuestion(User user, boolean isOpen);
}
