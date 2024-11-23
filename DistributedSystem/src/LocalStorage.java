import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class LocalStorage {
    private static final ConcurrentHashMap<Integer, String> localNotes = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java LocalStorage <storage name> <primary IP>");
            return;
        }

        String storageName = args[0];
        String primaryIp = args[1];
        try (Socket primarySocket = new Socket(primaryIp, 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(primarySocket.getInputStream()));
             PrintWriter out = new PrintWriter(primarySocket.getOutputStream(), true)) {

            // Initial data sync
            System.out.println("Syncing with Primary Storage...");
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(" ", 3);
                int id = Integer.parseInt(parts[1]);
                String body = parts[2];
                localNotes.put(id, body);
            }

            System.out.println("Sync complete. Local Storage ready.");
        }
    }
}
