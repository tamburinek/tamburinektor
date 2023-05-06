package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignmentDto {

    private Long id;

    private int point = 1;

    private String question;

    private String imageLink;

    private boolean openQuestion;

    private List<String> rightAnswers;

    private List<String> wrongAnswers;
}
