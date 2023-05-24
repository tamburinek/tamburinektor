package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.TestRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    public void createOrUpdateTest(Test test) {
        testRepository.save(test);
    }


    public List<Test> getAllByUser(User user) {
        return testRepository.getAllByCreateBy(user);
    }


    public Test getById(Long id) {
        return testRepository.getById(id);
    }


    public Test getLastByUser(User user) {
        List<Test> entities = testRepository.getAllByCreateBy(user);
        if (entities.isEmpty()){
            return null;
        }
        Test last = entities.get(0);
        for (Test entity : entities) {
            if (entity.getId() > last.getId()){
                last = entity;
            }
        }
        return last;
    }
}
