#!/bin/bash
# testVue를 멈춘다. 30초 기다려도 안 멈추면 PID 파일을 남기고 1로 끝난다(kill -9는 하지 않는다).
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PID_FILE="$SCRIPT_DIR/application.pid"

if [ ! -f "$PID_FILE" ] || ! kill -0 "$(cat "$PID_FILE")" 2>/dev/null; then
    echo "떠 있지 않습니다."
    rm -f "$PID_FILE"
    exit 0
fi
PID="$(cat "$PID_FILE")"
kill "$PID"
for i in $(seq 1 30); do
    if ! kill -0 "$PID" 2>/dev/null; then
        rm -f "$PID_FILE"
        echo "멈췄습니다 (PID $PID)."
        exit 0
    fi
    sleep 1
done
echo "30초 안에 멈추지 않았습니다 (PID $PID). 직접 확인하세요."
exit 1
