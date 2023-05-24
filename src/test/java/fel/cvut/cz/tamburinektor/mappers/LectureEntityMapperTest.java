package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class LectureEntityMapperTest {

    private LectureEntityMapper lectureEntityMapperUnderTest;


    @BeforeEach
    void setUp() {
        lectureEntityMapperUnderTest = new LectureEntityMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(0L);
        lectureEntity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lectureEntity.setCreatedBy(createdBy);

        // Run the test
        final LectureEntityDto result = lectureEntityMapperUnderTest.toDto(lectureEntity);

        // Verify the results
    }


    @Test
    void testToLectureEntity() {
        assertThat(lectureEntityMapperUnderTest.toLectureEntity(new LectureEntityDto())).isNull();
    }
}
