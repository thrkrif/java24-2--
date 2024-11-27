import socket
import json
import sys
import time

BUFFER_SIZE = 1024

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

        # Forward the request to the local storage server
        if method in ['GET', 'POST', 'PUT', 'PATCH', 'DELETE']:
            response = forward_to_local_storage(method, path, request, local_storage_url)
            if response:
                # Ensure response is a valid JSON object
                response_data = json.dumps(response)
                connection.send(response_data.encode('utf-8'))
            else:
                print(f"[{timestamp()}] TCP_SERVER ERROR: No valid response from local storage")
                connection.send(json.dumps({"msg": "ERROR"}).encode('utf-8'))
    except Exception as e:
        print(f"Error handling request: {e}")
        connection.send(json.dumps({"msg": "ERROR"}).encode('utf-8'))
    finally:
        connection.close()

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

        # Check if the response status is successful
        if response.status_code == 200:
            # Attempt to parse JSON from response
            try:
                return response.json()  # Return response in JSON format
            except ValueError:
                print(f"[{timestamp()}] ERROR: Response is not valid JSON")
                return {"msg": "ERROR"}
        else:
            print(f"[{timestamp()}] ERROR: Received non-200 status code from local storage")
            return {"msg": "ERROR"}
    except requests.exceptions.RequestException as e:
        print(f"Error forwarding to local storage: {e}")
        return {"msg": "ERROR"}

def main():
    if len(sys.argv) != 3:
        print("Usage: python3 tcp_server.py [APP_NAME] [LS_PORT]")
        sys.exit(1)

    app_name = sys.argv[1]
    local_storage_url = f"http://127.0.0.1:{sys.argv[2]}"
    server_address = ('0.0.0.0', 9000)

    print(f"[{timestamp()}] {app_name} TCP Server starting on port {server_address[1]}")

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind(server_address)
        server_socket.listen(5)
        print(f"[{timestamp()}] TCP Server listening for connections...")

        while True:
            conn, addr = server_socket.accept()
            handle_request(conn, addr, local_storage_url)

if __name__ == "__main__":
    main()
