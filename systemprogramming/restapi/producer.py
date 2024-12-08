import pika
import random
import time
import json
from datetime import datetime

def produce_temperature_data():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='temperature_data')

    try:
        while True:
            temperature = random.uniform(15.0, 35.0)
            message = {
                "temperature": round(temperature, 2),
                "timestamp": datetime.now().isoformat()
            }
            channel.basic_publish(
                exchange='', 
                routing_key='temperature_data', 
                body=json.dumps(message)
            )
            print(f"Sent: {message}")
            time.sleep(1)  # 데이터 전송 속도 증가
    except KeyboardInterrupt:
        print("Producer stopped.")
    finally:
        connection.close()

if __name__ == '__main__':
    produce_temperature_data()
