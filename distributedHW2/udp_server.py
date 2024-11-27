import socket
import sys

def start_udp_server(app_name, ls_port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind(('0.0.0.0', ls_port))
    print(f"UDP Server for {app_name} listening on port {ls_port}...")

    while True:
        data, client_address = server_socket.recvfrom(1024)
        if data:
            print(f"Received data: {data} from {client_address}")
            server_socket.sendto(b"UDP Response", client_address)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 udp_server.py [app_name] [ls_port]")
        sys.exit(1)

    app_name = sys.argv[1]
    ls_port = int(sys.argv[2])
    start_udp_server(app_name, ls_port)
