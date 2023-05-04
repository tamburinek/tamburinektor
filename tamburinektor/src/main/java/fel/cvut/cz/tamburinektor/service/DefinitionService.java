package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.DefinitionRepository;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public void createDefinition(Definition definition){
        definitionRepository.save(definition);
    }
}
