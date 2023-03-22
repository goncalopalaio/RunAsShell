# Cola

Sets text to your android device clipboard.

Runs the code through shell.

adb push cola/android/app/build/intermediates/apk/debug/app-debug.apk  /data/local/tmp/ && adb shell CLASSPATH=/data/local/tmp/app-debug.apk app_process / com.gplio.cola.Cola "https://github.com/" && adb shell input keyevent 279
