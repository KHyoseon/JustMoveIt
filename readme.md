# 서버 설정

pem 키를 사용하여 MobaXterm에 세션 생성 

(이때 ec2의 public ip 를 우분투에서 $curl [ifconfig.me](http://ifconfig.me/)  명령어를 사용하여 구함)

 

우분투에 java 1.8 // mysql:latest // docker-compose //docker 설치

DB

$ docker pull mysql 을 통해 Mysql Docker 이미지를 다운받고 

Docker을 통해 Mysql로 접속하여 ssafy 유저 생성한다음 권한 부여함 

이를 Docker-compose를 통해 Docker Container에 넣음 (포트 3306)

이후 Mysql Workbench에서 도메인을 통해 접속한 결과 정상동작 확인됨

Spring Boot

인텔리제이에서 만든 스프링 부트 프로젝트를 깃을 통해  EC2로 Pull 받음
Pull 받은 폴더 안에서 `$ ./gradlew` 을 통해 JAR 파일 빌드

받은 JAR 파일을 /etc/nginx/ 폴더에 옮기고 Dockerfile을 통해 이미지화 시킴

Dockerfile

---

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=CommonPJT-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]
WORKDIR /

---

이미지화 시킨 Docker file을 docker-compose 파일을 통해 컨테이너에 올림

docker-compose

---

version: '3'
services:
mysql:
image: mysql:latest
container_name: mysql_container
ports:
- 3306:3306 # HOST:CONTAINER
environment:
MYSQL_ROOT_PASSWORD: Ssafyd207
MYSQL_PASSWORD: Ssafyd207
MYSQL_USER: ssafy
MYSQL_PASSWORD: Ssafyd207
command:
- --character-set-server=utf8mb4
- --collation-server=utf8mb4_unicode_ci
volumes:
- /etc/mysql/
networks:
- test_network_02
application:
build: .
image: springboot
restart: always
ports:
- "8081:8081"
depends_on:
- mysql
container_name: springboot_container
environment:
SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/ssafy?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME: ssafy
SPRING_DATASOURCE_PASSWORD: Ssafyd207
networks:
- test_network_02

networks:
test_network_02:

---

---

docker ps 입력 시

---

root@ip-172-26-6-129:/etc/nginx# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED       STATUS       PORTS                                                  NAMES
a231687eb3e6   springboot     "java -jar /myboot.j…"   3 hours ago   Up 3 hours   0.0.0.0:8081->8081/tcp, :::8081->8081/tcp              springboot_container
502d87145d6b   mysql:latest   "docker-entrypoint.s…"   8 hours ago   Up 8 hours   0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp   mysql_container

포트 사용 상황

---

root@ip-172-26-6-129:/etc/nginx# netstat -tnlp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 0.0.0.0:3306            0.0.0.0:*               LISTEN      131287/docker-proxy
tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      176236/nginx: maste
tcp        0      0 0.0.0.0:8081            0.0.0.0:*               LISTEN      175444/docker-proxy
tcp        0      0 127.0.0.53:53           0.0.0.0:*               LISTEN      40309/systemd-resol
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      36711/sshd: /usr/sb
tcp        0      0 127.0.0.1:6010          0.0.0.0:*               LISTEN      165726/sshd: ubuntu
tcp        0      0 0.0.0.0:443             0.0.0.0:*               LISTEN      176236/nginx: maste
tcp        0      0 127.0.0.1:6011          0.0.0.0:*               LISTEN      174772/sshd: ubuntu
tcp6       0      0 :::3306                 :::*                    LISTEN      131294/docker-proxy
tcp6       0      0 :::80                   :::*                    LISTEN      176236/nginx: maste
tcp6       0      0 :::8081                 :::*                    LISTEN      175452/docker-proxy
tcp6       0      0 :::22                   :::*                    LISTEN      36711/sshd: /usr/sb
tcp6       0      0 ::1:6010                :::*                    LISTEN      165726/sshd: ubuntu
tcp6       0      0 :::443                  :::*                    LISTEN      176236/nginx: maste
tcp6       0      0 ::1:6011                :::*                    LISTEN      174772/sshd: ubuntu

/var/log/nginx 의 에러 로그

2022/08/08 01:24:58 [error] 174926#174926: *6 open() "/var/www/html/justmoveit/swagger-ui/index.html" failed (2: No such file or directory), client: 14.46.142.171, server: [i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/), request: "GET //justmoveit/swagger-ui/index.html HTTP/1.1", host: "[i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/)"
2022/08/08 01:25:03 [error] 174926#174926: *6 open() "/var/www/html/justmoveit/swagger-ui/index.html" failed (2: No such file or directory), client: 14.46.142.171, server: [i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/), request: "GET /justmoveit/swagger-ui/index.html HTTP/1.1", host: "[i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/)"
2022/08/08 01:29:00 [error] 175613#175613: *4 open() "/var/www/html/justmoveit/swagger-ui/index.html" failed (2: No such file or directory), client: 14.46.142.171, server: [i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/), request: "GET /justmoveit/swagger-ui/index.html HTTP/1.1", host: "[i7d207.p.ssafy.io](http://i7d207.p.ssafy.io/)"
2022/08/08 02:12:07 [crit] 175785#175785: *7 SSL_do_handshake() failed (SSL: error:14201044:SSL routines:tls_choose_sigalg:internal error) while SSL handshaking, client: 66.175.214.137, server: 0.0.0.0:443
2022/08/08 04:12:11 [crit] 176135#176135: *11 SSL_do_handshake() failed (SSL: error:14201044:SSL routines:tls_choose_sigalg:internal error) while SSL handshaking, client: 198.74.59.230, server: 0.0.0.0:443
