package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import fel.cvut.cz.tamburinektor.service.LectureEntityService;
import fel.cvut.cz.tamburinektor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LectureEntityController.class)
class LectureEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureEntityService mockLectureEntityService;
    @MockBean
    private UserService mockUserService;


    @Test
    void testGetCountOfMaterials() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockLectureEntityService.getCountByUser(any(User.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/material/count")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastEntity1() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure LectureEntityService.getLastMaterial(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        when(mockLectureEntityService.getLastMaterial(any(User.class))).thenReturn(entity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/material/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastEntity2() throws Exception {
        // Setup
        // Configure LectureEntityService.getEntityById(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        when(mockLectureEntityService.getEntityById(0L)).thenReturn(entity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/material/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
