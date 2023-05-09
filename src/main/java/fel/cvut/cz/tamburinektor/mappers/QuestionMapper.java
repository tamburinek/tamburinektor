package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuestionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import org.springframework.stereotype.Component;


@Component
public class QuestionMapper {

    public QuestionDto toDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestion());
        dto.setAnonymous(question.isAnonymous());
        dto.setLectureType(question.getType().getName());
        return dto;
    }

    public Question toQuestion(QuestionDto questionDto, User user) {
        Question question = new Question();
        question.setQuestion(questionDto.getQuestionText());
        question.setAnonymous(questionDto.isAnonymous());
        question.setType(LectureType.QUESTION);
        question.setCreatedBy(user);
        return question;
    }
}
