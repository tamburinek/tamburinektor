package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QuizDto {

    private Long id;

    private String name;

    private List<QuizQuestionDto> questions;

    private String lectureType;
}
