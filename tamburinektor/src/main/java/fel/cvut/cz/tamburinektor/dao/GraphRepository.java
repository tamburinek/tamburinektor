package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GraphRepository extends JpaRepository<Graph, Integer> {

    List<Graph> getAllByCreatedBy(User user);

    Graph getGraphsById(Long id);

}
