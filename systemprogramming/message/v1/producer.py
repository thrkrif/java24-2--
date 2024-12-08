import pika
import random
import time

def produce_temperature_data():
    # RabbitMQ 연결 설정
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()

    # Queue 선언
    channel.queue_declare(queue='temperature_data')

    try:
        while True:
            # 랜덤 온도 생성
            temperature = random.uniform(15.0, 35.0)
            message = f"{temperature:.2f}"
            # 메시지 전송
            channel.basic_publish(exchange='', routing_key='temperature_data', body=message)
            print(f"Sent: {message}")
            time.sleep(1)  # 1초 간격으로 데이터 전송
    except KeyboardInterrupt:
        print("Producer stopped.")
    finally:
        connection.close()

if __name__ == '__main__':
    produce_temperature_data()
