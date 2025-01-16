#!/bin/bash

APP_NAME="See-book-0.0.1-SNAPSHOT.jar"  # 실행할 JAR 파일 이름
APP_DIR="/home/ec2-user/SeeBook"        # 애플리케이션이 위치한 디렉터리
LOG_DIR="/home/ec2-user/SeeBook/logs"   # 로그 파일을 저장할 디렉터리
LOG_FILE="$LOG_DIR/app.log"             # 로그 파일 이름

# 로그 디렉터리 생성 (없을 경우)
mkdir -p $LOG_DIR

# 백그라운드에서 애플리케이션 실행
echo "Starting application..."
nohup java -jar $APP_DIR/$APP_NAME > $LOG_FILE 2>&1 &

# PID 저장
echo $! > $APP_DIR/app.pid
echo "Application started with PID $(cat $APP_DIR/app.pid)"
