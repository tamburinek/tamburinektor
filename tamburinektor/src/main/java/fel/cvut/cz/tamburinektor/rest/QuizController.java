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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


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

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuizDto> getAllQuizzesByUser(){
        User user = userService.getCurrentUser();
        List<Quiz> quizzes = quizService.getAllByUser(user);
        return quizzes.stream().map(quizMapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/quiz/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public QuizDto getQuizById(@PathVariable Long id){
        Quiz quiz = quizService.getById(id);
        return quizMapper.toDto(quiz);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/quiz/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuiz(@PathVariable Long id, @RequestBody QuizDto quizDto){
        Quiz quiz = quizService.getById(id);
        Quiz old = quizMapper.toQuiz(quizDto, null);
        quiz.setName(old.getName());
        quiz.setQuestions(old.getQuestions());
        quizService.createQuiz(quiz);
        log.info("Edited quiz {}", quiz.getName());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/quiz/{id}", quiz.getId());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
