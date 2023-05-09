package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.TestDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.test.Assignment;
import fel.cvut.cz.tamburinektor.model.test.Test;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TestMapper {

    public TestDto toDto(Test test) {
        TestDto testDto = new TestDto();
        testDto.setId(test.getId());
        testDto.setDescription(test.getDescription());
        testDto.setAssignments(test.getAssignments().stream().map(Assignment::getId).collect(Collectors.toList()));
        return testDto;
    }

    public Test toTest(TestDto testDto, User user, List<Assignment> assignmentList) {
        Test test = new Test();
        test.setDescription(testDto.getDescription());
        test.setCreateBy(user);
        test.setAssignments(assignmentList);
        return test;
    }
}
