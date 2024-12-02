import socket
import json

BUFFER_SIZE = 1024
host = '127.0.0.1'
port = 9001

def timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

def start_tcp_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((host, port))
        server_socket.listen(5)
        print(f"[{timestamp()}] TCP_SERVER Running on {host}:{port}...")
        while True:
            connection, address = server_socket.accept()
            handle_request(connection)

def handle_request(connection):
    with connection:
        data = connection.recv(BUFFER_SIZE)
        if data:
            data = json.loads(data.decode('utf-8'))
            method = data.get('method')
            path = data.get('path')
            response = forward_to_local_storage(method, path, data)
            connection.sendall(json.dumps(response).encode('utf-8'))

def forward_to_local_storage(method, path, request_data):
    url = f"http://127.0.0.1:8000/{path}"
    try:
        if method == 'GET':
            response = requests.get(url)
        elif method == 'POST':
            response = requests.post(url, json=request_data)
        return response.json()
    except requests.exceptions.RequestException as e:
        return {"msg": "ERROR"}

if __name__ == "__main__":
    start_tcp_server()
