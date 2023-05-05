package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.springframework.stereotype.Component;


@Component
public class QuizQuestionMapper {

    public QuizQuestionDto toDto(QuizQuestion quizQuestion) {
        return null;
    }

    public QuizQuestion toQuestion(QuizQuestionDto quizQuestionDto) {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(quizQuestionDto.getQuestion());
        quizQuestion.setRightAnswer(quizQuestionDto.getRightAnswer());
        quizQuestion.setWrongAnswers(quizQuestionDto.getWrongAnswers());
        return quizQuestion;
    }
}
