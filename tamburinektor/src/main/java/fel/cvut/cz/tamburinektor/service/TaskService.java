package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.dao.TaskRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getAllByUser(User user){
        return taskRepository.getAllByCreatedBy(user);
    }
}
