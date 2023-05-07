package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.AssignmentRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    public void createOrUploadAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllByUserClosed(User user){
        return assignmentRepository.getAllByCreateByAndAndOpenQuestion(user, false);
    }

    public List<Assignment> getAllByUserOpen(User user){
        return assignmentRepository.getAllByCreateByAndAndOpenQuestion(user, true);
    }

    public Assignment getById(Long id){
        return assignmentRepository.getById(id);
    }
}
