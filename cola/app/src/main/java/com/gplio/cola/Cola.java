package com.gplio.cola;

public class Cola {

    public static void main(String... args) {
        System.out.println("v0.1");
        System.out.println("---");
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("---");
        String firstArg;
        if (args.length == 0) {
            firstArg = "";
        } else {
            firstArg = args[0];
        }

        String text = firstArg;

        System.out.println("Setting the clipboard to: " + text);

        CustomClipboardManager cm = CustomServiceManager.INSTANCE.getClipboardManager();
        cm.setText(text);
    }
}