package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.mappers.QuestionAnswerMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import fel.cvut.cz.tamburinektor.service.QuestionAnswerService;
import fel.cvut.cz.tamburinektor.service.QuestionService;
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
public class QuestionAnswerController {

    private final UserService userService;

    private final QuestionAnswerService questionAnaswerService;

    private final QuestionAnswerMapper questionAnswerMapper;

    private final QuestionService questionService;

    @Autowired
    public QuestionAnswerController(UserService userService, QuestionAnswerService questionAnaswerService, QuestionAnswerMapper questionAnswerMapper, QuestionService questionService) {
        this.userService = userService;
        this.questionAnaswerService = questionAnaswerService;
        this.questionAnswerMapper = questionAnswerMapper;
        this.questionService = questionService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/questionanswer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDefinition(@RequestBody QuestionAnswerDto questionAnswerDto) {
        User user = userService.getCurrentUser();
        Question question = questionService.getById(questionAnswerDto.getId());
        QuestionAnswer answer = questionAnswerMapper.toQuestionAnswer(questionAnswerDto, user, question);
        questionAnaswerService.createAnswer(answer);
        log.info("New question answer created {} for {} by {}", answer.getMessage(), question.getQuestion(), user.getUsername());
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
