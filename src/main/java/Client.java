import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8105;
        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                String questionFromServer = in.readLine();
                if (questionFromServer.contains("Connection closed")) {
                    System.out.println(questionFromServer.replace("Connection closed", ""));
                    break;
                }
                System.out.println(questionFromServer);
                String answer = scanner.nextLine();
                out.println(answer);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
