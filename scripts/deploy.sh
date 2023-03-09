#!/bin/sh

use_port1() {
  START_PORT=8080
  KILL_PORT=8081
}
use_port2() {
  START_PORT=8081
  KILL_PORT=8080
}
print_current_port_info() {
  echo "> 현재 실행 중 Port $KILL_PORT"
}
health_check() {
  for count in $(seq 1 50)
    do
      HEALTH_CHECK=$(curl -s $1)

      if [ $(echo $HEALTH_CHECK | grep 'health' | wc -l) -gt 0 ]; then
        echo "> Health Check 성공"
        break
      else
        echo "> Health Check의 응답이 없거나, status가 health가 아닙니다."
        echo "> Health Check: ${HEALTH_CHECK}\n"
      fi

      if [ $count -eq 50 ]; then
        echo "> Health Check 실패"
        echo "> Nginx에 연결하지 않고 배포를 종료합니다."
        exit 1
      fi

      echo "> Health Check 연결 실패. 3초 후 재시도..."
      sleep 3
    done
}

HEALTH_CHECK_ENDPOINT="/health"

if [ $(curl "localhost:8080$HEALTH_CHECK_ENDPOINT" | grep "health" | wc -l ) -gt 0 ]; then
  use_port2
else
  use_port1
fi

print_current_port_info


echo "> 구동 될 Port : $START_PORT"

echo "> 실행 될 Jar 파일 : $JAR_FILE_NAME"
JENKINS_NODE_COOKIE=dontKillMe && nohup java -jar $JAR_FILE_NAME --spring.profiles.active=$ACTIVE_PROFILE --server.port=$START_PORT --spring.datasource.url=$DB_URL --spring.datasource.username=$DB_USERNAME --spring.datasource.password=$DB_PASSWORD --jwt.secret-key=$JWT_SECREY_KEY 1> $DEPLOY_PATH/output.log 2>&1 &



HEALTH_CHECK_URL="localhost:$START_PORT$HEALTH_CHECK_ENDPOINT"

echo "> 5초 후 Health Check 시작"
echo "> curl -s ${HEALTH_CHECK_URL}"
sleep 5

health_check ${HEALTH_CHECK_URL}

echo "> Port 변경 : $KILL_PORT -> $START_PORT"
echo "> File name : $NGINX_CONF_NAME"
echo "set \$service_url http://127.0.0.1:$START_PORT;" | sudo tee $NGINX_CONF_NAME

sudo service nginx restart

echo "> Kill Port : $KILL_PORT"
fuser -k $KILL_PORT/tcp