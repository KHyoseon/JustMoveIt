import socket

HEADER = 64
PORT = 8081
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "!DISCONNECT"
SERVER = "ws://i7d207.p.ssafy.io/api/socket"
ADDR = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDR)


def send(msg):
    message = msg.encode(FORMAT)
    msg_length = len(message)
    send_length = str(msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))
    client.send(send_length)
    client.send(message)
    print(client.recv(2048).decode(FORMAT))



if __name__ == "__main__":


    send("Hello World!")
    input()
    send("Hello Everyone!")
    input()
    send("Hello Tim!")

    send(DISCONNECT_MESSAGE)