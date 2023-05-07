package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ClassRoomRepository;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;

    @Autowired
    public ClassRoomService(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }


    public void createOrUpdateClass(Classroom classroom) {
        classRoomRepository.save(classroom);
    }

    public int getCountByUser(User user){
        return classRoomRepository.getAllByCreateBy(user).size();
    }

    public List<Classroom> getClassesCreatedBy(User user){
        return classRoomRepository.getAllByCreateBy(user);
    }


    public Classroom getLastClass(User user) {
        List<Classroom> entities = classRoomRepository.getAllByCreateBy(user);
        if (entities.isEmpty()){
            return null;
        }
        Classroom last = entities.get(0);
        for (Classroom entity : entities) {
            if (entity.getId() > last.getId()){
                last = entity;
            }
        }
        return last;
    }


    public Classroom getClassById(Long id) {
        return classRoomRepository.getById(id);
    }


    public void addLectureToClass(Classroom classroom, Lecture lecture) {
        List<Lecture> lectures = classroom.getLectures();
        lectures.add(lecture);
        classroom.setLectures(lectures);
        classRoomRepository.save(classroom);
    }


    public void removeLectureFromClass(Classroom classroom, Lecture lecture) {
        List<Lecture> lectures = classroom.getLectures().stream()
                .filter(lecture1 -> lecture1.getId() != lecture.getId()).collect(Collectors.toList());
        classroom.setLectures(lectures);
        classRoomRepository.save(classroom);
    }
}
