import grpc
import time
from datetime import datetime
import smartfarm_pb2
import smartfarm_pb2_grpc

def generate_temperature_data():
    while True:
        yield smartfarm_pb2.TemperatureData(
            temperature=round(20 + 10 * (time.time() % 2), 2),
            timestamp=datetime.now().isoformat()
        )
        time.sleep(5)

def run():
    channel = grpc.insecure_channel("localhost:50051")
    stub = smartfarm_pb2_grpc.SmartFarmStub(channel)
    response = stub.SendTemperature(generate_temperature_data())
    print(f"Server response: {response.message}")

if __name__ == "__main__":
    run()
