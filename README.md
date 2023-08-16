# RunShell

Suite of programs meant to be run in an Android device through app_process / shell.

Currently there's a single program:

## Cola

Sets text to your android device clipboard.

```bash
adb push RunShell/app/build/intermediates/apk/debug/app-debug.apk /data/local/tmp/ && adb shell CLASSPATH=/data/local/tmp/app-debug.apk app_process / Runner cola "https://google.com/" && adb shell input keyevent 279
```

## Escreve

Injects key events into the device.
Consult the corresponding key codes here: https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/KeyEvent.java

```bash
# Injects KEYCODE_2 ; KEYCODE_0; KEYCODE_5
adb push RunShell/app/build/intermediates/apk/debug/app-debug.apk /data/local/tmp/ && adb shell CLASSPATH=/data/local/tmp/app-debug.apk app_process / Runner escreve "9-7-12"

# Injects KEYCODE_2 ; Sleep for 200ms ; KEYCODE_0; Sleep for 200ms ; KEYCODE_5
adb push RunShell/app/build/intermediates/apk/debug/app-debug.apk /data/local/tmp/ && adb shell CLASSPATH=/data/local/tmp/app-debug.apk app_process / Runner escreve "9-SLEEP200-7-SLEEP200-12"
```
