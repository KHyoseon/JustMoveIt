from socket import *
from websocket import create_connection

# react
def sendmessage(data):
    try:
        # 연결
<<<<<<< HEAD
        ws = create_connection("wss://i7d207.p.ssafy.io/ws/socket")
=======
        ws = create_connection("wss://i7d207.p.ssafy.io/ws/socket",verify=False)
>>>>>>> feature/IoT/fix-socket
        connected = ws.recv()
        # 연결 되었으면
        if connected == 'all_connected':
            ws.send('request')  # 값 요청
            ret = ws.recv()
            print(ret)
            # ws.close()

    except Exception as e:
        print(e)

if __name__ == "__main__":
    sendmessage("vscode!!!")