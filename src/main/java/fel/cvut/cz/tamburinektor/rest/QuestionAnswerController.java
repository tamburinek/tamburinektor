package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.mappers.QuestionAnswerMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import fel.cvut.cz.tamburinektor.service.QuestionAnswerService;
import fel.cvut.cz.tamburinektor.service.QuestionService;
import fel.cvut.cz.tamburinektor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class QuestionAnswerController {

    private final UserService userService;

    private final QuestionAnswerMapper questionAnswerMapper;

    private final QuestionService questionService;

    private final QuestionAnswerService questionAnswerService;

    @Autowired
    public QuestionAnswerController(UserService userService, QuestionAnswerMapper questionAnswerMapper, QuestionService questionService, QuestionAnswerService questionAnswerService) {
        this.userService = userService;
        this.questionAnswerMapper = questionAnswerMapper;
        this.questionService = questionService;
        this.questionAnswerService = questionAnswerService;
    }

    @PostMapping(value = "/questionanswer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAnswer(@PathVariable Long id, @RequestBody QuestionAnswerDto questionAnswerDto) {
        User user = userService.getCurrentUser();
        Question question = questionService.getById(id);
        QuestionAnswer answer = questionAnswerMapper.toQuestionAnswer(questionAnswerDto, user);
        List<QuestionAnswer> answers = question.getAnswers();
        answers.add(answer);
        questionAnswerService.createAnswer(answer);
        questionService.createQuestion(question);
        log.info("New question answer created {} for {} by {}", answer.getMessage(), question.getQuestion(), user.getUsername());
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping(value = "/questionanswer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionAnswerDto> getAllAnswers(@PathVariable Long id) {
        Question question = questionService.getById(id);
        List<QuestionAnswer> answers = question.getAnswers();
        return answers.stream().map(questionAnswerMapper::toDto).collect(Collectors.toList());
    }
}
