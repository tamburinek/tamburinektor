package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.TaskDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Task;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setQuestion(task.getQuestion());
        dto.setAnswer(task.getAnswer());
        dto.setQuestionImage(task.getQuestionImage());
        dto.setAnswerImage(task.getAnswerImage());
        dto.setId(task.getId());
        dto.setLectureType(LectureType.TASK.getName());
        return dto;
    }

    public Task toTask(TaskDto taskDto, User user) {
        Task task = new Task();
        task.setType(LectureType.TASK);
        task.setCreatedBy(user);
        task.setQuestion(taskDto.getQuestion());
        task.setQuestionImage(taskDto.getQuestionImage());
        task.setAnswer(taskDto.getAnswer());
        task.setAnswerImage(taskDto.getAnswerImage());
        return task;
    }
}
