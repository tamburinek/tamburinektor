package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ImageRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    private ImageRepository mockImageRepository;

    private ImageService imageServiceUnderTest;


    @BeforeEach
    void setUp() {
        imageServiceUnderTest = new ImageService(mockImageRepository);
    }


    @Test
    void testCreateImage() {
        // Setup
        final Image image = new Image();
        image.setImageLink("imageLink");
        image.setDescription("description");

        // Run the test
        imageServiceUnderTest.createImage(image);

        // Verify the results
        verify(mockImageRepository).save(any(Image.class));
    }


    @Test
    void testGetAllByUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure ImageRepository.getAllByCreatedBy(...).
        final Image image = new Image();
        image.setImageLink("imageLink");
        image.setDescription("description");
        final List<Image> images = List.of(image);
        when(mockImageRepository.getAllByCreatedBy(any(User.class))).thenReturn(images);

        // Run the test
        final List<Image> result = imageServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_ImageRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockImageRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Image> result = imageServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure ImageRepository.getById(...).
        final Image image = new Image();
        image.setImageLink("imageLink");
        image.setDescription("description");
        when(mockImageRepository.getById(0L)).thenReturn(image);

        // Run the test
        final Image result = imageServiceUnderTest.getById(0L);

        // Verify the results
    }
}
