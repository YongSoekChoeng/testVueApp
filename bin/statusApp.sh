#!/bin/bash
# testVue 상태: RUNNING / STARTING / STOPPED
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PID_FILE="$SCRIPT_DIR/application.pid"
APP_PORT="${APP_PORT:-7100}"

if [ -f "$PID_FILE" ] && kill -0 "$(cat "$PID_FILE")" 2>/dev/null; then
    if curl -s -o /dev/null "http://localhost:$APP_PORT/"; then
        echo "RUNNING (PID $(cat "$PID_FILE"), http://localhost:$APP_PORT/)"
    else
        echo "STARTING (PID $(cat "$PID_FILE"))"
    fi
else
    echo "STOPPED"
fi
