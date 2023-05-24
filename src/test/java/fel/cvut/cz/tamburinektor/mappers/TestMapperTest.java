package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class TestMapperTest {

    private TestMapper testMapperUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        testMapperUnderTest = new TestMapper();
    }


    @Test
    void testToDto() throws Exception {
        // Setup
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        test.setCreateBy(createBy);
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        test.setAssignments(List.of(assignment));

        // Run the test
        final TestDto result = testMapperUnderTest.toDto(test);

        // Verify the results
    }


    @Test
    void testToTest() {
        // Setup
        final TestDto testDto = new TestDto();
        testDto.setId(0L);
        testDto.setDescription("description");
        testDto.setAssignments(List.of(0L));

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        final List<Assignment> assignmentList = List.of(assignment);

        // Run the test
        final fel.cvut.cz.tamburinektor.model.test.Test result = testMapperUnderTest.toTest(testDto, user,
                assignmentList);

        // Verify the results
    }
}
