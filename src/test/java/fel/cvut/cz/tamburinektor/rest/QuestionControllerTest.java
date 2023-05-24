package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuestionDto;
import fel.cvut.cz.tamburinektor.mappers.QuestionMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Question;
import fel.cvut.cz.tamburinektor.model.lecture.QuestionAnswer;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService mockQuestionService;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private QuestionMapper mockQuestionMapper;


    @Test
    void testCreateImage() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure QuestionMapper.toQuestion(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        when(mockQuestionMapper.toQuestion(any(QuestionDto.class), any(User.class))).thenReturn(question);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/question")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQuestionService).createQuestion(any(Question.class));
    }


    @Test
    void testGetAllQuestionsByUser() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure QuestionService.getAllByUser(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        final List<Question> questions = List.of(question);
        when(mockQuestionService.getAllByUser(any(User.class))).thenReturn(questions);

        // Configure QuestionMapper.toDto(...).
        final QuestionDto dto = new QuestionDto();
        dto.setId(0L);
        dto.setQuestionText("question");
        dto.setAnonymous(false);
        dto.setLectureType("lectureType");
        when(mockQuestionMapper.toDto(any(Question.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/question")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllQuestionsByUser_QuestionServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockQuestionService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/question")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetQuestionById() throws Exception {
        // Setup
        // Configure QuestionService.getById(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        when(mockQuestionService.getById(0L)).thenReturn(question);

        // Configure QuestionMapper.toDto(...).
        final QuestionDto dto = new QuestionDto();
        dto.setId(0L);
        dto.setQuestionText("question");
        dto.setAnonymous(false);
        dto.setLectureType("lectureType");
        when(mockQuestionMapper.toDto(any(Question.class))).thenReturn(dto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/question/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateQuestion() throws Exception {
        // Setup
        // Configure QuestionService.getById(...).
        final Question question = new Question();
        question.setId(0L);
        question.setQuestion("question");
        question.setAnonymous(false);
        final QuestionAnswer answer = new QuestionAnswer();
        answer.setId(0L);
        question.setAnswers(List.of(answer));
        when(mockQuestionService.getById(0L)).thenReturn(question);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/question/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQuestionService).createQuestion(any(Question.class));
    }
}
