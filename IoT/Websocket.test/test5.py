

import socket
import ssl

print(ssl.OPENSSL_VERSION)

hostname = 'i7d207.p.ssafy.io' #should be matched with name in the Cert
# PROTOCOL_TLS_CLIENT requires valid cert chain and hostname
context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
ssl._create_default_https_context = ssl._create_unverified_context
context.load_verify_locations(ssl) # trusted CA (서버인증을 위하여 CA인증서 로드)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0) as sock:
    with context.wrap_socket(sock, server_hostname='i7d207.p.ssafy.io') as ssock:
        ssock.connect(('i7d207.p.ssafy.io', 443))
        ssock.send (b'hello')