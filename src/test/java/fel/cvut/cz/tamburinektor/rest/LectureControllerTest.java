package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.mappers.LectureEntityMapper;
import fel.cvut.cz.tamburinektor.mappers.LectureMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import fel.cvut.cz.tamburinektor.service.LectureEntityService;
import fel.cvut.cz.tamburinektor.service.LectureService;
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
@WebMvcTest(LectureController.class)
class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService mockLectureService;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private LectureMapper mockMapper;
    @MockBean
    private LectureEntityMapper mockEntityMapper;
    @MockBean
    private LectureEntityService mockLectureEntityService;


    @Test
    void testCreateLecture() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure LectureEntityService.getEntityById(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        when(mockLectureEntityService.getEntityById(0L)).thenReturn(entity);

        // Configure LectureMapper.toLecture(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity1 = new LectureEntity();
        lecture.setLectureEntities(List.of(entity1));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        final LectureEntity entity2 = new LectureEntity();
        entity2.setId(0L);
        entity2.setType(LectureType.DEFINITION);
        final User createdBy1 = new User();
        createdBy1.setId(0L);
        createdBy1.setUsername("username");
        entity2.setCreatedBy(createdBy1);
        final List<LectureEntity> entities = List.of(entity2);
        when(mockMapper.toLecture(any(LectureDto.class), any(User.class), eq(entities))).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/lecture")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockLectureService).createOrUpdateLecture(any(Lecture.class));
    }


    @Test
    void testGetAllLecturesByMe() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure LectureService.getAllLecturesByUser(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        final List<Lecture> lectures = List.of(lecture);
        when(mockLectureService.getAllLecturesByUser(any(User.class))).thenReturn(lectures);

        // Configure LectureEntityMapper.toDto(...).
        final LectureEntityDto dto = new LectureEntityDto();
        dto.setId(0L);
        dto.setName("name");
        dto.setLectureType("lectureType");
        when(mockEntityMapper.toDto(any(LectureEntity.class))).thenReturn(dto);

        // Configure LectureMapper.toDto(...).
        final LectureDto lectureDto = new LectureDto();
        lectureDto.setId(0L);
        lectureDto.setDescription("description");
        lectureDto.setActive(false);
        final LectureEntityDto dto1 = new LectureEntityDto();
        dto1.setId(0L);
        lectureDto.setLectureEntities(List.of(dto1));
        final LectureEntityDto dto2 = new LectureEntityDto();
        dto2.setId(0L);
        dto2.setName("name");
        dto2.setLectureType("lectureType");
        final List<LectureEntityDto> dtos = List.of(dto2);
        when(mockMapper.toDto(any(Lecture.class), eq(dtos))).thenReturn(lectureDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllLecturesByMe_LectureServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockLectureService.getAllLecturesByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetLectureById() throws Exception {
        // Setup
        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Configure LectureEntityMapper.toDto(...).
        final LectureEntityDto dto = new LectureEntityDto();
        dto.setId(0L);
        dto.setName("name");
        dto.setLectureType("lectureType");
        when(mockEntityMapper.toDto(any(LectureEntity.class))).thenReturn(dto);

        // Configure LectureMapper.toDto(...).
        final LectureDto lectureDto = new LectureDto();
        lectureDto.setId(0L);
        lectureDto.setDescription("description");
        lectureDto.setActive(false);
        final LectureEntityDto dto1 = new LectureEntityDto();
        dto1.setId(0L);
        lectureDto.setLectureEntities(List.of(dto1));
        final LectureEntityDto dto2 = new LectureEntityDto();
        dto2.setId(0L);
        dto2.setName("name");
        dto2.setLectureType("lectureType");
        final List<LectureEntityDto> dtos = List.of(dto2);
        when(mockMapper.toDto(any(Lecture.class), eq(dtos))).thenReturn(lectureDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateLecture() throws Exception {
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

        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity1 = new LectureEntity();
        lecture.setLectureEntities(List.of(entity1));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/lecture/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockLectureService).createOrUpdateLecture(any(Lecture.class));
    }


    @Test
    void testGetLastLectureName() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure LectureService.getLast(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getLast(any(User.class))).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetLastLectureName_LectureServiceReturnsNull() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockLectureService.getLast(any(User.class))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture/last")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }


    @Test
    void testGetLectureIsActive() throws Exception {
        // Setup
        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture/{id}/active", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testSetActiveForLecture() throws Exception {
        // Setup
        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/lecture/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockLectureService).createOrUpdateLecture(any(Lecture.class));
    }


    @Test
    void testGetActiveEntityIndex() throws Exception {
        // Setup
        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/lecture/{id}/last", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testSetLastEntityId() throws Exception {
        // Setup
        // Configure LectureService.getById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));
        final LectureEntity lastEntity = new LectureEntity();
        lecture.setLastEntity(lastEntity);
        lecture.setOpen(false);
        when(mockLectureService.getById(0L)).thenReturn(lecture);

        // Configure LectureEntityService.getEntityById(...).
        final LectureEntity entity1 = new LectureEntity();
        entity1.setId(0L);
        entity1.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity1.setCreatedBy(createdBy);
        when(mockLectureEntityService.getEntityById(0L)).thenReturn(entity1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/lecture/{id}/last", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockLectureService).createOrUpdateLecture(any(Lecture.class));
    }
}
