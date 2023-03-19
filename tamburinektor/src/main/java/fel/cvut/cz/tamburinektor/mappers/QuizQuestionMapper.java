package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.AssignmentDto;
import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import org.springframework.stereotype.Component;


@Component
public class QuizQuestionMapper {

    public QuizQuestionDto toDto(QuizQuestion question) {
        return null;
    }

    public QuizQuestion toUser(QuizQuestionDto questionDto) {
        return null;
    }
}
