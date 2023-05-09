package fel.cvut.cz.tamburinektor.DTO;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionAnswerDto {

    private Long id;

    private Long questionId;

    private String answer;
}
