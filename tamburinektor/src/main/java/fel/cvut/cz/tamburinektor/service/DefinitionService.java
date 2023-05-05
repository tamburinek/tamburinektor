package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.DefinitionRepository;
import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Definition> getAllByUser(User user) {
        return definitionRepository.getAllByCreatedBy(user);
    }


    public Definition getById(Long id) {
        return definitionRepository.getDefinitionById(id);
    }
}
