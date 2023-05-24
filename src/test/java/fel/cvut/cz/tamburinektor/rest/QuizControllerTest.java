package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.QuizDto;
import fel.cvut.cz.tamburinektor.DTO.QuizQuestionDto;
import fel.cvut.cz.tamburinektor.mappers.QuizMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Quiz;
import fel.cvut.cz.tamburinektor.model.lecture.QuizQuestion;
import fel.cvut.cz.tamburinektor.service.QuizService;
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
@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService mockQuizService;
    @MockBean
    private QuizMapper mockQuizMapper;
    @MockBean
    private UserService mockUserService;


    @Test
    void testCreateQuiz() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure QuizMapper.toQuiz(...).
        final Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quiz.setQuestions(List.of(quizQuestion));
        when(mockQuizMapper.toQuiz(any(QuizDto.class), any(User.class))).thenReturn(quiz);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/quiz")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQuizService).createQuiz(any(Quiz.class));
    }


    @Test
    void testGetAllQuizzesByUser() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        // Configure QuizService.getAllByUser(...).
        final Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quiz.setQuestions(List.of(quizQuestion));
        final List<Quiz> quizzes = List.of(quiz);
        when(mockQuizService.getAllByUser(any(User.class))).thenReturn(quizzes);

        // Configure QuizMapper.toDto(...).
        final QuizDto quizDto = new QuizDto();
        quizDto.setId(0L);
        quizDto.setName("name");
        final QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setQuestion("question");
        quizQuestionDto.setRight("right");
        quizDto.setQuestions(List.of(quizQuestionDto));
        when(mockQuizMapper.toDto(any(Quiz.class))).thenReturn(quizDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/quiz")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testGetAllQuizzesByUser_QuizServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getCurrentUser(...).
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(mockUserService.getCurrentUser()).thenReturn(user);

        when(mockQuizService.getAllByUser(any(User.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/quiz")
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetQuizById() throws Exception {
        // Setup
        // Configure QuizService.getById(...).
        final Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quiz.setQuestions(List.of(quizQuestion));
        when(mockQuizService.getById(0L)).thenReturn(quiz);

        // Configure QuizMapper.toDto(...).
        final QuizDto quizDto = new QuizDto();
        quizDto.setId(0L);
        quizDto.setName("name");
        final QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setQuestion("question");
        quizQuestionDto.setRight("right");
        quizDto.setQuestions(List.of(quizQuestionDto));
        when(mockQuizMapper.toDto(any(Quiz.class))).thenReturn(quizDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/quiz/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateQuiz() throws Exception {
        // Setup
        // Configure QuizService.getById(...).
        final Quiz quiz = new Quiz();
        quiz.setId(0L);
        quiz.setName("name");
        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(0L);
        quizQuestion.setQuestion("question");
        quiz.setQuestions(List.of(quizQuestion));
        when(mockQuizService.getById(0L)).thenReturn(quiz);

        // Configure QuizMapper.toQuiz(...).
        final Quiz quiz1 = new Quiz();
        quiz1.setId(0L);
        quiz1.setName("name");
        final QuizQuestion quizQuestion1 = new QuizQuestion();
        quizQuestion1.setId(0L);
        quizQuestion1.setQuestion("question");
        quiz1.setQuestions(List.of(quizQuestion1));
        when(mockQuizMapper.toQuiz(any(QuizDto.class), any(User.class))).thenReturn(quiz1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/quiz/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQuizService).createQuiz(any(Quiz.class));
    }
}
