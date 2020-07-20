import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    int maxClients;
    int clientCounter = 0;
    List<Player> players;

    public PlayerController(int maxClients) {
        this.maxClients = maxClients;
        this.players = new ArrayList<>();
    }

    public void runProgram() throws IOException, InterruptedException {
        Quiz q = new Quiz();
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
            p.start();
        }
        Thread.sleep(5000);
        for (Player p: players ) {
            System.out.println("ans: " );
            p.getAnswers();
        }
    }
}
