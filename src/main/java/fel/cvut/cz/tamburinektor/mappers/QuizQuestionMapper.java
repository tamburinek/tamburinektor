package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QuizQuestionMapper {

    public QuizQuestionDto toDto(QuizQuestion quizQuestion) {
        QuizQuestionDto dto = new QuizQuestionDto();
        dto.setQuestion(quizQuestion.getQuestion());
        dto.setRight(quizQuestion.getRightAnswer());
        List<String> wrongAnswers = quizQuestion.getWrongAnswers();
        if (wrongAnswers.get(0) != null){
            dto.setWrong1(wrongAnswers.get(0));
        }
        if (wrongAnswers.get(1) != null){
            dto.setWrong2(wrongAnswers.get(1));
        }
        if (wrongAnswers.get(2) != null){
            dto.setWrong3(wrongAnswers.get(2));
        }
        return dto;
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
