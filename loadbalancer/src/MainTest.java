import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer();

        try (ServerSocket loadBalancerSocket = new ServerSocket(8080)) {
            System.out.println("로드 밸런서 실행 중...");

            while (true) {
                // 활성 서버가 없을 경우 등록을 요청
                if (loadBalancer.getServerRegistry().isEmpty()) {
                    System.out.print("서버 등록을 원하십니까? (y/n): ");
                    Scanner scanner = new Scanner(System.in);
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("y")) {
                        System.out.print("서버 유형 (TCP/UDP/API): ");
                        String type = scanner.nextLine().toLowerCase();

                        System.out.print("서버 호스트: ");
                        String host = scanner.nextLine();

                        System.out.print("서버 포트: ");
                        int port = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기

                        // 호스트 유효성 검사
                        try {
                            InetAddress.getByName(host); // 호스트를 검사하여 유효한지 확인
                        } catch (IOException e) {
                            System.out.println("유효하지 않은 호스트입니다: " + host);
                            continue;
                        }

                        // 서버 등록 요청 처리
                        loadBalancer.registerServer(type, type, host, port);
                        System.out.println("서버가 등록되었습니다: " + host + ":" + port);
                    }
                }

                // 클라이언트 요청 수신 및 처리
                Socket clientSocket = loadBalancerSocket.accept();
                loadBalancer.handleClientRequest(clientSocket, 8080); // 예시 포트 지정
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
