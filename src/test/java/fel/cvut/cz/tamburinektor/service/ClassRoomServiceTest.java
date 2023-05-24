package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ClassRoomRepository;
import fel.cvut.cz.tamburinektor.model.Classroom;
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
class ClassRoomServiceTest {

    @Mock
    private ClassRoomRepository mockClassRoomRepository;

    private ClassRoomService classRoomServiceUnderTest;


    @BeforeEach
    void setUp() {
        classRoomServiceUnderTest = new ClassRoomService(mockClassRoomRepository);
    }


    @Test
    void testCreateOrUpdateClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        // Run the test
        classRoomServiceUnderTest.createOrUpdateClass(classroom);

        // Verify the results
        verify(mockClassRoomRepository).save(any(Classroom.class));
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

        // Configure ClassRoomRepository.getAllByCreateBy(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user1 = new User();
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(classrooms);

        // Run the test
        final int result = classRoomServiceUnderTest.getCountByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }


    @Test
    void testGetCountByUser_ClassRoomRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final int result = classRoomServiceUnderTest.getCountByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }


    @Test
    void testGetClassesCreatedBy() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure ClassRoomRepository.getAllByCreateBy(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user1 = new User();
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(classrooms);

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getClassesCreatedBy(user);

        // Verify the results
    }


    @Test
    void testGetClassesCreatedBy_ClassRoomRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getClassesCreatedBy(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetLastClass() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure ClassRoomRepository.getAllByCreateBy(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user1 = new User();
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(classrooms);

        // Run the test
        final Classroom result = classRoomServiceUnderTest.getLastClass(user);

        // Verify the results
    }


    @Test
    void testGetLastClass_ClassRoomRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockClassRoomRepository.getAllByCreateBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final Classroom result = classRoomServiceUnderTest.getLastClass(user);

        // Verify the results
        assertThat(result).isNull();
    }


    @Test
    void testGetClassById() {
        // Setup
        // Configure ClassRoomRepository.getById(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        when(mockClassRoomRepository.getById(0L)).thenReturn(classroom);

        // Run the test
        final Classroom result = classRoomServiceUnderTest.getClassById(0L);

        // Verify the results
    }


    @Test
    void testAddLectureToClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        final Lecture lecture1 = new Lecture();
        lecture1.setId(0L);
        lecture1.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture1.setCreatedBy(createdBy);

        // Run the test
        classRoomServiceUnderTest.addLectureToClass(classroom, lecture1);

        // Verify the results
        verify(mockClassRoomRepository).save(any(Classroom.class));
    }


    @Test
    void testRemoveLectureFromClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        final Lecture lecture1 = new Lecture();
        lecture1.setId(0L);
        lecture1.setDescription("description");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        lecture1.setCreatedBy(createdBy);

        // Run the test
        classRoomServiceUnderTest.removeLectureFromClass(classroom, lecture1);

        // Verify the results
        verify(mockClassRoomRepository).save(any(Classroom.class));
    }


    @Test
    void testAddTestToClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        final fel.cvut.cz.tamburinektor.model.test.Test test1 = new fel.cvut.cz.tamburinektor.model.test.Test();
        test1.setId(0L);
        test1.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test1.setCreateBy(createBy);

        // Run the test
        classRoomServiceUnderTest.addTestToClass(classroom, test1);

        // Verify the results
        verify(mockClassRoomRepository).save(any(Classroom.class));
    }


    @Test
    void testRemoveTestFromClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        final fel.cvut.cz.tamburinektor.model.test.Test test1 = new fel.cvut.cz.tamburinektor.model.test.Test();
        test1.setId(0L);
        test1.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test1.setCreateBy(createBy);

        // Run the test
        classRoomServiceUnderTest.removeTestFromClass(classroom, test1);

        // Verify the results
        verify(mockClassRoomRepository).save(any(Classroom.class));
    }


    @Test
    void testGetAllClasses() {
        // Setup
        // Configure ClassRoomRepository.findAll(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomRepository.findAll()).thenReturn(classrooms);

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getAllClasses();

        // Verify the results
    }


    @Test
    void testGetAllClasses_ClassRoomRepositoryReturnsNoItems() {
        // Setup
        when(mockClassRoomRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getAllClasses();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetAllClassesMy() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure ClassRoomRepository.getAllByUsersContaining(...).
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user1 = new User();
        classroom.setUsers(List.of(user1));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));
        final List<Classroom> classrooms = List.of(classroom);
        when(mockClassRoomRepository.getAllByUsersContaining(any(User.class))).thenReturn(classrooms);

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getAllClassesMy(user);

        // Verify the results
    }


    @Test
    void testGetAllClassesMy_ClassRoomRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockClassRoomRepository.getAllByUsersContaining(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Classroom> result = classRoomServiceUnderTest.getAllClassesMy(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testPutUserToClass() {
        // Setup
        final Classroom classroom = new Classroom();
        classroom.setId(0L);
        classroom.setPassword("password");
        final User user = new User();
        classroom.setUsers(List.of(user));
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        classroom.setTests(List.of(test));
        final Lecture lecture = new Lecture();
        lecture.setId(0L);
        classroom.setLectures(List.of(lecture));

        final User user1 = new User();
        user1.setId(0L);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");

        // Run the test
        final boolean result = classRoomServiceUnderTest.putUserToClass(classroom, user1, "password");

        // Verify the results
        assertThat(result).isTrue();
        verify(mockClassRoomRepository).save(any(Classroom.class));
    }
}
