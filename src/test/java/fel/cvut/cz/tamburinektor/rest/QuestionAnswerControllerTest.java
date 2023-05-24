package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuestionAnswerDto;
import fel.cvut.cz.tamburinektor.mappers.QuestionAnswerMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
import fel.cvut.cz.tamburinektor.service.QuestionAnswerService;
import fel.cvut.cz.tamburinektor.service.QuestionService;
import fel.cvut.cz.tamburinektor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionAnswerController.class)
class QuestionAnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;
    @MockBean
    private QuestionAnswerMapper mockQuestionAnswerMapper;
    @MockBean
    private QuestionService mockQuestionService;
    @MockBean
    private QuestionAnswerService mockQuestionAnswerService;


    @Test
    void testCreateAnswer() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure QuestionService.getById(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setMessage("message");
        question.setAnswers(List.of(answer));
        when(mockQuestionService.getById(0L)).thenReturn(question);

        // Configure QuestionAnswerMapper.toQuestionAnswer(...).
        final QuestionAnswer answer1 = new QuestionAnswer();
        answer1.setId(0L);
        answer1.setMessage("message");
        final User createdBy = new User();
        createdBy.setId(0L);
        createdBy.setUsername("username");
        answer1.setCreatedBy(createdBy);
        when(mockQuestionAnswerMapper.toQuestionAnswer(any(QuestionAnswerDto.class), any(User.class)))
                .thenReturn(answer1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/questionanswer/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQuestionAnswerService).createAnswer(any(QuestionAnswer.class));
        verify(mockQuestionService).createQuestion(any(Question.class));
    }


    @Test
    void testGetAllAnswers() throws Exception {
        // Setup
        // Configure QuestionService.getById(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setMessage("message");
        question.setAnswers(List.of(answer));
        when(mockQuestionService.getById(0L)).thenReturn(question);

        // Configure QuestionAnswerMapper.toDto(...).
        final QuestionAnswerDto dto = new QuestionAnswerDto();
        dto.setId(0L);
        dto.setQuestionId(0L);
        dto.setAnswer("answer");
        dto.setUser("user");
        when(mockQuestionAnswerMapper.toDto(any(QuestionAnswer.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/questionanswer/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
