package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.lecture.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GraphRepository extends JpaRepository<Graph, Integer> {

}
