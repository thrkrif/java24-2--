import json
import sys
import time
from flask import Flask, request, jsonify

app = Flask(__name__)

local_data = []  # Local Storage의 데이터 저장소

# 포트 번호 기본값 (storage1, storage2, storage3에 각각 포트를 지정)
PORTS = {
    "storage1": 8001,
    "storage2": 8002,
    "storage3": 8003,
}

def timestamp():
    return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

@app.route('/initialize', methods=['POST'])
def initialize_data():
    """Primary로부터 초기 데이터 동기화"""
    global local_data
    local_data = request.json
    print(f"[{timestamp()}] LOCAL_STORAGE [SYNC] Initialized with data: {local_data}")
    return jsonify({"msg": "OK"})

@app.route('/backup', methods=['POST', 'PUT', 'PATCH', 'DELETE'])
def backup_request():
    """Primary로부터 동기화 요청 처리"""
    global local_data
    method = request.method
    if method == 'POST':
        note = request.json
        if not note or not isinstance(note, dict) or "id" not in note or "name" not in note:
            return jsonify({"msg": "Invalid data format"}), 400  # 잘못된 형식일 경우 400 Bad Request 응답
        local_data.append(note)
    elif method in ['PUT', 'PATCH']:
        note_id = int(request.path.split('/')[-1])
        note = next((n for n in local_data if n["id"] == note_id), None)
        if note:
            note.update(request.json)  # PUT/PATCH 시 데이터 업데이트
        else:
            return jsonify({"msg": "Note not found"}), 404
    elif method == 'DELETE':
        note_id = int(request.path.split('/')[-1])
        local_data = [n for n in local_data if n["id"] != note_id]  # 해당 ID를 가진 항목 제거

    print(f"[{timestamp()}] LOCAL_STORAGE [REPLY] {method} completed")
    return jsonify({"msg": "OK"})

@app.route('/notes', methods=['GET', 'POST'])
def notes():
    """GET 및 POST 요청 처리"""
    if request.method == 'GET':
        return jsonify(local_data)
    elif request.method == 'POST':
        note = request.json
        if not note or "id" not in note or "name" not in note:
            return jsonify({"msg": "Invalid data format"}), 400  # 잘못된 형식일 경우
        local_data.append(note)
        return jsonify({"msg": "Note added successfully!"}), 201

@app.route('/notes/<int:id>', methods=['GET', 'PUT', 'DELETE'])
def note_detail(id):
    """특정 노트에 대한 GET, PUT, DELETE 요청 처리"""
    note = next((n for n in local_data if n["id"] == id), None)
    if not note:
        return jsonify({"msg": "Note not found"}), 404

    if request.method == 'GET':
        return jsonify(note)
    elif request.method == 'PUT':
        note.update(request.json)
        return jsonify({"msg": "Note updated successfully!"})
    elif request.method == 'DELETE':
        local_data.remove(note)
        return jsonify({"msg": "Note deleted successfully!"})

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 local_storage.py [STORAGE_NAME] [PRIMARY_IP]")
        sys.exit(1)
    storage_name = sys.argv[1]
    primary_ip = sys.argv[2]

    # storage_name에 따른 포트 번호를 가져오기
    if storage_name not in PORTS:
        print(f"Error: Invalid storage name '{storage_name}'. Choose from: {', '.join(PORTS.keys())}")
        sys.exit(1)
    
    port = PORTS[storage_name]

    print(f"[{timestamp()}] LOCAL_STORAGE {storage_name} starting... connecting to primary at {primary_ip}:{port}")
    
    # Flask 서버 실행
    app.run(host="0.0.0.0", port=port)
