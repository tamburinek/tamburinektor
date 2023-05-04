package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import org.springframework.stereotype.Component;


@Component
public class DefinitionMapper {

    public DefinitionDto toDto(Definition definition) {
        return null;
    }

    public Definition toDefinition(DefinitionDto definitionDto, User user) {
        Definition definition = new Definition();
        definition.setType(LectureType.DEFINITION);
        definition.setDefinition(definitionDto.getDefinition());
        definition.setDescription(definitionDto.getDescription());
        definition.setCreatedBy(user);
        definition.setImageLink(definitionDto.getImageUrl());
        return definition;
    }
}
