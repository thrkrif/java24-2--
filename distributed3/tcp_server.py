import socket
import json
import sys
import time
import requests

BUFFER_SIZE = 1024

local_storage_url = None  # Local Storage URL 지정

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

def handle_request(connection, address, local_storage_url):
    try:
        data = connection.recv(BUFFER_SIZE).decode('utf-8')
        if not data:
            return
        request = json.loads(data)
        method = request.get('method')
        path = request.get('path')

        print(f"[{timestamp()}] TCP_SERVER Received {method} {path} from {address}")

        if method in ['GET', 'POST', 'PUT', 'PATCH', 'DELETE']:
            response = forward_to_local_storage(method, path, request, local_storage_url)
            if response:
                response_data = json.dumps(response)
                connection.send(response_data.encode('utf-8'))
            else:
                print(f"[{timestamp()}] ERROR: No valid response from local storage")
                connection.send(json.dumps({"msg": "ERROR"}).encode('utf-8'))
    except Exception as e:
        print(f"Error handling request: {e}")
        connection.send(json.dumps({"msg": "ERROR"}).encode('utf-8'))
    finally:
        connection.close()

def forward_to_local_storage(method, path, request, local_storage_url):
    url = f"{local_storage_url}/notes"
    print(f"[{timestamp()}] Forwarding request to Local Storage: {url}") 
    data = request.get('data', {})

    try:
        if method == 'GET':
            response = requests.get(url)
        elif method == 'POST':
            response = requests.post(url, json=data)
        elif method == 'PUT':
            response = requests.put(url, json=data)
        elif method == 'PATCH':
            response = requests.patch(url, json=data)
        elif method == 'DELETE':
            response = requests.delete(url)
        else:
            return {"msg": "ERROR"}

        if response.status_code == 200:
            try:
                return response.json()
            except ValueError:
                print(f"[{timestamp()}] ERROR: Response is not valid JSON")
                return {"msg": "ERROR"}
        else:
            print(f"[{timestamp()}] ERROR: Invalid response from local storage")
            return {"msg": "ERROR"}

    except requests.exceptions.RequestException as e:
        print(f"[{timestamp()}] ERROR: Failed to forward request to local storage: {e}")
        return {"msg": "ERROR"}

def start_tcp_server(local_storage_url):
    host = '127.0.0.1'
    port = 9001
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((host, port))
        server_socket.listen(5)
        print(f"[{timestamp()}] TCP_SERVER Running on {host}:{port}...")
        while True:
            connection, address = server_socket.accept()
            handle_request(connection, address, local_storage_url)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 tcp_server.py [LS_IP] [LS_PORT]")
        sys.exit(1)
    app_name = sys.argv[1]
    local_storage_url = f"http://{sys.argv[1]}:{sys.argv[2]}"
    print(f"[{timestamp()}] {app_name} starting...")
    start_tcp_server(local_storage_url)
   
