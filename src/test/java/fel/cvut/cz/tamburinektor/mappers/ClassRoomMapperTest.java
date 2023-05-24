package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.ClassRoomDto;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClassRoomMapperTest {

    private ClassRoomMapper classRoomMapperUnderTest;


    @BeforeEach
    void setUp() {
        classRoomMapperUnderTest = new ClassRoomMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        classroom.setName("name");
        final User createBy = new User();
        createBy.setFirstName("firstName");
        createBy.setLastName("lastName");
        classroom.setCreateBy(createBy);

        // Run the test
        final ClassRoomDto result = classRoomMapperUnderTest.toDto(classroom);

        // Verify the results
    }


    @Test
    void testToClassRoom() {
        // Setup
        final ClassRoomDto dto = new ClassRoomDto();
        dto.setId(0L);
        dto.setName("name");
        dto.setPassword("password");
        dto.setUsername("username");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Classroom result = classRoomMapperUnderTest.toClassRoom(dto, user);

        // Verify the results
    }
}
