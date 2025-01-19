# RunShell

Suite of programs meant to be run in an Android device through app_process / shell.

Currently there's two programs:

## Clipboard

Sets text to your android device clipboard and pastes it into the focused field.

```bash
adb push run-as-shell.apk /data/local/tmp/ \
&& adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner clipboard "https://google.com/" \
&& adb shell input keyevent 279
```

## Input

Injects key events into the device.
Consult the corresponding key codes here: https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/KeyEvent.java

```bash
# Injects KEYCODE_2 ; KEYCODE_0; KEYCODE_5
adb push run-as-shell.apk /data/local/tmp/ \
&& adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner input "9-7-12"

# Injects KEYCODE_2 ; Sleep for 200ms ; KEYCODE_0; Sleep for 200ms ; KEYCODE_5
adb push run-as-shell.apk /data/local/tmp/ \
&& adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner input "9-SLEEP200-7-SLEEP200-12"
```

## Toast

Displays a Toast on the screen.

```bash
adb push run-as-shell.apk /data/local/tmp/ \
&& adb shell CLASSPATH=/data/local/tmp/run-as-shell.apk app_process / Runner toast "This is a short toast"
```
