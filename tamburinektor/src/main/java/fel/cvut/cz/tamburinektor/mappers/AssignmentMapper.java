package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.AssignmentDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.springframework.stereotype.Component;


@Component
public class AssignmentMapper {

    public AssignmentDto toDto(Assignment assignment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setQuestion(assignment.getQuestion());
        dto.setImageLink(assignment.getImageLink());
        dto.setOpenQuestion(assignment.isOpenQuestion());
        dto.setRightAnswers(assignment.getRightAnswers());
        dto.setWrongAnswers(assignment.getWrongAnswers());
        return dto;
    }

    public Assignment toAssignment(AssignmentDto dto, User user) {
        Assignment assignment = new Assignment();
        assignment.setQuestion(dto.getQuestion());
        assignment.setImageLink(dto.getImageLink());
        assignment.setOpenQuestion(dto.isOpenQuestion());
        assignment.setCreateBy(user);
        assignment.setRightAnswers(dto.getRightAnswers());
        assignment.setWrongAnswers(dto.getWrongAnswers());
        return assignment;
    }
}
