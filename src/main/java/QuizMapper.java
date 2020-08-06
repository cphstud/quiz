import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class QuizMapper {
    private File fh;
    private ArrayList<Question> questions;

    public QuizMapper(String file) {
        //300	WORLD CAPITALS	Home to NATO & little green sprouts	Brussels
        this.fh = new File(file);
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

    private ArrayList<Question> getQuestions() throws IOException {
        return this.questions;
    }
}
