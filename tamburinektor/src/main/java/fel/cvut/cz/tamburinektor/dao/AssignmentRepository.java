package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

}
