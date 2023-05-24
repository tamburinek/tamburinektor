package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.mappers.DefinitionMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import fel.cvut.cz.tamburinektor.service.DefinitionService;
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
@WebMvcTest(DefinitionController.class)
class DefinitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefinitionService mockDefinitionService;
    @MockBean
    private DefinitionMapper mockDefinitionMapper;
    @MockBean
    private UserService mockUserService;


    @Test
    void testCreateDefinition() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure DefinitionMapper.toDefinition(...).
        final Definition definition = new Definition();
        definition.setId(0L);
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        when(mockDefinitionMapper.toDefinition(any(DefinitionDto.class), any(User.class))).thenReturn(definition);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/definition")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockDefinitionService).createDefinition(any(Definition.class));
    }


    @Test
    void testGetAllDefinitionsByMe() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure DefinitionService.getAllByUser(...).
        final Definition definition = new Definition();
        definition.setId(0L);
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        final List<Definition> definitions = List.of(definition);
        when(mockDefinitionService.getAllByUser(any(User.class))).thenReturn(definitions);

        // Configure DefinitionMapper.toDto(...).
        final DefinitionDto definitionDto = new DefinitionDto();
        definitionDto.setId(0L);
        definitionDto.setDescription("description");
        definitionDto.setImageUrl("imageLink");
        definitionDto.setDefinition("definition");
        definitionDto.setLectureType("lectureType");
        when(mockDefinitionMapper.toDto(any(Definition.class))).thenReturn(definitionDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/definition")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllDefinitionsByMe_DefinitionServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockDefinitionService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/definition")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetDefinitionById() throws Exception {
        // Setup
        // Configure DefinitionService.getById(...).
        final Definition definition = new Definition();
        definition.setId(0L);
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        when(mockDefinitionService.getById(0L)).thenReturn(definition);

        // Configure DefinitionMapper.toDto(...).
        final DefinitionDto definitionDto = new DefinitionDto();
        definitionDto.setId(0L);
        definitionDto.setDescription("description");
        definitionDto.setImageUrl("imageLink");
        definitionDto.setDefinition("definition");
        definitionDto.setLectureType("lectureType");
        when(mockDefinitionMapper.toDto(any(Definition.class))).thenReturn(definitionDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/definition/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateDefinition() throws Exception {
        // Setup
        // Configure DefinitionService.getById(...).
        final Definition definition = new Definition();
        definition.setId(0L);
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        when(mockDefinitionService.getById(0L)).thenReturn(definition);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/definition/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockDefinitionService).createDefinition(any(Definition.class));
    }
}
