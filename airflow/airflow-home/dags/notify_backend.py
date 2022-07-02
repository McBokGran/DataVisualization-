import requests

def notify_backend():
    print("notifying backend")
    response = requests.get("http://192.168.68.100:8080/notification")
    print(response)