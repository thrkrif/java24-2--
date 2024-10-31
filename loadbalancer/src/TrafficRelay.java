import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

class TrafficRelay implements Runnable {
    private Socket clientSocket;
    private Server server;

    public TrafficRelay(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        if ("tcp".equalsIgnoreCase(server.getProtocol())) {
            handleTcpTraffic();
        } else if ("udp".equalsIgnoreCase(server.getProtocol())) {
            handleUdpTraffic();
        }
    }

    private void handleTcpTraffic() {
        try (InputStream clientIn = clientSocket.getInputStream();
             OutputStream clientOut = clientSocket.getOutputStream();
             Socket serverSocket = new Socket(server.getHost(), server.getPort());
             InputStream serverIn = serverSocket.getInputStream();
             OutputStream serverOut = serverSocket.getOutputStream()) {

            new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = clientIn.read(buffer)) != -1) {
                        serverOut.write(buffer, 0, bytesRead);
                        serverOut.flush();
                    }
                } catch (IOException ignored) {}
            }).start();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = serverIn.read(buffer)) != -1) {
                clientOut.write(buffer, 0, bytesRead);
                clientOut.flush();
            }

        } catch (IOException e) {
            System.err.println("TCP 트래픽 릴레이 중 오류 발생: " + e.getMessage());
        }
    }

    private void handleUdpTraffic() {
        try (DatagramSocket clientDatagramSocket = new DatagramSocket(clientSocket.getPort())) {
            // 클라이언트로부터 UDP 패킷 수신
            byte[] buffer = new byte[1024];
            DatagramPacket clientPacket = new DatagramPacket(buffer, buffer.length);
            clientDatagramSocket.receive(clientPacket);
    
            // 서버로 UDP 패킷 송신
            InetAddress serverAddress = InetAddress.getByName(server.getHost());
            DatagramPacket serverPacket = new DatagramPacket(clientPacket.getData(), clientPacket.getLength(), serverAddress, server.getPort());
            try (DatagramSocket serverDatagramSocket = new DatagramSocket()) {
                serverDatagramSocket.send(serverPacket);
            }
    
            // 서버의 응답 패킷 수신
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024], 1024);
            try (DatagramSocket responseSocket = new DatagramSocket()) {
                responseSocket.receive(responsePacket);
            }
    
            // 클라이언트에게 응답 송신
            DatagramPacket clientResponsePacket = new DatagramPacket(responsePacket.getData(), responsePacket.getLength(), clientPacket.getAddress(), clientPacket.getPort());
            clientDatagramSocket.send(clientResponsePacket);
    
        } catch (IOException e) {
            System.err.println("UDP 트래픽 릴레이 중 오류 발생: " + e.getMessage());
        }
    }
}
