package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private Long id;

    private String question;

    private String answer;

    private String questionImage;

    private String answerImage;

    private String lectureType;
}
