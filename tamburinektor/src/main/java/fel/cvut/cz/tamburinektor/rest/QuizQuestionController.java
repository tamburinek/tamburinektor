package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.service.AssignmentService;
import fel.cvut.cz.tamburinektor.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class QuizQuestionController {

    private final QuizQuestionService questionService;

    @Autowired
    public QuizQuestionController(QuizQuestionService questionService) {
        this.questionService = questionService;
    }
}
