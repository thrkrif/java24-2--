import sys
import requests
from flask import Flask, request, jsonify
import time

app = Flask(__name__)
local_storage_url = None  # Local Storage URL 지정

@app.route('/notes', methods=['GET', 'POST'])
def handle_notes():
    if request.method == 'GET':
        response = requests.get(f"{local_storage_url}/notes")
        return jsonify(response.json()), response.status_code
    elif request.method == 'POST':
        note = request.json
        response = requests.post(f"{local_storage_url}/notes", json=note)
        return jsonify(response.json()), response.status_code
    return jsonify({"msg": "ERROR"}), 400

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 api_server.py [APP_NAME] [LS_PORT]")
        sys.exit(1)
    app_name = sys.argv[1]
    local_storage_url = f"http://127.0.0.1:{sys.argv[2]}"
    print(f"[{timestamp()}] {app_name} starting...")
    app.run(host="0.0.0.0", port=8000)
