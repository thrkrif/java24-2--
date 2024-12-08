from flask import Flask, jsonify, request
import pika
import threading
import json
from datetime import datetime

app = Flask(__name__)

# 데이터 파일 경로
DATA_FILE = 'db.json'

# JSON 데이터 로드
def load_data():
    try:
        with open(DATA_FILE, 'r') as f:
            return json.load(f)
    except FileNotFoundError:
        return []

# JSON 데이터 저장
def save_data(data):
    with open(DATA_FILE, 'w') as f:
        json.dump(data, f, indent=4)

# RabbitMQ 소비자: 데이터를 수신하고 저장
def process_message(body):
    data = json.loads(body)
    temperature = data['temperature']
    timestamp = data['timestamp']

    tasks = load_data()
    new_task = {
        'id': len(tasks) + 1,
        'title': f"Temperature Reading {len(tasks) + 1}",
        'description': f"Recorded temperature: {temperature}°C at {timestamp}",
        'temperature': temperature,
        'timestamp': timestamp,
        'completed': False
    }
    tasks.append(new_task)
    save_data(tasks)

    print(f"Processed and saved task: {new_task}")

def callback(ch, method, properties, body):
    process_message(body)

def consume_temperature_data():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='temperature_data')

    channel.basic_consume(queue='temperature_data', on_message_callback=callback, auto_ack=True)

    print("RabbitMQ consumer started. Waiting for messages...")
    try:
        channel.start_consuming()
    except KeyboardInterrupt:
        print("Consumer stopped.")
    finally:
        connection.close()

# RabbitMQ 소비자 쓰레드 실행
def start_consumer_thread():
    thread = threading.Thread(target=consume_temperature_data, daemon=True)
    thread.start()

# REST API Routes
@app.route('/tasks', methods=['GET'])
def get_tasks():
    tasks = load_data()
    return jsonify(tasks), 200

@app.route('/tasks/<int:task_id>', methods=['GET'])
def get_task(task_id):
    tasks = load_data()
    task = next((t for t in tasks if t['id'] == task_id), None)
    if task:
        return jsonify(task), 200
    return jsonify({'error': 'Task not found'}), 404

@app.route('/tasks', methods=['POST'])
def create_task():
    tasks = load_data()
    data = request.get_json()

    new_task = {
        'id': len(tasks) + 1,
        'title': data['title'],
        'description': data.get('description', ''),
        'completed': False
    }
    tasks.append(new_task)
    save_data(tasks)
    return jsonify(new_task), 201

@app.route('/tasks/<int:task_id>', methods=['PUT'])
def update_task(task_id):
    tasks = load_data()
    task = next((t for t in tasks if t['id'] == task_id), None)
    if not task:
        return jsonify({'error': 'Task not found'}), 404

    data = request.get_json()
    task['title'] = data.get('title', task['title'])
    task['description'] = data.get('description', task['description'])
    task['completed'] = data.get('completed', task['completed'])

    save_data(tasks)
    return jsonify(task), 200

@app.route('/tasks/<int:task_id>/complete', methods=['PATCH'])
def complete_task(task_id):
    tasks = load_data()
    task = next((t for t in tasks if t['id'] == task_id), None)
    if not task:
        return jsonify({'error': 'Task not found'}), 404

    task['completed'] = True
    save_data(tasks)
    return jsonify(task), 200

@app.route('/tasks/<int:task_id>', methods=['DELETE'])
def delete_task(task_id):
    tasks = load_data()
    task = next((t for t in tasks if t['id'] == task_id), None)
    if not task:
        return jsonify({'error': 'Task not found'}), 404

    tasks.remove(task)
    save_data(tasks)
    return jsonify({'message': 'Task deleted'}), 200

if __name__ == '__main__':
    start_consumer_thread()  # RabbitMQ 소비자 실행
    app.run(host='0.0.0.0', port=5000)
