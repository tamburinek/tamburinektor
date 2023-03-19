package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.service.AssignmentService;
import fel.cvut.cz.tamburinektor.service.DefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class DefinitionController {

    private final DefinitionService definitionService;

    @Autowired
    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }
}
