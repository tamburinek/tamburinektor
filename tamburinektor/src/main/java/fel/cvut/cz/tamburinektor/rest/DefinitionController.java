package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.mappers.DefinitionMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import fel.cvut.cz.tamburinektor.service.DefinitionService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping
@Slf4j
public class DefinitionController {

    private final DefinitionService definitionService;

    private final DefinitionMapper definitionMapper;

    private final UserService userService;

    @Autowired
    public DefinitionController(DefinitionService definitionService, DefinitionMapper definitionMapper, UserService userService) {
        this.definitionService = definitionService;
        this.definitionMapper = definitionMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/definition", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDefinition(@RequestBody DefinitionDto definitionDto) {
        User user = userService.getCurrentUser();
        Definition definition = definitionMapper.toDefinition(definitionDto, user);
        definitionService.createDefinition(definition);
        log.info("New definition created {} by {}", definition.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/definition/{id}", definition.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/definition", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DefinitionDto> getAllDefinitionsByMe(){
        User user = userService.getCurrentUser();
        List<Definition> definitions = definitionService.getAllByUser(user);
        return definitions.stream().map(definitionMapper::toDto).collect(Collectors.toList());
    }
}
