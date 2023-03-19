package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.service.TaskService;
import fel.cvut.cz.tamburinektor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
}
