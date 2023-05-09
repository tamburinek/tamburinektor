package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GraphService {

    private final GraphRepository graphRepository;

    @Autowired
    public GraphService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }
}
