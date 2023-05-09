package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.QuizDto;
import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class QuizMapper {

    private final QuizQuestionMapper quizQuestionMapper;


    public QuizMapper(QuizQuestionMapper quizQuestionMapper) {
        this.quizQuestionMapper = quizQuestionMapper;
    }


    public QuizDto toDto(Quiz quiz) {
        List<QuizQuestionDto> questions = quiz.getQuestions().stream()
                .map((quizQuestionMapper::toDto)).collect(Collectors.toList());
        QuizDto dto = new QuizDto();
        dto.setName(quiz.getName());
        dto.setQuestions(questions);
        dto.setId(quiz.getId());
        dto.setLectureType(LectureType.QUIZ.getName());
        return dto;
    }

    public Quiz toQuiz(QuizDto quizDto, User user) {
        Quiz quiz = new Quiz();
        quiz.setName(quizDto.getName());
        quiz.setType(LectureType.QUIZ);
        quiz.setQuestions(quizDto.getQuestions().stream()
                .map(quizQuestionMapper::toQuestion)
                .collect(Collectors.toList()));
        quiz.setCreatedBy(user);
        return quiz;
    }
}
