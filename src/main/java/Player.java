import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player extends Thread{
    private Socket s;
    private String name;
    private PrintWriter pw;
    private BufferedReader br;
    private Map<Integer, String> questions;
    private Map<Integer, String> answers;

    private boolean isDone;
    public Player(Socket s, Map<Integer, String> questions) {
        this.s = s;
        this.questions = questions;
        this.answers = new HashMap<>();
    }

    @Override
    public void run() {
        String line = "";
        String answer = "";

        try {
            this.pw = new PrintWriter(s.getOutputStream(),true);
            this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.pw.println("What is your name?");
            name = br.readLine();
            System.out.println("Name: " + name);
            Set<Integer> keys = questions.keySet();
            for ( Integer key : keys ) {
                System.out.println(questions.get(key));
                pw.println(questions.get(key));
                answer = br.readLine();
                System.out.println(name + " answered: " + answer);
                answers.put(key,answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer,String> getAnswers() {
        return this.answers;
    }
}
