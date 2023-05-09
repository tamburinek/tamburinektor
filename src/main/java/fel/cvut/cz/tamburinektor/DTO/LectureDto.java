package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class LectureDto {

    private Long id;

    private String description;

    private List<LectureEntityDto> lectureEntities;
}