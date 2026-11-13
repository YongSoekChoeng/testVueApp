#!/bin/bash
# testVue를 빌드해서 기동한다. systemd를 쓰지 않는다. 정지는 stopApp.sh, 상태는 statusApp.sh.
#
#   bin/startApp.sh          화면(npm) 빌드 + 서버(mvn) 빌드 + 기동
#   bin/startApp.sh -n       빌드 없이 target/testVue.jar 그대로 기동
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
APP_NAME="testVue"
APP_PORT="${APP_PORT:-7100}"
PID_FILE="$SCRIPT_DIR/application.pid"
export JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/java-21-openjdk-amd64}"

if [ -f "$PID_FILE" ] && kill -0 "$(cat "$PID_FILE")" 2>/dev/null; then
    echo "이미 떠 있습니다 (PID $(cat "$PID_FILE")). 먼저 bin/stopApp.sh를 실행하세요."
    exit 1
fi

cd "$PROJECT_DIR"
if [ "$1" != "-n" ]; then
    echo "[1/3] 화면 빌드 (frontend → frontend/dist)"
    cd "$PROJECT_DIR/frontend"
    [ -d node_modules ] || npm ci
    npm run build
    cd "$PROJECT_DIR"
    echo "[2/3] 서버 빌드 (mvn clean package)"
    mvn -q clean package -DskipTests
fi

echo "[3/3] 기동 (포트 $APP_PORT)"
mkdir -p "$PROJECT_DIR/logs"
APP_PORT="$APP_PORT" setsid nohup "$JAVA_HOME/bin/java" -jar "$PROJECT_DIR/target/$APP_NAME.jar" \
    >> "$PROJECT_DIR/logs/$APP_NAME.out" 2>&1 < /dev/null &
echo $! > "$PID_FILE"

for i in $(seq 1 60); do
    if curl -s -o /dev/null "http://localhost:$APP_PORT/"; then
        echo "완료. http://localhost:$APP_PORT/ (로그: logs/$APP_NAME.log, logs/$APP_NAME.out)"
        exit 0
    fi
    if ! kill -0 "$(cat "$PID_FILE")" 2>/dev/null; then
        echo "기동하다 멈췄습니다. logs/$APP_NAME.out을 확인하세요."
        rm -f "$PID_FILE"
        exit 1
    fi
    sleep 1
done
echo "60초 안에 응답이 없습니다. logs/$APP_NAME.out을 확인하세요."
exit 1
