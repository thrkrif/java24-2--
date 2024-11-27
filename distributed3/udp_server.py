import socket
import json
import sys
import time
import requests

BUFFER_SIZE = 1024

local_storage_url = None  # Local Storage URL 지정

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

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
                return {"msg": "ERROR"}
        else:
            return {"msg": "ERROR"}
    except requests.exceptions.RequestException as e:
        print(f"Error forwarding to Local Storage: {e}")
        return {"msg": "ERROR"}

def start_udp_server(local_storage_url):
    host = '127.0.0.1'
    port = 9002

    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server_socket:
        server_socket.bind((host, port))
        print(f"[{timestamp()}] UDP_SERVER Running on {host}:{port}...")
        while True:
            data, address = server_socket.recvfrom(BUFFER_SIZE)
            if data:
                data = json.loads(data.decode('utf-8'))
                method = data.get('method')
                path = data.get('path')
                response = forward_to_local_storage(method, path, data, local_storage_url)
                server_socket.sendto(json.dumps(response).encode('utf-8'), address)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 tcp_server.py [LS_IP] [LS_PORT]")
        sys.exit(1)
    app_name = sys.argv[1]
    local_storage_url = f"http://{sys.argv[1]}:{sys.argv[2]}"
    print(f"[{timestamp()}] {app_name} starting...")
    start_udp_server(local_storage_url)

