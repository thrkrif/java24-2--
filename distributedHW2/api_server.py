# import requests
# import sys
# from flask import Flask, request, jsonify

# app = Flask(__name__)

# @app.route('/primary', methods=['POST'])
# def handle_primary():
#     data = request.get_json()
#     # 받은 데이터를 출력하는 예시
#     print(f"Received data: {data}")
#     return jsonify({'msg': 'Data received successfully.'}), 200

# def start_api_server(app_name, ls_port):
#     # 실제 Flask 서버 실행
#     print(f"API Server {app_name} is running on port {ls_port}.")
#     app.run(host='0.0.0.0', port=ls_port)

# if __name__ == "__main__":
#     if len(sys.argv) != 3:
#         print("Usage: python3 api_server.py [app_name] [ls_port]")
#         sys.exit(1)

#     app_name = sys.argv[1]
#     ls_port = int(sys.argv[2])
#     start_api_server(app_name, ls_port)

from flask import Flask, request, jsonify
import sys

app = Flask(__name__)

# 메모 목록 저장
notes = []
# ID 순차 증가 변수
next_id = 1

@app.route('/notes', methods=['POST'])
def create_note():
    global next_id
    data = request.get_json()

    # 전달된 데이터에서 id를 제외한 title과 body만 받음
    title = data.get("title")
    body = data.get("body")

    # 데이터가 없으면 에러 메시지 반환
    if not title or not body:
        return jsonify({"msg": "ERROR"}), 400

    # 새로운 메모 생성
    new_note = {
        "id": next_id,
        "title": title,
        "body": body
    }

    # 메모 목록에 추가하고, id 증가
    notes.append(new_note)
    next_id += 1

    return jsonify(new_note), 201

@app.route('/notes/<int:id>', methods=['PUT'])
def update_note(id):
    data = request.get_json()

    # id에 해당하는 메모를 찾아서 새로운 데이터로 덮어씀
    for note in notes:
        if note["id"] == id:
            note.update(data)
            return jsonify(note), 200

    return jsonify({"msg": "ERROR"}), 404

@app.route('/notes/<int:id>', methods=['PATCH'])
def patch_note(id):
    data = request.get_json()

    # id에 해당하는 메모를 찾아서 일부 필드를 수정
    for note in notes:
        if note["id"] == id:
            if "title" in data:
                note["title"] = data["title"]
            if "body" in data:
                note["body"] = data["body"]
            return jsonify(note), 200

    return jsonify({"msg": "ERROR"}), 404

@app.route('/notes/<int:id>', methods=['DELETE'])
def delete_note(id):
    global notes
    # id에 해당하는 메모를 찾아서 삭제
    notes = [note for note in notes if note["id"] != id]

    # 삭제 후 해당 ID의 메모가 존재하는지 확인
    if len(notes) == len([note for note in notes if note["id"] != id]):
        return jsonify({"msg": "ERROR"}), 404

    return jsonify({"msg": "OK"}), 200

# Flask 앱 실행 함수
def start_api_server(app_name, ls_port):
    app.run(host='0.0.0.0', port=ls_port, debug=True)

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("Usage: python3 api_server.py [app_name] [ls_port]")
        sys.exit(1)

    app_name = sys.argv[1]
    ls_port = int(sys.argv[2])

    print(f"API Server {app_name} is running on port {ls_port}.")
    start_api_server(app_name, ls_port)
