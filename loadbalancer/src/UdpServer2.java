import java.io.*;
import java.net.*;

public class UdpServer2 {
    public static void main(String[] args) throws IOException {
        int port = 8084; // 각 서버마다 다른 포트로 설정
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("UDP 서버 실행 중 (포트: " + port + ")");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    out.println("Hello from UDP server on port " + port);
                } catch (IOException e) {
                    System.out.println("클라이언트 연결 중 오류 발생: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("UDP 서버 실행 중 오류 발생: " + e.getMessage());
        }
    }
}
