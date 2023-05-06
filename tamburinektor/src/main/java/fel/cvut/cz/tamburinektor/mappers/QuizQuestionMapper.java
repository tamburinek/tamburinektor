package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QuizQuestionMapper {

    public QuizQuestionDto toDto(QuizQuestion quizQuestion) {
        return null;
    }

    public QuizQuestion toQuestion(QuizQuestionDto quizQuestionDto) {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(quizQuestionDto.getQuestion());
        quizQuestion.setRightAnswer(quizQuestionDto.getRight());
        List<String> wrongAnswers = new ArrayList<>();
        wrongAnswers.add(quizQuestionDto.getWrong1());
        wrongAnswers.add(quizQuestionDto.getWrong2());
        wrongAnswers.add(quizQuestionDto.getWrong3());
        quizQuestion.setWrongAnswers(wrongAnswers);
        return quizQuestion;
    }
}
