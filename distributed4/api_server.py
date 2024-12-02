import json
import requests
from flask import Flask, request, jsonify
from datetime import datetime

app = Flask(__name__)

local_storage_url = "http://127.0.0.1:8000"  # 로컬 스토리지 주소
BUFFER_SIZE = 1024

def timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

@app.route('/notes', methods=['GET', 'POST'])
def handle_notes():
    if request.method == 'GET':
        response = requests.get(f"{local_storage_url}/notes")
        return response.json(), response.status_code

    elif request.method == 'POST':
        note = request.json
        response = requests.post(f"{local_storage_url}/notes", json=note)
        return response.json(), response.status_code

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001)
