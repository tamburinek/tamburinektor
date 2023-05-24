package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.AssignmentRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

    @Mock
    private AssignmentRepository mockAssignmentRepository;

    private AssignmentService assignmentServiceUnderTest;


    @BeforeEach
    void setUp() {
        assignmentServiceUnderTest = new AssignmentService(mockAssignmentRepository);
    }


    @Test
    void testCreateOrUploadAssignment() {
        // Setup
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));

        // Run the test
        assignmentServiceUnderTest.createOrUploadAssignment(assignment);

        // Verify the results
        verify(mockAssignmentRepository).save(any(Assignment.class));
    }


    @Test
    void testGetAllByUserClosed() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure AssignmentRepository.getAllByCreateByAndAndOpenQuestion(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        final List<Assignment> assignments = List.of(assignment);
        when(mockAssignmentRepository.getAllByCreateByAndAndOpenQuestion(any(User.class), eq(false)))
                .thenReturn(assignments);

        // Run the test
        final List<Assignment> result = assignmentServiceUnderTest.getAllByUserClosed(user);

        // Verify the results
    }


    @Test
    void testGetAllByUserClosed_AssignmentRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockAssignmentRepository.getAllByCreateByAndAndOpenQuestion(any(User.class), eq(false)))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Assignment> result = assignmentServiceUnderTest.getAllByUserClosed(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetAllByUserOpen() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure AssignmentRepository.getAllByCreateByAndAndOpenQuestion(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        final List<Assignment> assignments = List.of(assignment);
        when(mockAssignmentRepository.getAllByCreateByAndAndOpenQuestion(any(User.class), eq(true)))
                .thenReturn(assignments);

        // Run the test
        final List<Assignment> result = assignmentServiceUnderTest.getAllByUserOpen(user);

        // Verify the results
    }


    @Test
    void testGetAllByUserOpen_AssignmentRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockAssignmentRepository.getAllByCreateByAndAndOpenQuestion(any(User.class), eq(true)))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Assignment> result = assignmentServiceUnderTest.getAllByUserOpen(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure AssignmentRepository.getById(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        when(mockAssignmentRepository.getById(0L)).thenReturn(assignment);

        // Run the test
        final Assignment result = assignmentServiceUnderTest.getById(0L);

        // Verify the results
    }
}
