package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
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
class LectureServiceTest {

    @Mock
    private LectureRepository mockLectureRepository;

    private LectureService lectureServiceUnderTest;


    @BeforeEach
    void setUp() {
        lectureServiceUnderTest = new LectureService(mockLectureRepository);
    }


    @Test
    void testCreateOrUpdateLecture() {
        // Setup
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture.setCreatedBy(createdBy);

        // Run the test
        lectureServiceUnderTest.createOrUpdateLecture(lecture);

        // Verify the results
        verify(mockLectureRepository).save(any(Lecture.class));
    }


    @Test
    void testGetAllLecturesByUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure LectureRepository.getAllByCreatedBy(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture.setCreatedBy(createdBy);
        final List<Lecture> lectures = List.of(lecture);
        when(mockLectureRepository.getAllByCreatedBy(any(User.class))).thenReturn(lectures);

        // Run the test
        final List<Lecture> result = lectureServiceUnderTest.getAllLecturesByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllLecturesByUser_LectureRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockLectureRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Lecture> result = lectureServiceUnderTest.getAllLecturesByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure LectureRepository.getLectureById(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture.setCreatedBy(createdBy);
        when(mockLectureRepository.getLectureById(0L)).thenReturn(lecture);

        // Run the test
        final Lecture result = lectureServiceUnderTest.getById(0L);

        // Verify the results
    }


    @Test
    void testGetLast() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure LectureRepository.getAllByCreatedBy(...).
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        lecture.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture.setCreatedBy(createdBy);
        final List<Lecture> lectures = List.of(lecture);
        when(mockLectureRepository.getAllByCreatedBy(any(User.class))).thenReturn(lectures);

        // Run the test
        final Lecture result = lectureServiceUnderTest.getLast(user);

        // Verify the results
    }


    @Test
    void testGetLast_LectureRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockLectureRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final Lecture result = lectureServiceUnderTest.getLast(user);

        // Verify the results
        assertThat(result).isNull();
    }
}
