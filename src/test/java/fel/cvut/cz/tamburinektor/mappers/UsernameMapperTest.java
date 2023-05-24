package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.UsernameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UsernameMapperTest {

    private UsernameMapper usernameMapperUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        usernameMapperUnderTest = new UsernameMapper();
    }


    @Test
    void testToDto() throws Exception {
        // Setup
        // Run the test
        final UsernameDto result = usernameMapperUnderTest.toDto("name", "surname");

        // Verify the results
    }
}
