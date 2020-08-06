import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class PlayerController {

    int maxClients;
    int clientCounter = 0;
    List<Player> players;

    public PlayerController(int maxClients) {
        this.maxClients = maxClients;
        this.players = new ArrayList<>();
    }

    public void runProgram() throws IOException, InterruptedException {
        List<Thread> threads = new ArrayList<>();
        //Quiz q = new Quiz();
        
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Waiting for clients");
        while(clientCounter < maxClients) {
            clientCounter++;
            Socket s = serverSocket.accept();
            Player player = new Player(s,q.getQuizQuestions());
            players.add(player);
        }
        System.out.println("clients added ...");
        for (Player p: players ) {
            p.setName();
            Thread t = new Thread(p);
            t.setName(p.getName());
            t.start();
            threads.add(t);
        }
        for(Thread t: threads) t.join(10000);
        Map<Integer, String> tmpAns = new HashMap<>();
        for (Player p: players ) {
            System.out.println("ans: " );
            tmpAns = p.getAnswers();
            Set<Integer> keys = tmpAns.keySet();
            for(Integer i: keys) {
                System.out.println(p.getName() + " has ans to " + i + " is: " + tmpAns.get(i) );
            }
        }
    }
}
