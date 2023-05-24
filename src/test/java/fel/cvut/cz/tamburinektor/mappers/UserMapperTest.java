package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.UserDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserMapperTest {

    private UserMapper userMapperUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        userMapperUnderTest = new UserMapper();
    }


    @Test
    void testToDto() throws Exception {
        assertThat(userMapperUnderTest.toDto(new User())).isNull();
    }


    @Test
    void testToUser() {
        // Setup
        final UserDto userDTO = new UserDto();
        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setFirstName("firstName");
        userDTO.setLastName("lastName");
        userDTO.setUserType(UserType.ROLE_STUDENT);

        // Run the test
        final User result = userMapperUnderTest.toUser(userDTO);

        // Verify the results
    }
}
