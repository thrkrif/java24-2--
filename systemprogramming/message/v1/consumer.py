import pika

def consume_temperature_data():
    # RabbitMQ 연결 설정
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()

    # Queue 선언 (생산자와 동일하게)
    channel.queue_declare(queue='temperature_data')

    def callback(ch, method, properties, body):
        temperature = float(body.decode())
        print(f"Received: {temperature}°C")
        # 예시: 온도가 30도 이상이면 경고 출력
        if temperature > 30.0:
            print("Warning: High temperature detected!")

    # Queue에서 메시지 소비
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
