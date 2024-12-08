import pika
import threading
import json
from time import sleep
from datetime import datetime

def process_message(body):
    data = json.loads(body)
    temperature = data['temperature']
    timestamp = data['timestamp']

    print(f"[{datetime.now().isoformat()}] Processing temperature: {temperature}°C")
    if temperature > 30.0:
        print(f"Warning: High temperature detected! ({temperature}°C) at {data['timestamp']}")
    
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
