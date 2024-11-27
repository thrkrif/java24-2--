import socket
import json
import sys
import time

BUFFER_SIZE = 1024

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

def handle_request(data, address, local_storage_url, server_socket):
    try:
        request = json.loads(data)
        method = request.get('method')
        path = request.get('path')

        print(f"[{timestamp()}] UDP_SERVER Received {method} {path} from {address}")

        # Forward the request to the local storage server
        if method in ['GET', 'POST', 'PUT', 'PATCH', 'DELETE']:
            response = forward_to_local_storage(method, path, request, local_storage_url)
            server_socket.sendto(json.dumps(response).encode('utf-8'), address)
    except Exception as e:
        print(f"Error handling request: {e}")

def forward_to_local_storage(method, path, request, local_storage_url):
    import requests

    url = f"{local_storage_url}{path}"
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
        return response.json()
    except requests.exceptions.RequestException as e:
        print(f"Error forwarding to local storage: {e}")
        return {"msg": "ERROR"}

def main():
    if len(sys.argv) != 3:
        print("Usage: python3 udp_server.py [APP_NAME] [LS_PORT]")
        sys.exit(1)

    app_name = sys.argv[1]
    local_storage_url = f"http://127.0.0.1:{sys.argv[2]}"
    server_address = ('0.0.0.0', 9001)

    print(f"[{timestamp()}] {app_name} UDP Server starting on port {server_address[1]}")

    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server_socket:
        server_socket.bind(server_address)
        print(f"[{timestamp()}] UDP Server listening for messages...")

        while True:
            data, addr = server_socket.recvfrom(BUFFER_SIZE)
            handle_request(data.decode('utf-8'), addr, local_storage_url, server_socket)

if __name__ == "__main__":
    main()
