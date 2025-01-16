#!/bin/bash

APP_DIR="/home/ec2-user/SeeBook"  # 애플리케이션이 위치한 디렉터리
PID_FILE="$APP_DIR/app.pid"       # PID 파일 경로

if [ -f $PID_FILE ]; then
    PID=$(cat $PID_FILE)
    echo "Stopping application with PID $PID..."
    kill $PID

    # 종료 확인
    sleep 2
    if ps -p $PID > /dev/null; then
        echo "Force killing application..."
        kill -9 $PID
    fi

    # PID 파일 삭제
    rm $PID_FILE
    echo "Application stopped."
else
    echo "No PID file found. Application may not be running."
fi