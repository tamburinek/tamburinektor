package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LectureEntityServiceTest {

    @Mock
    private LectureEntityRepository mockLectureEntityRepository;

    private LectureEntityService lectureEntityServiceUnderTest;


    @BeforeEach
    void setUp() {
        lectureEntityServiceUnderTest = new LectureEntityService(mockLectureEntityRepository);
    }


    @Test
    void testGetEntityById() {
        // Setup
        // Configure LectureEntityRepository.getById(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        when(mockLectureEntityRepository.getById(0L)).thenReturn(entity);

        // Run the test
        final LectureEntity result = lectureEntityServiceUnderTest.getEntityById(0L);

        // Verify the results
    }


    @Test
    void testGetCountByUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure LectureEntityRepository.getAllByCreatedBy(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        final List<LectureEntity> entities = List.of(entity);
        when(mockLectureEntityRepository.getAllByCreatedBy(any(User.class))).thenReturn(entities);

        // Run the test
        final int result = lectureEntityServiceUnderTest.getCountByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }


    @Test
    void testGetCountByUser_LectureEntityRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockLectureEntityRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final int result = lectureEntityServiceUnderTest.getCountByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }


    @Test
    void testGetLastMaterial() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure LectureEntityRepository.getAllByCreatedBy(...).
        final LectureEntity entity = new LectureEntity();
        entity.setId(0L);
        entity.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        entity.setCreatedBy(createdBy);
        final List<LectureEntity> entities = List.of(entity);
        when(mockLectureEntityRepository.getAllByCreatedBy(any(User.class))).thenReturn(entities);

        // Run the test
        final LectureEntity result = lectureEntityServiceUnderTest.getLastMaterial(user);

        // Verify the results
    }


    @Test
    void testGetLastMaterial_LectureEntityRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockLectureEntityRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final LectureEntity result = lectureEntityServiceUnderTest.getLastMaterial(user);

        // Verify the results
        assertThat(result).isNull();
    }
}
