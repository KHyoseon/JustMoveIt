import RPi.GPIO as gpio
import time
import requests

# 초음파 센서 세팅
gpio.setmode(gpio.BCM)
# 초음파 센서의 GPIO 포트 정보 불러오기
trig = 3
echo = 2
gpio.setup(trig, gpio.OUT)
gpio.setup(echo, gpio.IN)

while True:
    # 아니면 초음파 돌려돌려
    try:
        gpio.output(trig, False)
        # 1초마다 초음파 측정을 위함
        time.sleep(1)

        gpio.output(trig, True)
        time.sleep(0.00001)
        gpio.output(trig, False)

        # 초음파 발신
        while gpio.input(echo) == 0:
            # 초음파가 발신된 시간
            pulse_start = time.time()

        # 초음파 수신
        while gpio.input(echo) == 1:
            # 초음파가 수신된 시간
            pulse_end = time.time()

        # 초음파가 돌아올 때까지 걸린 시간
        pulse_duration = pulse_end - pulse_start
        # 초음파로 측정한 거리 계산
        distance = pulse_duration * 17000
        # 소수점 둘째자리에서 반올림
        distance = round(distance, 1)
        print(distance)
        
        # HTTP POST 요청
        if distance < 150:
            res = requests.post('http://localhost:8080', data={'distance': distance})

    except Exception as e:
        gpio.cleanup()
        print(e)

ws.close()