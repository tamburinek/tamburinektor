package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.AssignmentDto;
import fel.cvut.cz.tamburinektor.mappers.AssignmentMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import fel.cvut.cz.tamburinektor.service.AssignmentService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AssignmentController.class)
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService mockAssignmentService;
    @MockBean
    private AssignmentMapper mockMapper;
    @MockBean
    private UserService mockUserService;


    @Test
    void testCreateAssignment() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure AssignmentMapper.toAssignment(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        when(mockMapper.toAssignment(any(AssignmentDto.class), any(User.class))).thenReturn(assignment);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/assignment")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockAssignmentService).createOrUploadAssignment(any(Assignment.class));
    }


    @Test
    void testUpdateAssignment() throws Exception {
        // Setup
        // Configure AssignmentService.getById(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        when(mockAssignmentService.getById(0L)).thenReturn(assignment);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/assignment")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockAssignmentService).createOrUploadAssignment(any(Assignment.class));
    }


    @Test
    void testGetAllAssignmentByMeClose() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure AssignmentService.getAllByUserClosed(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        final List<Assignment> assignments = List.of(assignment);
        when(mockAssignmentService.getAllByUserClosed(any(User.class))).thenReturn(assignments);

        // Configure AssignmentMapper.toDto(...).
        final AssignmentDto dto = new AssignmentDto();
        dto.setId(0L);
        dto.setQuestion("question");
        dto.setImageLink("imageLink");
        dto.setOpenQuestion(false);
        dto.setRightAnswers(List.of("value"));
        dto.setWrongAnswers(List.of("value"));
        when(mockMapper.toDto(any(Assignment.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/assignment/close")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllAssignmentByMeClose_AssignmentServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockAssignmentService.getAllByUserClosed(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/assignment/close")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetAllAssignmentByMeOpen() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure AssignmentService.getAllByUserOpen(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        final List<Assignment> assignments = List.of(assignment);
        when(mockAssignmentService.getAllByUserOpen(any(User.class))).thenReturn(assignments);

        // Configure AssignmentMapper.toDto(...).
        final AssignmentDto dto = new AssignmentDto();
        dto.setId(0L);
        dto.setQuestion("question");
        dto.setImageLink("imageLink");
        dto.setOpenQuestion(false);
        dto.setRightAnswers(List.of("value"));
        dto.setWrongAnswers(List.of("value"));
        when(mockMapper.toDto(any(Assignment.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/assignment/open")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllAssignmentByMeOpen_AssignmentServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockAssignmentService.getAllByUserOpen(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/assignment/open")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetAssignmentById() throws Exception {
        // Setup
        // Configure AssignmentService.getById(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        assignment.setWrongAnswers(List.of("value"));
        when(mockAssignmentService.getById(0L)).thenReturn(assignment);

        // Configure AssignmentMapper.toDto(...).
        final AssignmentDto dto = new AssignmentDto();
        dto.setId(0L);
        dto.setQuestion("question");
        dto.setImageLink("imageLink");
        dto.setOpenQuestion(false);
        dto.setRightAnswers(List.of("value"));
        dto.setWrongAnswers(List.of("value"));
        when(mockMapper.toDto(any(Assignment.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/assignment/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
