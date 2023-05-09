package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DefinitionDto {

    private Long id;

    private String description;

    private String imageUrl;

    private String definition;

    private String lectureType;
}
