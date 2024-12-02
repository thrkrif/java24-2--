import sys
import json
import requests
from flask import Flask, request, jsonify
from datetime import datetime

app = Flask(__name__)

# 기본적인 변수들
local_storages = []  # 등록된 로컬 스토리지 목록
data_store = []  # 프라이머리 서버의 데이터 저장소
BUFFER_SIZE = 1024  # TCP/UDP 버퍼 크기
PORTS = {
    "storage1": 8000,
    "storage2": 8001,
    "storage3": 8002,
}

def timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

@app.route('/notes', methods=['GET', 'POST'])
def handle_notes():
    global data_store
    if request.method == 'GET':
        return jsonify(data_store), 200

    elif request.method == 'POST':
        note = request.json
        if not note or "title" not in note or "body" not in note:
            return jsonify({"msg": "Missing required fields: title, body"}), 400

        note["id"] = len(data_store) + 1
        data_store.append(note)
        forward_to_replicas('POST', note)
        return jsonify(note), 201

@app.route('/replica/register', methods=['POST'])
def register_replica():
    storage_name = request.json.get("storage_name")
    if storage_name and storage_name not in local_storages:
        local_storages.append(storage_name)
        print(f"[{timestamp()}] PRIMARY_SERVER [REGISTER] Registered {storage_name}")
        return jsonify({"msg": "OK"})
    return jsonify({"msg": "ERROR"}), 400

def forward_to_replicas(method, data):
    for storage in local_storages:
        try:
            url = f"http://{storage}/notes"
            if method == 'POST':
                requests.post(url, json=data)
            elif method == 'PUT':
                requests.put(f"{url}/{data['id']}", json=data)
            elif method == 'PATCH':
                requests.patch(f"{url}/{data['id']}", json=data)
            elif method == 'DELETE':
                requests.delete(f"{url}/{data['id']}")
        except requests.exceptions.RequestException as e:
            print(f"[{timestamp()}] ERROR: Failed to forward to {storage}: {e}")

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
