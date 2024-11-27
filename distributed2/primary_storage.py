import json
from flask import Flask, request, jsonify
import time

app = Flask(__name__)

local_storages = []  # 등록된 Local Storage 목록
data_store = []  # Primary의 데이터 저장소

@app.route('/replica/register', methods=['POST'])
def register_replica():
    """로컬 스토리지 서버 등록"""
    storage_name = request.json.get("storage_name")
    if storage_name and storage_name not in local_storages:
        local_storages.append(storage_name)
        print(f"[{timestamp()}] PRIMARY_SERVER [REGISTER] Registered {storage_name}")
        return jsonify({"msg": "OK"})
    return jsonify({"msg": "ERROR"}), 400

@app.route('/primary', methods=['POST', 'PUT', 'PATCH', 'DELETE'])
def handle_primary_write():
    """Primary로의 쓰기 작업 처리"""
    global data_store
    method = request.method
    if method == 'POST':
        note = request.json
        note["id"] = len(data_store) + 1
        data_store.append(note)
        forward_to_replicas(method, note)
        return jsonify(note), 201

    elif method in ['PUT', 'PATCH', 'DELETE']:
        note_id = int(request.path.split('/')[-1])
        note = next((n for n in data_store if n["id"] == note_id), None)

        if note:
            if method == 'PUT':
                note.update(request.json)
            elif method == 'PATCH':
                note.update(request.json)
            elif method == 'DELETE':
                data_store = [n for n in data_store if n["id"] != note_id]
            forward_to_replicas(method, {"id": note_id, **request.json})
            return jsonify(note), 200

    return jsonify({"msg": "ERROR"}), 400

def forward_to_replicas(method, data):
    """Local Storage로 동기화 요청"""
    for storage in local_storages:
        print(f"[{timestamp()}] PRIMARY_SERVER [REQUEST] {method} -> {storage}")
        # 로컬 스토리지 동기화 로직 추가 (e.g., HTTP 요청)

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
