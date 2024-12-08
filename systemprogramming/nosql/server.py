import grpc
from concurrent import futures
from pymongo import MongoClient
import smartfarm_pb2
import smartfarm_pb2_grpc
from datetime import datetime

# MongoDB 연결 설정
def connect_to_db():
    client = MongoClient("mongodb://localhost:27017/")
    db = client["farm_data"]
    return db

# 데이터 삽입
def insert_temperature_data(db, temperature, timestamp, complete):
    collection = db["temperature_data"]
    data = {
        "temperature": temperature,
        "timestamp": timestamp,
        "complete": complete
    }
    collection.insert_one(data)

# gRPC 서비스 구현
class SmartFarmServicer(smartfarm_pb2_grpc.SmartFarmServicer):
    def SendTemperature(self, request_iterator, context):
        db = connect_to_db()  # MongoDB 연결
        for temp_data in request_iterator:
            # 온도 데이터를 소수점 둘째 자리로 반올림
            temperature = round(temp_data.temperature, 2)
            complete = temperature < 30.0  # 30도 미만이면 True, 30도 이상이면 False

            # 데이터 삽입
            insert_temperature_data(db, temperature, temp_data.timestamp, complete)

            # 30도 초과 시 경고 출력
            if not complete:
                print(f"Warning: High temperature detected: {temperature}°C at {temp_data.timestamp}")

            print(f"Received: {temperature}°C at {temp_data.timestamp}, Complete: {complete}")

        return smartfarm_pb2.Response(message="Data received successfully")

    def GetTemperature(self, request, context):
        db = connect_to_db()  # MongoDB 연결
        collection = db["temperature_data"]

        results = collection.find()  # 모든 데이터 조회
        for document in results:
            yield smartfarm_pb2.TemperatureData(
                temperature=document["temperature"],
                timestamp=document["timestamp"]
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
