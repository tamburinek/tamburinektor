package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import fel.cvut.cz.tamburinektor.service.LectureEntityService;
import fel.cvut.cz.tamburinektor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class LectureEntityController {

    private final LectureEntityService lectureEntityService;

    private final UserService userService;

    @Autowired
    public LectureEntityController(LectureEntityService lectureEntityService, UserService userService) {
        this.lectureEntityService = lectureEntityService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/material/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getCountOfMaterials(){
        User user = userService.getCurrentUser();
        return lectureEntityService.getCountByUser(user);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/material/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public LectureEntity getLastEntity(){
        User user = userService.getCurrentUser();
        return lectureEntityService.getLastMaterial(user);
    }
}
