package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class LectureMapperTest {

    private LectureMapper lectureMapperUnderTest;


    @BeforeEach
    void setUp() {
        lectureMapperUnderTest = new LectureMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        lecture.setCreatedBy(createdBy);
        final LectureEntity entity = new LectureEntity();
        lecture.setLectureEntities(List.of(entity));

        final LectureEntityDto dto = new LectureEntityDto();
        dto.setId(0L);
        dto.setName("name");
        dto.setLectureType("lectureType");
        final List<LectureEntityDto> dtos = List.of(dto);

        // Run the test
        final LectureDto result = lectureMapperUnderTest.toDto(lecture, dtos);

        // Verify the results
    }


    @Test
    void testToLecture() {
        // Setup
        final LectureDto lectureDto = new LectureDto();
        lectureDto.setId(0L);
        lectureDto.setDescription("description");
        lectureDto.setActive(false);
        final LectureEntityDto dto = new LectureEntityDto();
        dto.setId(0L);
        lectureDto.setLectureEntities(List.of(dto));

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        final List<LectureEntity> entities = List.of(entity);

        // Run the test
        final Lecture result = lectureMapperUnderTest.toLecture(lectureDto, user, entities);

        // Verify the results
    }
}
