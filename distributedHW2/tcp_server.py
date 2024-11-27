import socket
import sys
import json

# 메모를 저장할 리스트
notes = []
next_id = 1

def handle_request(data):
    global next_id, notes

    try:
        request = json.loads(data)
        method = request.get('method')
        path = request.get('path')

        if method == 'GET':
            if path == '/notes':
                return json.dumps(notes), 200
            elif path.startswith('/notes/'):
                note_id = int(path.split('/')[-1])
                note = next((note for note in notes if note['id'] == note_id), None)
                if note:
                    return json.dumps(note), 200
                else:
                    return json.dumps({"msg": "ERROR"}), 404

        elif method == 'POST' and path == '/sync':
            sync_data = request.get('data', {})
            if sync_data:
                notes.append(sync_data)
                return json.dumps({"msg": "OK"}), 200
            else:
                return json.dumps({"msg": "ERROR"}), 400

        return json.dumps({"msg": "Invalid method or path"}), 400

    except Exception as e:
        return json.dumps({"msg": "ERROR", "error": str(e)}), 500

def start_tcp_server(app_name, ls_port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('0.0.0.0', ls_port))
    server_socket.listen(5)
    print(f"TCP Server for {app_name} listening on port {ls_port}...")

    while True:
        client_socket, client_address = server_socket.accept()
        print(f"Connection from {client_address}")

        data = client_socket.recv(1024)
        if data:
            print(f"Received data: {data.decode()}")

            response_data, status_code = handle_request(data.decode())
            response = f"HTTP/1.1 {status_code} OK\r\nContent-Type: application/json\r\n\r\n{response_data}"
            client_socket.sendall(response.encode())

        client_socket.close()

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 tcp_server.py [app_name] [ls_port]")
        sys.exit(1)

    app_name = sys.argv[1]
    ls_port = int(sys.argv[2])
    start_tcp_server(app_name, ls_port)
