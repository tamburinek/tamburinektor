package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.UserRepository;
import fel.cvut.cz.tamburinektor.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userServiceUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        userServiceUnderTest = new UserService(mockUserRepository);
    }


    @Test
    void testGetUserByUsername() {
        // Setup
        // Configure UserRepository.getUserByUsername(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserRepository.getUserByUsername("username")).thenReturn(user);

        // Run the test
        final User result = userServiceUnderTest.getUserByUsername("username");

        // Verify the results
    }


    @Test
    void testCreateUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        userServiceUnderTest.createUser(user);

        // Verify the results
        verify(mockUserRepository).save(any(User.class));
    }


    @Test
    void testGetCurrentUser() {
        // Setup
        // Configure UserRepository.getUserByUsername(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserRepository.getUserByUsername("username")).thenReturn(user);

        // Run the test
        final User result = userServiceUnderTest.getCurrentUser();

        // Verify the results
    }
}
