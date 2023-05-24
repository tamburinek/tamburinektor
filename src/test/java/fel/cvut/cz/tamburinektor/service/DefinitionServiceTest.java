package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.DefinitionRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
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
class DefinitionServiceTest {

    @Mock
    private DefinitionRepository mockDefinitionRepository;

    private DefinitionService definitionServiceUnderTest;


    @BeforeEach
    void setUp() {
        definitionServiceUnderTest = new DefinitionService(mockDefinitionRepository);
    }


    @Test
    void testCreateDefinition() {
        // Setup
        final Definition definition = new Definition();
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");

        // Run the test
        definitionServiceUnderTest.createDefinition(definition);

        // Verify the results
        verify(mockDefinitionRepository).save(any(Definition.class));
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

        // Configure DefinitionRepository.getAllByCreatedBy(...).
        final Definition definition = new Definition();
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        final List<Definition> definitions = List.of(definition);
        when(mockDefinitionRepository.getAllByCreatedBy(any(User.class))).thenReturn(definitions);

        // Run the test
        final List<Definition> result = definitionServiceUnderTest.getAllByUser(user);

        // Verify the results
    }


    @Test
    void testGetAllByUser_DefinitionRepositoryReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(mockDefinitionRepository.getAllByCreatedBy(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Definition> result = definitionServiceUnderTest.getAllByUser(user);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetById() {
        // Setup
        // Configure DefinitionRepository.getDefinitionById(...).
        final Definition definition = new Definition();
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");
        when(mockDefinitionRepository.getDefinitionById(0L)).thenReturn(definition);

        // Run the test
        final Definition result = definitionServiceUnderTest.getById(0L);

        // Verify the results
    }
}
