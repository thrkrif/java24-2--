import java.io.IOException;
import java.net.Socket;

class Server {
    private String type;
    private String protocol;
    private String host;
    private int port;

    public Server(String type, String protocol, String host, int port) {
        this.type = type;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getType() {
        return type;
    }

    public String getProtocol() {
        return protocol;
    }

    public boolean isAlive() {
        try (Socket testSocket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
