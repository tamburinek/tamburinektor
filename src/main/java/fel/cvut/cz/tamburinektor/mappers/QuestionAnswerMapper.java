package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.springframework.stereotype.Component;


@Component
public class QuestionAnswerMapper {

    public QuestionAnswerDto toDto(QuestionAnswer testResult) {
        return null;
    }

    public QuestionAnswer toQuestionAnswer(QuestionAnswerDto testResultDto, User user, Question question) {
        return null;
    }
}
