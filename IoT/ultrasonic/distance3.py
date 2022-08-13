import RPi.GPIO as gpio
import time
from socket import *
from select import select
from websocket import create_connection

# Spring에 신호를 보내는 함수 정의
def send_distance(msg):

    try:
        ws = create_connection("ws://localhost:8081/api/socket")
        print("Sending")
        ws.send(msg)
        ws.close()
    except Exception as e:
        print(e)


gpio.setmode(gpio.BCM)

trig = 3
echo = 2

gpio.setup(trig, gpio.OUT)
gpio.setup(echo, gpio.IN)

while 1:
    
    try:
        gpio.output(trig, False)
        time.sleep(1)
        
        gpio.output(trig, True)
        time.sleep(0.00001)
        gpio.output(trig, False)
        
        while gpio.input(echo) == 0:
            pulse_start = time.time()
        
        while gpio.input(echo) == 1:
            pulse_end = time.time()
            
        pulse_duration = pulse_end - pulse_start
        distance = pulse_duration * 17000
        distance = round(distance, 2)
        print(distance)
        send_distance(distance)

    except:
        gpio.cleanup()
