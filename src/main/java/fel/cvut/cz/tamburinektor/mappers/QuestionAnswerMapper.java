package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import org.springframework.stereotype.Component;


@Component
public class QuestionAnswerMapper {

    public QuestionAnswerDto toDto(QuestionAnswer testResult) {
        QuestionAnswerDto dto = new QuestionAnswerDto();
        dto.setAnswer(testResult.getMessage());
        dto.setId(testResult.getId());
        dto.setUser(testResult.getCreatedBy().getFirstName() + " " + testResult.getCreatedBy().getLastName());
        return dto;
    }

    public QuestionAnswer toQuestionAnswer(QuestionAnswerDto testResultDto, User user) {
        QuestionAnswer answer = new QuestionAnswer();
        answer.setMessage(testResultDto.getAnswer());
        answer.setCreatedBy(user);
        return answer;
    }
}
