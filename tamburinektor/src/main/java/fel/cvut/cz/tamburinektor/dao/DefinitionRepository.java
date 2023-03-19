package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Integer> {

    List<Definition> getAllByCreatedBy(User user);

    Definition getDefinitionById(Long id);

}
