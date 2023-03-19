package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.service.TestResultService;
import fel.cvut.cz.tamburinektor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class TestResultController {

    private final TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }
}
