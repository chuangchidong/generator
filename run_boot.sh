#!/bin/sh
## java env

#export JAVA_HOME=/usr/local/jdk/jdk1.8.0_101
#export JRE_HOME=$JAVA_HOME/jre

## jar文件全路径
FILE_URL="$1"
# FILE_ROOT jar包所在路径


#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh $0 jar包文件 [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $JAR_NAME|grep java |grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup java -Xms256m -Xmx512m -jar $FILE_URL >/dev/null 2>&1 &
    echo $! > $PID
    echo ">>> start $JAR_NAME successed PID=$! <<<"
   fi
  }

#停止方法
stop(){
  #is_exist
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9  $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#重启
restart(){
  stop
  start
}

#参数处理
if [ -f "$1" ]; then
    IS_CURRENT_ROOT=$(echo $FILE_URL | grep "/")
    if [[ "$IS_CURRENT_ROOT" != "" ]]; then
        FILE_ROOT=${FILE_URL%/*}/
    else
        FILE_ROOT=./
    fi
    ##PID  jar文件名称
    JAR_NAME=${FILE_URL##*/}
    ##PID  服务名称
    API_NAME=${JAR_NAME%.*}
    ##PID  代表是PID文件
    PID=$FILE_ROOT$API_NAME.pid
else
    echo "ERROR: "$FILE_URL" not exist"
    usage
    exit 0
fi

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$2" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
exit 0