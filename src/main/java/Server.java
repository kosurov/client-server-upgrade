import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 8105;
        ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080

        while (true) {
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("New connection accepted");

            out.println("Write your name");
            String name = in.readLine();
            out.println("Are you child? (yes/no)");
            String isChild = in.readLine();
            // Чтобы сообшить клиенту, что это последнее сообщение от сервера, это сообщение должно содержать "Connection closed"
            if (isChild.equals("yes")) {
                out.println(String.format("Welcome to the kids area, %s! Let's play!Connection closed", name));
            } else {
                out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or working day!Connection closed", name));
            }
            System.out.println("Connection closed");
        }
    }
}
