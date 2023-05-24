package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.AssignmentDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class AssignmentMapperTest {

    private AssignmentMapper assignmentMapperUnderTest;


    @BeforeEach
    void setUp() {
        assignmentMapperUnderTest = new AssignmentMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        final User createBy = new User();
        assignment.setCreateBy(createBy);

        // Run the test
        final AssignmentDto result = assignmentMapperUnderTest.toDto(assignment);

        // Verify the results
    }


    @Test
    void testToAssignment() {
        // Setup
        final AssignmentDto dto = new AssignmentDto();
        dto.setId(0L);
        dto.setQuestion("question");
        dto.setImageLink("imageLink");
        dto.setOpenQuestion(false);
        dto.setRightAnswers(List.of("value"));
        dto.setWrongAnswers(List.of("value"));

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Assignment result = assignmentMapperUnderTest.toAssignment(dto, user);

        // Verify the results
    }
}
