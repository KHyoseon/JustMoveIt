from socket import *
from select import select
import sys
import time
from websocket import create_connection


def sendmessage(msg):

    try:
        ws = create_connection("ws://localhost:8081/api/socket")
        print("Sending")
        ws.send(msg)
        ws.close()
    except Exception as e:
        print(e)

if __name__ == "__main__":
    sendmessage('Hello')