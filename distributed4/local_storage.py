import sys
import json
from flask import Flask, request, jsonify
from datetime import datetime

app = Flask(__name__)

data_store = []
BUFFER_SIZE = 1024

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
        return jsonify(note), 201

@app.route('/notes/<int:id>', methods=['GET', 'PUT', 'PATCH', 'DELETE'])
def handle_note_by_id(id):
    global data_store
    note = next((n for n in data_store if n["id"] == id), None)
    if not note:
        return jsonify({"msg": "NO DATA"}), 404

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
        data_store = [n for n in data_store if n["id"] != id]
        return jsonify({"msg": "OK"}), 200

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000)
