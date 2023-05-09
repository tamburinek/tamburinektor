package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionDto {

    private Long id;

    private String questionText;

    private boolean anonymous;

    private String lectureType;
}
