package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.DTO.QuestionDto;
import fel.cvut.cz.tamburinektor.mappers.QuestionMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
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
public class QuestionController {

    private final QuestionService questionService;

    private final UserService userService;

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionController(QuestionService questionService, UserService userService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.userService = userService;
        this.questionMapper = questionMapper;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/question", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createImage(@RequestBody QuestionDto questionDto) {
        User user = userService.getCurrentUser();
        Question question = questionMapper.toQuestion(questionDto, user);
        questionService.createQuestion(question);
        log.info("New question created {} by {}", question.getQuestion(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/question/{id}", question.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionDto> getAllQuestionsByUser(){
        User user = userService.getCurrentUser();
        List<Question> questions = questionService.getAllByUser(user);
        return questions.stream().map(questionMapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/question/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto getQuestionById(@PathVariable Long id){
        Question question = questionService.getById(id);
        return questionMapper.toDto(question);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/question/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto dto){
        Question question = questionService.getById(id);
        question.setQuestion(dto.getQuestionText());
        question.setAnonymous(dto.isAnonymous());
        questionService.createQuestion(question);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
