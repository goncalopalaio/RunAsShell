#!/bin/bash
set -e

command=$1
args=$2

path_to_apk="app/build/intermediates/apk/debug/app-debug.apk"
# path_to_apk="app/build/intermediates/apk/release/run-as-shell.apk"

test_apk="run-as-shell.apk"

function install {
  cp $path_to_apk $test_apk
  adb push $test_apk /data/local/tmp
}

if [ "$command" = "install" ]; then
    install

elif [ "$command" = "test-input-1" ];then
    adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner input "9-SLEEP200-7-SLEEP200-12"

elif [ "$command" = "test-toast-1" ];then
    adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner toast "This\ is a short toast"

elif [ "$command" = "test-broadcast-1" ];then
    adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner broadcast "a" "b"
else
	echo "Command not supported | command="$command", args="$args
fi