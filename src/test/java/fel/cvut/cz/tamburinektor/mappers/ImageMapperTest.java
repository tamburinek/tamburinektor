package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ImageMapperTest {

    private ImageMapper imageMapperUnderTest;


    @BeforeEach
    void setUp() {
        imageMapperUnderTest = new ImageMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Image image = new Image();
        image.setId(0L);
        image.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        image.setCreatedBy(createdBy);
        image.setImageLink("imageLink");
        image.setDescription("description");

        // Run the test
        final ImageDto result = imageMapperUnderTest.toDto(image);

        // Verify the results
    }


    @Test
    void testToImage() {
        // Setup
        final ImageDto imageDto = new ImageDto();
        imageDto.setId(0L);
        imageDto.setDescription("description");
        imageDto.setImageUrl("imageLink");
        imageDto.setLectureType("name");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Image result = imageMapperUnderTest.toImage(imageDto, user);

        // Verify the results
    }
}
