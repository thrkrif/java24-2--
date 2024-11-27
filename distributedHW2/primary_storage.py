# # from flask import Flask, request, jsonify

# # app = Flask(__name__)

# # # Primary storage에 보관된 데이터
# # data_storage = {}

# # @app.route('/primary', methods=['POST', 'GET'])
# # def handle_data():
# #     if request.method == 'GET':
# #         return jsonify(data_storage)
# #     elif request.method == 'POST':
# #         data = request.get_json()
# #         data_id = len(data_storage) + 1
# #         data['id'] = data_id
# #         data_storage[data_id] = data
# #         return jsonify(data), 201

# # @app.route('/primary/<int:id>', methods=['PUT', 'PATCH', 'DELETE'])
# # def handle_data_by_id(id):
# #     if request.method == 'PUT' or request.method == 'PATCH':
# #         data = request.get_json()
# #         if id in data_storage:
# #             data_storage[id].update(data)
# #             return jsonify(data_storage[id])
# #         else:
# #             return jsonify({'msg': 'ERROR'}), 404

# #     if request.method == 'DELETE':
# #         if id in data_storage:
# #             del data_storage[id]
# #             return jsonify({'msg': 'OK'})
# #         else:
# #             return jsonify({'msg': 'ERROR'}), 404

# # @app.route('/replica/register', methods=['POST'])
# # def register_replica():
# #     # 등록된 replica 서버들에게 데이터를 동기화하는 기능을 구현할 수 있음
# #     print(f"New replica registered")
# #     return jsonify({'msg': 'OK'}), 200

# # if __name__ == '__main__':
# #     app.run(host='0.0.0.0', port=5000)
# # 1. 실행은 됐으나 로컬 스토리지와 연결하려니 404 에러가 뜸.

# # 2번째 코드
# from flask import Flask, jsonify, request

# app = Flask(__name__)

# # Primary Storage에 보관된 데이터
# data_storage = {}

# # 로컬 스토리지 목록
# local_storages = []

# @app.route('/primary', methods=['POST', 'GET'])
# def handle_data():
#     if request.method == 'GET':
#         return jsonify(data_storage)
#     elif request.method == 'POST':
#         data = request.get_json()
#         data_id = len(data_storage) + 1
#         data['id'] = data_id
#         data_storage[data_id] = data
#         return jsonify(data), 201

# @app.route('/primary/<int:id>', methods=['PUT', 'PATCH', 'DELETE'])
# def handle_data_by_id(id):
#     if request.method == 'PUT' or request.method == 'PATCH':
#         data = request.get_json()
#         if id in data_storage:
#             data_storage[id].update(data)
#             return jsonify(data_storage[id])
#         else:
#             return jsonify({'msg': 'ERROR'}), 404

#     if request.method == 'DELETE':
#         if id in data_storage:
#             del data_storage[id]
#             return jsonify({'msg': 'OK'})
#         else:
#             return jsonify({'msg': 'ERROR'}), 404

# @app.route('/replica/register', methods=['POST'])
# def register_replica():
#     # 등록된 replica 서버들에게 데이터를 동기화하는 기능을 구현
#     data = request.get_json()
#     storage_name = data.get("storage_name")
#     if storage_name:
#         local_storages.append(storage_name)  # 로컬 스토리지 목록에 추가
#         print(f"New replica registered: {storage_name}")
#         return jsonify({'msg': 'OK'}), 200
#     return jsonify({'msg': 'ERROR'}), 400

# @app.route('/sync', methods=['POST'])
# def sync_data():
#     # 각 Local Storage에 데이터 동기화 요청
#     data = request.get_json()
#     storage_name = data.get("storage_name")
#     if storage_name:
#         print(f"Syncing data to {storage_name}")
#         return jsonify({'data': data_storage}), 200
#     return jsonify({'msg': 'ERROR'}), 400

# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port=5000)

# 3번째 코드
from flask import Flask, jsonify, request
import time
import requests

app = Flask(__name__)

# Primary Storage에 보관된 데이터
data_storage = {}

# 로컬 스토리지 목록
local_storages = []

@app.route('/primary', methods=['POST', 'GET'])
def handle_data():
    if request.method == 'GET':
        return jsonify(data_storage)
    elif request.method == 'POST':
        data = request.get_json()
        data_id = len(data_storage) + 1
        data['id'] = data_id
        data_storage[data_id] = data
        forward_to_replicas("POST", "/primary", data)  # Replica로 동기화
        return jsonify(data), 201

@app.route('/primary/<int:id>', methods=['PUT', 'PATCH', 'DELETE'])
def handle_data_by_id(id):
    if request.method == 'PUT' or request.method == 'PATCH':
        data = request.get_json()
        if id in data_storage:
            data_storage[id].update(data)
            forward_to_replicas(request.method, f"/primary/{id}", data)  # Replica로 동기화
            return jsonify(data_storage[id])
        else:
            return jsonify({'msg': 'ERROR'}), 404

    if request.method == 'DELETE':
        if id in data_storage:
            del data_storage[id]
            forward_to_replicas("DELETE", f"/primary/{id}", {})  # Replica로 동기화
            return jsonify({'msg': 'OK'})
        else:
            return jsonify({'msg': 'ERROR'}), 404

@app.route('/replica/register', methods=['POST'])
def register_replica():
    # 등록된 replica 서버들에게 데이터를 동기화하는 기능을 구현
    data = request.get_json()
    storage_name = data.get("storage_name")
    if storage_name:
        local_storages.append(storage_name)  # 로컬 스토리지 목록에 추가
        print(f"New replica registered: {storage_name}")
        return jsonify({'msg': 'OK'}), 200
    return jsonify({'msg': 'ERROR'}), 400

@app.route('/sync', methods=['POST'])
def sync_data():
    # 각 Local Storage에 데이터 동기화 요청
    data = request.get_json()
    storage_name = data.get("storage_name")
    if storage_name:
        print(f"Syncing data to {storage_name}")
        return jsonify({'data': data_storage}), 200
    return jsonify({'msg': 'ERROR'}), 400

def forward_to_replicas(method, path, data):
    timestamp = time.time()  # 타임스탬프 추가
    for storage in local_storages:
        try:
            response = requests.post(f'http://{storage}:5000/sync', json={
                'timestamp': timestamp,
                'method': method,
                'path': path,
                'data': data
            })
            if response.status_code == 200:
                print(f"Replica {storage} updated successfully.")
            else:
                print(f"Error updating Replica {storage}.")
        except requests.exceptions.RequestException as e:
            print(f"Error syncing with Replica {storage}: {e}")

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

