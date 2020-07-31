import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player implements Runnable{
    private Long starttime = 0L;
    private Long stoptime = 0L;
    private Socket s;
    private String name;
    private PrintWriter pw;
    private BufferedReader br;
    private Map<Integer, String> questionsMap;
    private Map<Integer, String> answersMap;
    private String[] answers;
    private ArrayList<Question> questions;
    private Question[] questionsArr;
    private static ArrayList<Question> questionsStatic;
    private boolean isDone;
    private boolean simple;

    public Player(Socket s) {
        this.s = s;
        this.simple = true;
        try {
            setPrintWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setQuestions(Question[] questions) {
        this.questionsArr = questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        questionsStatic = questions;
    }

    public Player(Socket s, Map<Integer, String> questions ) {
        this.s = s;
        this.questionsMap = questions;
        this.answersMap = new HashMap<>();
        this.simple = false;
        try {
            setPrintWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPrintWriter() throws IOException {
        this.pw = new PrintWriter(s.getOutputStream(),true);
    }

    private void setReader() throws IOException {
        this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public Long getStartTime() {
        return this.starttime;
    }
    public Long getStopTime() {
        return this.stoptime;
    }

    public void answerQuestion(int questionNo) {

    }

    @Override
    public void run() {
        String line = "";
        String answer = "";
        Long startTime = System.currentTimeMillis();
        this.starttime = startTime;
        System.out.println("Q: " + questionsArr.hashCode());
        //System.out.println("Q: " + questions.hashCode());
        //System.out.println("Q: " + questionsStatic.hashCode());
        if (simple) {
            //int runTime = questionsStatic.size();
            int runTime = questionsArr.length;
            answers = new String[runTime];
            try {
                for (int i = 0; i < runTime ; i++) {
                    //pw.println(questionsStatic.get(i));
                    pw.println(questionsArr[i]);
                    answer = br.readLine();
                    System.out.println(name + " answered: " + answer);
                    answers[i] = answer;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.println("Another quiz?");
        } else {
            try {
                this.pw.println("Take the quiz?");
                answer = br.readLine();
                Set<Integer> keys = questionsMap.keySet();
                for ( Integer key : keys ) {
                    System.out.println(questions.get(key));
                    pw.println(questions.get(key));
                    answer = br.readLine();
                    System.out.println(name + " answered: " + answer);
                    answersMap.put(key,answer);
                }
                Long stopTime = System.currentTimeMillis();
                this.stoptime = stopTime;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setName() throws IOException {
        this.pw.println("What is your name?");
        name = br.readLine();
        System.out.println("Name: " + name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Map<Integer,String> getAnswersMap() {
        System.out.println("int get ..");
        return this.answersMap;
    }
    public String[] getAnswers() {
        System.out.println("int getarr ..");
        return this.answers;
    }
}
