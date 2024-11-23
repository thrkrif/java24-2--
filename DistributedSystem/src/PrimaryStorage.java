import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PrimaryStorage {
    private static final int PORT = 5000;
    private static final Map<Integer, String> notes = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Primary Storage Server started on port " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String input;
                while ((input = in.readLine()) != null) {
                    String[] parts = input.split(" ", 3); // METHOD ID BODY
                    String method = parts[0];
                    int id = Integer.parseInt(parts[1]);
                    String body = parts.length > 2 ? parts[2] : null;

                    String response = switch (method) {
                        case "GET" -> notes.getOrDefault(id, "NO DATA");
                        case "POST" -> {
                            notes.putIfAbsent(id, body);
                            yield "OK";
                        }
                        case "PUT" -> {
                            notes.put(id, body);
                            yield "OK";
                        }
                        case "PATCH" -> {
                            notes.put(id, notes.getOrDefault(id, "") + body);
                            yield "OK";
                        }
                        case "DELETE" -> {
                            notes.remove(id);
                            yield "OK";
                        }
                        default -> "ERROR";
                    };

                    out.println(response);
                }
            } catch (IOException e) {
                System.err.println("Client handler error: " + e.getMessage());
            }
        }
    }
}
