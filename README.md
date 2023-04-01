# RunShell

Suite of programs meant to be run in an Android device through app_process / shell.

## Cola

Sets text to your android device clipboard.

```bash
adb push RunShell/app/build/intermediates/apk/debug/app-debug.apk /data/local/tmp/ && adb shell CLASSPATH=/data/local/tmp/app-debug.apk app_process / Runner cola "https://google.com/" && adb shell input keyevent 279
```