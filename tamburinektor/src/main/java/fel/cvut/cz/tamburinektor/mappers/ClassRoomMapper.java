package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.ClassRoomDto;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.stereotype.Component;


@Component
public class ClassRoomMapper {

    public ClassRoomDto toDto(Classroom classroom) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setName(classroom.getName());
        dto.setPassword(classroom.getPassword());
        dto.setId(classroom.getId());
        return dto;
    }

    public Classroom toClassRoom(ClassRoomDto dto, User user) {
        Classroom classroom = new Classroom();
        classroom.setName(dto.getName());
        classroom.setPassword(dto.getPassword());
        classroom.setCreateBy(user);
        return classroom;
    }
}
