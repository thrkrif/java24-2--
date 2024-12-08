import pika
import threading
import json
from time import sleep
from datetime import datetime
import requests  # REST API 호출을 위한 라이브러리

API_URL = "http://localhost:5000/tasks"  # REST API URL

def process_message(body):
    data = json.loads(body)
    temperature = data['temperature']
    timestamp = data['timestamp']

    print(f"[{datetime.now().isoformat()}] Processing temperature: {temperature}°C")

    # 온도 경고 처리
    if temperature > 30.0:
        print(f"Warning: High temperature detected! ({temperature}°C)")

    # REST API에 작업 저장
    task = {
        "title": f"Temperature Reading {data.get('id', 'unknown')}",
        "description": f"Recorded temperature: {temperature}°C at {timestamp}",
        "temperature": temperature,
        "timestamp": timestamp
    }
    response = requests.post(API_URL, json=task)
    if response.status_code == 201:
        saved_task = response.json()
        print(f"Processed and saved task: {saved_task}")

        # 30도 이하일 경우 complete를 true로 변경
        if temperature <= 30.0:
            task_id = saved_task['id']
            complete_url = f"{API_URL}/{task_id}/complete"
            complete_response = requests.patch(complete_url)
            if complete_response.status_code == 200:
                print(f"Task ID {task_id} marked as complete.")
            else:
                print(f"Failed to update task ID {task_id} as complete.")
    else:
        print(f"Failed to save task: {response.status_code} - {response.text}")

    # 시뮬레이션 작업: 처리 시간이 걸리는 작업 수행
    sleep(1)

def callback(ch, method, properties, body):
    thread = threading.Thread(target=process_message, args=(body,))
    thread.start()

def consume_temperature_data():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='temperature_data')

    channel.basic_consume(queue='temperature_data', on_message_callback=callback, auto_ack=True)

    print("Waiting for messages. Press Ctrl+C to stop.")
    try:
        channel.start_consuming()
    except KeyboardInterrupt:
        print("Consumer stopped.")
    finally:
        connection.close()

if __name__ == '__main__':
    consume_temperature_data()
