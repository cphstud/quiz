import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuizMapperTest {
    QuizMapper quizMapper;
    ArrayList<Question> questions;

    @Before
    public void setUp() throws Exception {
        //quizMapper = new QuizMapper("src/main/resources/WTEST");
        quizMapper = QuizMapper.getInstance("src/main/resources/WTEST");
        questions = new ArrayList<>();
    }

    @Test
    public void getQuestions() {
        try {
            questions = quizMapper.getQuestions();
            for (Question q:questions) {
                System.out.println(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}