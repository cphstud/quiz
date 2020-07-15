import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int maxClients = 2;
        int clientCounter = 0;
        PlayerController playerController = new PlayerController(maxClients);
        try {
            playerController.runProgram();

        } catch (IOException | InterruptedException e) {
            e.getStackTrace();
        }
    }
}
