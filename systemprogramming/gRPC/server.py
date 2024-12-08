from concurrent import futures
import grpc
import json
from datetime import datetime
import smartfarm_pb2
import smartfarm_pb2_grpc

DATA_FILE = "db.json"

# 데이터 저장
def save_data(data):
    try:
        with open(DATA_FILE, "r") as f:
            db = json.load(f)
    except FileNotFoundError:
        db = []

    db.append(data)
    with open(DATA_FILE, "w") as f:
        json.dump(db, f, indent=4)

# 데이터 로드
def load_data():
    try:
        with open(DATA_FILE, "r") as f:
            return json.load(f)
    except FileNotFoundError:
        return []

# gRPC 서비스 구현
class SmartFarmServicer(smartfarm_pb2_grpc.SmartFarmServicer):
    def SendTemperature(self, request_iterator, context):
        for temp_data in request_iterator:
            # 온도 데이터를 소수점 둘째 자리로 반올림
            temperature = round(temp_data.temperature, 2)
            complete = temperature < 30.0  # 30도 미만이면 True, 30도 이상이면 False

            # 데이터 구성
            data = {
                "temperature": temperature,
                "timestamp": temp_data.timestamp,
                "complete": complete  # 추가된 필드
            }

            # 30도 초과 시 경고 출력
            if not complete:
                print(f"Warning: High temperature detected: {temperature}°C at {data['timestamp']}")

            print(f"Received: {data}")
            save_data(data)

        return smartfarm_pb2.Response(message="Data received successfully")

    def GetTemperature(self, request, context):
        data = load_data()
        for entry in data:
            yield smartfarm_pb2.TemperatureData(
                temperature=entry["temperature"],
                timestamp=entry["timestamp"]
            )

# 서버 실행
def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    smartfarm_pb2_grpc.add_SmartFarmServicer_to_server(SmartFarmServicer(), server)
    server.add_insecure_port("[::]:50051")
    print("Server started on port 50051")
    server.start()
    server.wait_for_termination()

if __name__ == "__main__":
    serve()
