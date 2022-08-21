import socket

HOST = '127.0.0.1'
PORT = 9999

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((HOST, PORT))

for i in range(1, 10):
    msg = 'java hello message'

    data = msg.encode()
    length = len(data)
    client_socket.sendall(length.to_bytes(4, byteorder="little"))
    client_socket.sendall(data)

    data = client_socket.recv(4)
    length = int.from_bytes(data, "big")
    data = client_socket.recv(length)
    msg = data.decode()
    print("Received from : ", msg)
client_socket.close()