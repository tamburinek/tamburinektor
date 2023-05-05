package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class QuizMapper {

    private final QuizQuestionMapper quizQuestionMapper;


    public QuizMapper(QuizQuestionMapper quizQuestionMapper) {
        this.quizQuestionMapper = quizQuestionMapper;
    }


    public QuizDto toDto(Quiz quiz) {
        return null;
    }

    public Quiz toQuiz(QuizDto quizDto, User user) {
        Quiz quiz = new Quiz();
        quiz.setName(quiz.getName());
        quiz.setType(LectureType.QUIZ);
        quiz.setQuestions(quizDto.getQuestions().stream()
                .map(quizQuestionMapper::toQuestion)
                .collect(Collectors.toList()));
        quiz.setCreatedBy(user);
        return quiz;
    }
}
