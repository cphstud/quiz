import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class PlayerController {

    static ArrayList<Question> allQuestionsToBeAsked = new ArrayList<>();
    //static ArrayList<Question> firstRound = new ArrayList<>();
    //static ArrayList<Question> secRound = new ArrayList<>();
    int maxClients;
    int clientCounter = 0;
    List<Player> players;
    QuizMapper quizMapper;

    public PlayerController(int maxClients) {
        this.maxClients = maxClients;
        this.players = new ArrayList<>();
    }

    public void runProgram() throws IOException, InterruptedException {
        quizMapper = QuizMapper.getInstance("src/main/resources/WTEST");
        List<Thread> threads = new ArrayList<>();

        Player player = null;

        //Quiz q = new Quiz();

        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Waiting for clients");
        while(clientCounter < maxClients) {
            clientCounter++;
            Socket s = serverSocket.accept();
            player = new Player(s);
            player.setQuestions(allQuestionsToBeAsked);
            players.add(player);
        }
        ArrayList<Question> firstRound = allQuestionsToBeAsked;
        ArrayList<Question> allQuestions = new ArrayList<>();
        allQuestions = quizMapper.getQuestions();
        for (int i = 0; i < (allQuestions.size()/2) ; i++) {
            firstRound.add(allQuestions.get(i));
        }
        System.out.println("clients added ...");
        for (Player p: players ) {
            p.setName();
            Thread t = new Thread(p);
            t.setName(p.getName());
            t.start();
            threads.add(t);
        }
        System.out.println("joining .." + System.currentTimeMillis());
        for(Thread t: threads) t.join(10000);

        ArrayList<Question> secRound = allQuestionsToBeAsked;
        for (int i = 0; i < (allQuestions.size()/2) ; i++) {
            secRound.add(allQuestions.get((allQuestions.size()-1)-i));
        }
        System.out.println("F: " + firstRound);
        System.out.println("S: " + secRound);
        //secRound = firstRound;
        //firstRound = secRound;
        System.out.println("leaving .." + System.currentTimeMillis());
        System.out.println("F: " + firstRound);
        System.out.println("F: " + firstRound.hashCode());
        System.out.println("S: " + secRound.hashCode());
        Map<Integer, String> tmpAnsMap = new HashMap<>();
        String[] tmpAns = new String[quizMapper.getQuestions().size()];
        for (Player p: players ) {
            System.out.println("ans: " );
            tmpAns = p.getAnswers();
            /*
            Set<Integer> keys = tmpAnsMap.keySet();
            for(Integer i: keys) {
                System.out.println(p.getName() + " has ans to " + i + " is: " + tmpAnsMap.get(i) );
            }
             */
            for (int i = 0; i < tmpAns.length; i++) {
                System.out.println("Ans: " + tmpAns[i]);
            }
        }
    }
}
