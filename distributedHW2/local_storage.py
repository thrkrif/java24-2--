# # import requests
# # import sys

# # class LocalStorage:
# #     def __init__(self, storage_name, primary_ip):
# #         self.storage_name = storage_name
# #         self.primary_ip = primary_ip
# #         self.data = {}

# #     def sync_with_primary(self):
# #         # Primary Storage에서 데이터를 받아서 동기화
# #         try:
# #             response = requests.post(f'http://{self.primary_ip}:5000/sync', json={"storage_name": self.storage_name})
# #             if response.status_code == 200:
# #                 self.data = response.json().get('data', {})
# #                 print(f"Data synchronized from Primary Storage: {self.data}")
# #             else:
# #                 print("Failed to sync data.")
# #         except requests.exceptions.RequestException as e:
# #             print(f"Error syncing with Primary Storage: {e}")

# # def start_local_storage(storage_name, primary_ip):
# #     storage = LocalStorage(storage_name, primary_ip)
# #     storage.sync_with_primary()  # Primary Storage와 동기화
# #     # 로컬 스토리지 실행
# #     print(f"Local Storage {storage_name} is running.")

# # if __name__ == "__main__":
# #     if len(sys.argv) != 3:
# #         print("Usage: python3 local_storage.py [storage_name] [primary_ip]")
# #         sys.exit(1)

# #     storage_name = sys.argv[1]
# #     primary_ip = sys.argv[2]
# #     start_local_storage(storage_name, primary_ip)
# # 1. 코드 실행은 됐었음. 로컬 스토리지랑 연결하니까 404 에러가 남


# # 2번째 코드
# import requests
# import sys

# class LocalStorage:
#     def __init__(self, storage_name, primary_ip):
#         self.storage_name = storage_name
#         self.primary_ip = primary_ip
#         self.data = {}

#     def sync_with_primary(self):
#         # Primary Storage에서 데이터를 받아서 동기화
#         try:
#             response = requests.post(f'http://{self.primary_ip}:5000/sync', json={"storage_name": self.storage_name})
#             if response.status_code == 200:
#                 self.data = response.json().get('data', {})
#                 print(f"Data synchronized from Primary Storage: {self.data}")
#             else:
#                 print("Failed to sync data.")
#         except requests.exceptions.RequestException as e:
#             print(f"Error syncing with Primary Storage: {e}")

#     def register_replica(self):
#         # Primary Storage에 로컬 스토리지 등록 요청
#         try:
#             response = requests.post(f'http://{self.primary_ip}:5000/replica/register', json={"storage_name": self.storage_name})
#             if response.status_code == 200:
#                 print(f"Replica {self.storage_name} registered with Primary Storage.")
#             else:
#                 print("Failed to register replica.")
#         except requests.exceptions.RequestException as e:
#             print(f"Error registering replica: {e}")

# def start_local_storage(storage_name, primary_ip):
#     storage = LocalStorage(storage_name, primary_ip)
#     storage.register_replica()  # Primary Storage에 로컬 스토리지 등록
#     storage.sync_with_primary()  # Primary Storage와 동기화
#     # 로컬 스토리지 실행
#     print(f"Local Storage {storage_name} is running.")

# if __name__ == "__main__":
#     if len(sys.argv) != 3:
#         print("Usage: python3 local_storage.py [storage_name] [primary_ip]")
#         sys.exit(1)

#     storage_name = sys.argv[1]
#     primary_ip = sys.argv[2]
#     start_local_storage(storage_name, primary_ip)
# 프라이머리랑 실행 잘 됐음.

#3번째 코드
import requests
import sys

class LocalStorage:
    def __init__(self, storage_name, primary_ip):
        self.storage_name = storage_name
        self.primary_ip = primary_ip
        self.data = {}

    def sync_with_primary(self):
        # Primary Storage에서 데이터를 받아서 동기화
        try:
            response = requests.post(f'http://{self.primary_ip}:5000/sync', json={"storage_name": self.storage_name})
            if response.status_code == 200:
                self.data = response.json().get('data', {})
                print(f"Data synchronized from Primary Storage: {self.data}")
            else:
                print("Failed to sync data.")
        except requests.exceptions.RequestException as e:
            print(f"Error syncing with Primary Storage: {e}")

    def register_replica(self):
        # Primary Storage에 로컬 스토리지 등록 요청
        try:
            response = requests.post(f'http://{self.primary_ip}:5000/replica/register', json={"storage_name": self.storage_name})
            if response.status_code == 200:
                print(f"Replica {self.storage_name} registered with Primary Storage.")
            else:
                print("Failed to register replica.")
        except requests.exceptions.RequestException as e:
            print(f"Error registering replica: {e}")

def start_local_storage(storage_name, primary_ip):
    storage = LocalStorage(storage_name, primary_ip)
    storage.register_replica()  # Primary Storage에 로컬 스토리지 등록
    storage.sync_with_primary()  # Primary Storage와 동기화
    # 로컬 스토리지 실행
    print(f"Local Storage {storage_name} is running.")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 local_storage.py [storage_name] [primary_ip]")
        sys.exit(1)

    storage_name = sys.argv[1]
    primary_ip = sys.argv[2]
    start_local_storage(storage_name, primary_ip)
