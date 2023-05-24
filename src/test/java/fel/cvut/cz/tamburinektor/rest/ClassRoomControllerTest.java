package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ClassRoomDto;
import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.DTO.UsernameDto;
import fel.cvut.cz.tamburinektor.mappers.ClassRoomMapper;
import fel.cvut.cz.tamburinektor.mappers.LectureMapper;
import fel.cvut.cz.tamburinektor.mappers.TestMapper;
import fel.cvut.cz.tamburinektor.mappers.UsernameMapper;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.service.ClassRoomService;
import fel.cvut.cz.tamburinektor.service.LectureService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ClassRoomController.class)
class ClassRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassRoomService mockClassRoomService;
    @MockBean
    private ClassRoomMapper mockMapper;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private UsernameMapper mockUsernameMapper;
    @MockBean
    private LectureService mockLectureService;
    @MockBean
    private LectureMapper mockLectureMapper;
    @MockBean
    private TestMapper mockTestMapper;
    @MockBean
    private TestService mockTestService;


    @Test
    void testCreateClass() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ClassRoomMapper.toClassRoom(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user1 = new User();
        user1.setUsername("username");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockMapper.toClassRoom(any(ClassRoomDto.class), any(User.class))).thenReturn(classroom);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/class")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockClassRoomService).createOrUpdateClass(any(Classroom.class));
    }


    @Test
    void testGetClassCount() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockClassRoomService.getCountByUser(any(User.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/count")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetClassesByUser() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ClassRoomService.getClassesCreatedBy(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user1 = new User();
        user1.setUsername("username");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomService.getClassesCreatedBy(any(User.class))).thenReturn(classrooms);

        // Configure ClassRoomMapper.toDto(...).
        final ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(0L);
        classRoomDto.setName("name");
        classRoomDto.setPassword("password");
        classRoomDto.setUsername("username");
        when(mockMapper.toDto(any(Classroom.class))).thenReturn(classRoomDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetClassesByUser_ClassRoomServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockClassRoomService.getClassesCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetLastClass() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ClassRoomService.getLastClass(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user1 = new User();
        user1.setUsername("username");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getLastClass(any(User.class))).thenReturn(classroom);

        // Configure ClassRoomMapper.toDto(...).
        final ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(0L);
        classRoomDto.setName("name");
        classRoomDto.setPassword("password");
        classRoomDto.setUsername("username");
        when(mockMapper.toDto(any(Classroom.class))).thenReturn(classRoomDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastClass_ClassRoomServiceReturnsNull() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockClassRoomService.getLastClass(any(User.class))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }


    @Test
    void testGetLecturesFromClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure LectureMapper.toDto(...).
        final LectureDto lectureDto = new LectureDto();
        lectureDto.setId(0L);
        lectureDto.setDescription("description");
        lectureDto.setActive(false);
        final LectureEntityDto dto = new LectureEntityDto();
        dto.setId(0L);
        lectureDto.setLectureEntities(List.of(dto));
        final LectureEntityDto dto1 = new LectureEntityDto();
        dto1.setId(0L);
        dto1.setName("name");
        dto1.setLectureType("lectureType");
        final List<LectureEntityDto> dtos = List.of(dto1);
        when(mockLectureMapper.toDto(any(Lecture.class), eq(dtos))).thenReturn(lectureDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/{id}/lecture", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetTestsFromClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure TestMapper.toDto(...).
        final TestDto testDto = new TestDto();
        testDto.setId(0L);
        testDto.setDescription("description");
        testDto.setAssignments(List.of(0L));
        when(mockTestMapper.toDto(any(fel.cvut.cz.tamburinektor.model.test.Test.class))).thenReturn(testDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/{id}/test", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetUsersByClassId() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure UsernameMapper.toDto(...).
        final UsernameDto usernameDto = new UsernameDto();
        usernameDto.setFirstName("firstName");
        usernameDto.setLastName("lastName");
        when(mockUsernameMapper.toDto("firstName", "lastName")).thenReturn(usernameDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/{id}/users", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testAddLectureToClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure LectureService.getById(...).
        final Lecture lecture1 = new Lecture();
        lecture1.setId(0L);
        lecture1.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture1.setCreatedBy(createdBy);
        when(mockLectureService.getById(0L)).thenReturn(lecture1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/class/{id}/lecture/{lectureId}", 0, 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockClassRoomService).addLectureToClass(any(Classroom.class), any(Lecture.class));
    }


    @Test
    void testDeleteLectureFromClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure LectureService.getById(...).
        final Lecture lecture1 = new Lecture();
        lecture1.setId(0L);
        lecture1.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture1.setCreatedBy(createdBy);
        when(mockLectureService.getById(0L)).thenReturn(lecture1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/class/{id}/lecture/{lectureId}", 0, 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockClassRoomService).removeLectureFromClass(any(Classroom.class), any(Lecture.class));
    }


    @Test
    void testAddTestToClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure TestService.getById(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test1 = new fel.cvut.cz.tamburinektor.model.test.Test();
        test1.setId(0L);
        test1.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test1.setCreateBy(createBy);
        when(mockTestService.getById(0L)).thenReturn(test1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/class/{id}/test/{testId}", 0, 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockClassRoomService).addTestToClass(any(Classroom.class),
                any(fel.cvut.cz.tamburinektor.model.test.Test.class));
    }


    @Test
    void testDeleteTestFromClass() throws Exception {
        // Setup
        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        // Configure TestService.getById(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test1 = new fel.cvut.cz.tamburinektor.model.test.Test();
        test1.setId(0L);
        test1.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test1.setCreateBy(createBy);
        when(mockTestService.getById(0L)).thenReturn(test1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/class/{id}/test/{testId}", 0, 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockClassRoomService).removeTestFromClass(any(Classroom.class),
                any(fel.cvut.cz.tamburinektor.model.test.Test.class));
    }


    @Test
    void testGetAllClasses() throws Exception {
        // Setup
        // Configure ClassRoomService.getAllClasses(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomService.getAllClasses()).thenReturn(classrooms);

        // Configure ClassRoomMapper.toDto(...).
        final ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(0L);
        classRoomDto.setName("name");
        classRoomDto.setPassword("password");
        classRoomDto.setUsername("username");
        when(mockMapper.toDto(any(Classroom.class))).thenReturn(classRoomDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllClasses_ClassRoomServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockClassRoomService.getAllClasses()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetAllClassesIAmIn() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ClassRoomService.getAllClassesMy(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user1 = new User();
        user1.setUsername("username");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomService.getAllClassesMy(any(User.class))).thenReturn(classrooms);

        // Configure ClassRoomMapper.toDto(...).
        final ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(0L);
        classRoomDto.setName("name");
        classRoomDto.setPassword("password");
        classRoomDto.setUsername("username");
        when(mockMapper.toDto(any(Classroom.class))).thenReturn(classRoomDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/my")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllClassesIAmIn_ClassRoomServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockClassRoomService.getAllClassesMy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/class/my")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testAddMeToClass() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ClassRoomService.getClassById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setName("name");
        final User user1 = new User();
        user1.setUsername("username");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomService.getClassById(0L)).thenReturn(classroom);

        when(mockClassRoomService.putUserToClass(any(Classroom.class), any(User.class), eq("password")))
                .thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/class/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
