import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class QuizMapper {
    private static QuizMapper quizMapperInstance = null;
    private File fh;
    private ArrayList<Question> questions;

    private QuizMapper(String file) {
        //300	WORLD CAPITALS	Home to NATO & little green sprouts	Brussels
        fh = new File(file);
        questions = new ArrayList<>();
        try {
            populateListOfQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static QuizMapper getInstance(String file) {
        if (quizMapperInstance == null) {
            quizMapperInstance = new QuizMapper(file);
        }
        return quizMapperInstance;
    }


    private void populateListOfQuestions() throws IOException {
        String line = "";
        FileReader fr = new FileReader(fh);
        BufferedReader br = new BufferedReader(fr);
        while((line = br.readLine()) != null) {
            // regexp
            String[] lineArr = line.split("\t");
            Question q = new Question(lineArr[1],Integer.parseInt(lineArr[0]),lineArr[2],lineArr[3]);
            questions.add(q);
        }
    }

    public ArrayList<Question> getQuestions() throws IOException {
        return this.questions;
    }
}
