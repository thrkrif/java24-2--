import json
import requests
import time
from flask import Flask, request, jsonify

app = Flask(__name__)

# Backup이 처리할 데이터
notes = []

@app.route('/backup', methods=['POST'])
def handle_sync_request():
    data = request.get_json()
    timestamp = data['timestamp']
    method = data['method']
    path = data['path']
    request_data = data.get('data')

    print(f"Timestamp: {timestamp}, Method: {method}, Path: {path}, Data: {request_data}")
    
    if method == "POST" and path == "/primary":
        notes.append(request_data)
    elif method in ["PUT", "PATCH"]:
        note_id = int(path.split('/')[-1])
        for note in notes:
            if note['id'] == note_id:
                note.update(request_data)
    elif method == "DELETE":
        note_id = int(path.split('/')[-1])
        notes[:] = [note for note in notes if note['id'] != note_id]
    
    # 응답을 처리한 후 Primary 서버로 상태 전달
    response = requests.post(f'http://{data["storage_name"]}:5000/primary', json={
        'timestamp': timestamp,
        'method': method,
        'path': path,
        'data': request_data
    })
    return jsonify({'msg': 'OK'}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=6000)
