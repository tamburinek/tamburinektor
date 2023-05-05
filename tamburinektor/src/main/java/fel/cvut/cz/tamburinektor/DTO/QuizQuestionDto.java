package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QuizQuestionDto{

    private String question;

    private String imageLink;

    private String rightAnswer;

    private List<String> wrongAnswers;
}

