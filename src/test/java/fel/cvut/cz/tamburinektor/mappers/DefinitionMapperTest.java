package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DefinitionMapperTest {

    private DefinitionMapper definitionMapperUnderTest;


    @BeforeEach
    void setUp() {
        definitionMapperUnderTest = new DefinitionMapper();
    }


    @Test
    void testToDto() {
        // Setup
        final Definition definition = new Definition();
        definition.setId(0L);
        definition.setType(LectureType.DEFINITION);
        final User createdBy = new User();
        definition.setCreatedBy(createdBy);
        definition.setDescription("description");
        definition.setDefinition("definition");
        definition.setImageLink("imageLink");

        // Run the test
        final DefinitionDto result = definitionMapperUnderTest.toDto(definition);

        // Verify the results
    }


    @Test
    void testToDefinition() {
        // Setup
        final DefinitionDto definitionDto = new DefinitionDto();
        definitionDto.setId(0L);
        definitionDto.setDescription("description");
        definitionDto.setImageUrl("imageLink");
        definitionDto.setDefinition("definition");
        definitionDto.setLectureType("name");

        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        // Run the test
        final Definition result = definitionMapperUnderTest.toDefinition(definitionDto, user);

        // Verify the results
    }
}
