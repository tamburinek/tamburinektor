package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.mappers.TestMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import fel.cvut.cz.tamburinektor.service.AssignmentService;
import fel.cvut.cz.tamburinektor.service.TestService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService mockTestService;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private TestMapper mockMapper;
    @MockBean
    private AssignmentService mockAssignmentService;


    @Test
    void testCreateTest() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure AssignmentService.getById(...).
        final Assignment assignment = new Assignment();
        assignment.setId(0L);
        assignment.setQuestion("question");
        assignment.setImageLink("imageLink");
        assignment.setOpenQuestion(false);
        assignment.setRightAnswers(List.of("value"));
        when(mockAssignmentService.getById(0L)).thenReturn(assignment);

        // Configure TestMapper.toTest(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        test.setCreateBy(createBy);
        final Assignment assignment1 = new Assignment();
        test.setAssignments(List.of(assignment1));
        final Assignment assignment2 = new Assignment();
        assignment2.setId(0L);
        assignment2.setQuestion("question");
        assignment2.setImageLink("imageLink");
        assignment2.setOpenQuestion(false);
        assignment2.setRightAnswers(List.of("value"));
        final List<Assignment> assignmentList = List.of(assignment2);
        when(mockMapper.toTest(any(TestDto.class), any(User.class), eq(assignmentList))).thenReturn(test);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/test")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockTestService).createOrUpdateTest(any(fel.cvut.cz.tamburinektor.model.test.Test.class));
    }


    @Test
    void testUpdateTest() throws Exception {
        // Setup
        // Configure TestService.getById(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        test.setCreateBy(createBy);
        final Assignment assignment = new Assignment();
        test.setAssignments(List.of(assignment));
        when(mockTestService.getById(0L)).thenReturn(test);

        // Configure AssignmentService.getById(...).
        final Assignment assignment1 = new Assignment();
        assignment1.setId(0L);
        assignment1.setQuestion("question");
        assignment1.setImageLink("imageLink");
        assignment1.setOpenQuestion(false);
        assignment1.setRightAnswers(List.of("value"));
        when(mockAssignmentService.getById(0L)).thenReturn(assignment1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/test/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockTestService).createOrUpdateTest(any(fel.cvut.cz.tamburinektor.model.test.Test.class));
    }


    @Test
    void testGetAllMyTests() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure TestService.getAllByUser(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        test.setCreateBy(createBy);
        final Assignment assignment = new Assignment();
        test.setAssignments(List.of(assignment));
        final List<fel.cvut.cz.tamburinektor.model.test.Test> tests = List.of(test);
        when(mockTestService.getAllByUser(any(User.class))).thenReturn(tests);

        // Configure TestMapper.toDto(...).
        final TestDto testDto = new TestDto();
        testDto.setId(0L);
        testDto.setDescription("description");
        testDto.setAssignments(List.of(0L));
        when(mockMapper.toDto(any(fel.cvut.cz.tamburinektor.model.test.Test.class))).thenReturn(testDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/test")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllMyTests_TestServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockTestService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/test")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetTestById() throws Exception {
        // Setup
        // Configure TestService.getById(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        test.setCreateBy(createBy);
        final Assignment assignment = new Assignment();
        test.setAssignments(List.of(assignment));
        when(mockTestService.getById(0L)).thenReturn(test);

        // Configure TestMapper.toDto(...).
        final TestDto testDto = new TestDto();
        testDto.setId(0L);
        testDto.setDescription("description");
        testDto.setAssignments(List.of(0L));
        when(mockMapper.toDto(any(fel.cvut.cz.tamburinektor.model.test.Test.class))).thenReturn(testDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/test/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastTestByMe() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure TestService.getLastByUser(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        test.setCreateBy(createBy);
        final Assignment assignment = new Assignment();
        test.setAssignments(List.of(assignment));
        when(mockTestService.getLastByUser(any(User.class))).thenReturn(test);

        // Configure TestMapper.toDto(...).
        final TestDto testDto = new TestDto();
        testDto.setId(0L);
        testDto.setDescription("description");
        testDto.setAssignments(List.of(0L));
        when(mockMapper.toDto(any(fel.cvut.cz.tamburinektor.model.test.Test.class))).thenReturn(testDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/test/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastTestByMe_TestServiceReturnsNull() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockTestService.getLastByUser(any(User.class))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/test/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }
}
