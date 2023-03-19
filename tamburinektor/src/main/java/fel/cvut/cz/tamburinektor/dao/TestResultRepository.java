package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.test.Test;
import fel.cvut.cz.tamburinektor.model.test.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    List<TestResult> getAllByTest(Test test);
}
