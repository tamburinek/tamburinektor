package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.TestRepository;
import fel.cvut.cz.tamburinektor.model.User;
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
class TestServiceTest {

    @Mock
    private TestRepository mockTestRepository;

    private TestService testServiceUnderTest;


    @BeforeEach
    void setUp() throws Exception {
        testServiceUnderTest = new TestService(mockTestRepository);
    }


    @Test
    void testCreateOrUpdateTest() {
        // Setup
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test.setCreateBy(createBy);

        // Run the test
        testServiceUnderTest.createOrUpdateTest(test);

        // Verify the results
        verify(mockTestRepository).save(any(fel.cvut.cz.tamburinektor.model.test.Test.class));
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

        // Configure TestRepository.getAllByCreateBy(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test.setCreateBy(createBy);
        final List<fel.cvut.cz.tamburinektor.model.test.Test> tests = List.of(test);
        when(mockTestRepository.getAllByCreateBy(any(User.class))).thenReturn(tests);

        // Run the test
        final List<fel.cvut.cz.tamburinektor.model.test.Test> result = testServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_TestRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockTestRepository.getAllByCreateBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<fel.cvut.cz.tamburinektor.model.test.Test> result = testServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure TestRepository.getById(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test.setCreateBy(createBy);
        when(mockTestRepository.getById(0L)).thenReturn(test);

        // Run the test
        final fel.cvut.cz.tamburinektor.model.test.Test result = testServiceUnderTest.getById(0L);

        // Verify the results
    }


    @Test
    void testGetLastByUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Configure TestRepository.getAllByCreateBy(...).
        final fel.cvut.cz.tamburinektor.model.test.Test test = new fel.cvut.cz.tamburinektor.model.test.Test();
        test.setId(0L);
        test.setDescription("description");
        final User createBy = new User();
        createBy.setId(0L);
        createBy.setUsername("username");
        test.setCreateBy(createBy);
        final List<fel.cvut.cz.tamburinektor.model.test.Test> tests = List.of(test);
        when(mockTestRepository.getAllByCreateBy(any(User.class))).thenReturn(tests);

        // Run the test
        final fel.cvut.cz.tamburinektor.model.test.Test result = testServiceUnderTest.getLastByUser(user);

        // Verify the results
    }


    @Test
    void testGetLastByUser_TestRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockTestRepository.getAllByCreateBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final fel.cvut.cz.tamburinektor.model.test.Test result = testServiceUnderTest.getLastByUser(user);

        // Verify the results
        assertThat(result).isNull();
    }
}
