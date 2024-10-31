import java.io.IOException;
import java.net.*;
import java.util.*;

public class LoadBalancer {
    private Map<Integer, List<Server>> serverRegistry = new HashMap<>(); // 포트별로 서버 리스트 관리
    private int currentIndex = 0; // 라운드 로빈을 위한 인덱스
    private Map<Socket, Server> sessionTable = new HashMap<>(); // 세션 테이블과 서버 리스트를 관리

    // 서버 등록 메서드
    public synchronized void registerServer(String type, String protocol, String host, int port) {
        Server server = new Server(type, protocol, host, port);
        if (isServerAvailable(host, port)) {
            serverRegistry.computeIfAbsent(port, k -> new ArrayList<>()).add(server);
            System.out.println("서버 등록: " + type + " [" + protocol.toUpperCase() + "] " + host + ":" + port);
        } else {
            System.out.println("서버가 연결할 수 없습니다: " + host + ":" + port);
        }
    }

    // 서버 해제 메서드
    public synchronized void unregisterServer(String host, int port) {
        List<Server> servers = serverRegistry.get(port);
        if (servers != null) {
            servers.removeIf(server -> server.getHost().equals(host) && server.getPort() == port);
            if (servers.isEmpty()) {
                serverRegistry.remove(port); // 포트에 남아있는 서버가 없으면 제거
                System.out.println("포트 " + port + "의 모든 서버 해제, 'down' 상태로 설정");
            } else {
                System.out.println("서버 해제: " + host + ":" + port);
            }
        }
    }

    public Map<Integer, List<Server>> getServerRegistry() {
        return serverRegistry;
    }

    // 라운드 로빈 방식으로 서버 선택
    private synchronized Server getNextServer(int port) {
        List<Server> servers = serverRegistry.get(port);
        if (servers == null || servers.isEmpty()) {
            System.out.println("활성 서버가 없습니다.");
            return null;
        }
        Server server = servers.get(currentIndex % servers.size());
        currentIndex = (currentIndex + 1) % servers.size(); // 다음 서버로 순환
        return server;
    }

    // 클라이언트 요청 처리
    public void handleClientRequest(Socket clientSocket, int port) {
        Server server = sessionTable.getOrDefault(clientSocket, getNextServer(port));
        if (server != null) {
            sessionTable.put(clientSocket, server);
            new Thread(new TrafficRelay(clientSocket, server)).start();
        } else {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("클라이언트 소켓 닫는 중 오류 발생: " + e.getMessage());
            }
        }
    }

    // 헬스 체크 메서드
    public void healthCheck() {
        for (Map.Entry<Integer, List<Server>> entry : serverRegistry.entrySet()) {
            List<Server> servers = entry.getValue();
            servers.removeIf(server -> !server.isAlive());

            if (servers.isEmpty()) {
                serverRegistry.remove(entry.getKey());
                System.out.println("포트 " + entry.getKey() + "의 모든 서버가 비활성화됨.");
            }
        }

        // 세션 테이블에서 비활성화된 서버 제거
        sessionTable.values().removeIf(server -> !server.isAlive());
        System.out.println("헬스 체크 완료.");
    }

    // 서버가 연결 가능한지 확인하는 메서드
    private boolean isServerAvailable(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1000); // 1초 타임아웃
            return true;
        } catch (IOException e) {
            return false; // 서버가 연결되지 않음
        }
    }

    // 활성 서버가 없으면 종료
    public boolean hasActiveServers() {
        return !serverRegistry.isEmpty();
    }
}
