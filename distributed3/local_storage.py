import sys
import json
from flask import Flask, request, jsonify
from datetime import datetime
import time

app = Flask(__name__)

memo_data = []  # 메모 데이터를 저장할 리스트
memo_id_counter = 1  # ID 순차 증가를 위한 카운터

# 포트 번호 기본값 (storage1, storage2, storage3에 각각 포트를 지정)
PORTS = {
    "storage1": 8000,
    "storage2": 8001,
    "storage3": 8002,
}

def timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

@app.route('/notes', methods=['GET', 'POST'])
def handle_notes():
    global memo_id_counter
    if request.method == 'GET':
        # 전체 메모 리스트 반환
        return jsonify(memo_data), 200

    elif request.method == 'POST':
        note = request.json
        if not note or "title" not in note or "body" not in note:
            return jsonify({"msg": "Missing required fields: title, body"}), 400  # 잘못된 데이터 형식

        note["id"] = memo_id_counter
        memo_id_counter += 1
        memo_data.append(note)

        return jsonify(note), 201

    return jsonify({"msg": "ERROR"}), 400

@app.route('/notes/<int:id>', methods=['GET', 'PUT', 'PATCH', 'DELETE'])
def handle_note_by_id(id):
    global memo_data
    note = next((n for n in memo_data if n["id"] == id), None)
    if not note:
        return jsonify({"msg": "NO DATA"}), 404  # 메모가 존재하지 않는 경우

    if request.method == 'GET':
        return jsonify(note), 200

    elif request.method == 'PUT':
        new_data = request.json
        if "title" in new_data:
            note["title"] = new_data["title"]
        if "body" in new_data:
            note["body"] = new_data["body"]
        return jsonify(note), 200

    elif request.method == 'PATCH':
        new_data = request.json
        if "title" in new_data:
            note["title"] = new_data["title"]
        if "body" in new_data:
            note["body"] = new_data["body"]
        return jsonify(note), 200

    elif request.method == 'DELETE':
        memo_data = [n for n in memo_data if n["id"] != id]
        return jsonify({"msg": "OK"}), 200

    return jsonify({"msg": "ERROR"}), 400


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
