package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.UsernameDto;
import org.springframework.stereotype.Component;


@Component
public class UsernameMapper {

    public UsernameDto toDto(String name, String surname) {
        UsernameDto dto = new UsernameDto();
        dto.setFirstName(name);
        dto.setLastName(surname);
        return dto;
    }

}
