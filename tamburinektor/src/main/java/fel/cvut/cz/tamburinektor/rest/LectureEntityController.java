package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.service.AssignmentService;
import fel.cvut.cz.tamburinektor.service.LectureEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class LectureEntityController {

    private final LectureEntityService lectureEntityService;

    @Autowired
    public LectureEntityController(LectureEntityService lectureEntityService) {
        this.lectureEntityService = lectureEntityService;
    }
}
