package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.UserDto;
import fel.cvut.cz.tamburinektor.mappers.UserMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.security.jwt.JwtUtils;
import fel.cvut.cz.tamburinektor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper mockUserMapper;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private AuthenticationManager mockAuthenticationManager;
    @MockBean
    private PasswordEncoder mockEncoder;
    @MockBean
    private JwtUtils mockJwtUtils;


    @Test
    void testGetCurrentlyLoggedInUser() throws Exception {
        // Setup
        // Configure UserService.getUserByUsername(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/users/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testCreateUser() throws Exception {
        // Setup
        // Configure UserMapper.toUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserMapper.toUser(any(UserDto.class))).thenReturn(user);

        when(mockEncoder.encode("password")).thenReturn("password");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserService).createUser(any(User.class));
    }


    @Test
    void testAuthenticate() throws Exception {
        // Setup
        // Configure AuthenticationManager.authenticate(...).
        final Authentication authentication = new TestingAuthenticationToken("user", "pass", "ROLE_USER");
        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenReturn(authentication);

        when(mockJwtUtils.generateJwtToken(new TestingAuthenticationToken("user", "pass", "ROLE_USER")))
                .thenReturn("accessToken");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users/signin")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testAuthenticate_AuthenticationManagerThrowsAuthenticationException() throws Exception {
        // Setup
        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenThrow(AuthenticationException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users/signin")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
