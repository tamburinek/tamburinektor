package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

}
