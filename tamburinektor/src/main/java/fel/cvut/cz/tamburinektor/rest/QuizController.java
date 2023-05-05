package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuizDto;
import fel.cvut.cz.tamburinektor.mappers.QuizMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import fel.cvut.cz.tamburinektor.service.QuizService;
import fel.cvut.cz.tamburinektor.service.UserService;
import fel.cvut.cz.tamburinektor.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class QuizController {

    private final QuizService quizService;

    private final QuizMapper quizMapper;

    private final UserService userService;

    @Autowired
    public QuizController(QuizService quizService, QuizMapper quizMapper, UserService userService) {
        this.quizService = quizService;
        this.quizMapper = quizMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/quiz", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> createQuiz(@RequestBody QuizDto quizDto){
        User user = userService.getCurrentUser();
        Quiz quiz = quizMapper.toQuiz(quizDto, user);
        quizService.createQuiz(quiz);
        log.info("New quiz created {} by {}", quiz.getName(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/quiz/{id}", quiz.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
