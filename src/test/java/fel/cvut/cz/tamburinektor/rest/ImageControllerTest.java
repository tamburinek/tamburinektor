package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.mappers.ImageMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import fel.cvut.cz.tamburinektor.service.ImageService;
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
@WebMvcTest(ImageController.class)
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService mockImageService;
    @MockBean
    private ImageMapper mockImageMapper;
    @MockBean
    private UserService mockUserService;


    @Test
    void testCreateImage() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ImageMapper.toImage(...).
        final Image image = new Image();
        image.setId(0L);
        image.setImageLink("imageLink");
        image.setDescription("description");
        when(mockImageMapper.toImage(any(ImageDto.class), any(User.class))).thenReturn(image);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/image")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockImageService).createImage(any(Image.class));
    }


    @Test
    void testGetAllImagesByUser() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure ImageService.getAllByUser(...).
        final Image image = new Image();
        image.setId(0L);
        image.setImageLink("imageLink");
        image.setDescription("description");
        final List<Image> images = List.of(image);
        when(mockImageService.getAllByUser(any(User.class))).thenReturn(images);

        // Configure ImageMapper.toDto(...).
        final ImageDto imageDto = new ImageDto();
        imageDto.setId(0L);
        imageDto.setDescription("description");
        imageDto.setImageUrl("imageLink");
        imageDto.setLectureType("lectureType");
        when(mockImageMapper.toDto(any(Image.class))).thenReturn(imageDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/image")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllImagesByUser_ImageServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockImageService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/image")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetImageById() throws Exception {
        // Setup
        // Configure ImageService.getById(...).
        final Image image = new Image();
        image.setId(0L);
        image.setImageLink("imageLink");
        image.setDescription("description");
        when(mockImageService.getById(0L)).thenReturn(image);

        // Configure ImageMapper.toDto(...).
        final ImageDto imageDto = new ImageDto();
        imageDto.setId(0L);
        imageDto.setDescription("description");
        imageDto.setImageUrl("imageLink");
        imageDto.setLectureType("lectureType");
        when(mockImageMapper.toDto(any(Image.class))).thenReturn(imageDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/image/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateImage() throws Exception {
        // Setup
        // Configure ImageService.getById(...).
        final Image image = new Image();
        image.setId(0L);
        image.setImageLink("imageLink");
        image.setDescription("description");
        when(mockImageService.getById(0L)).thenReturn(image);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/image/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockImageService).createImage(any(Image.class));
    }
}
