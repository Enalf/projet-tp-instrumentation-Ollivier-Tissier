package vv.spoon.logger;

public class ShutdownHookCount extends Thread {

    public void run() {
        CountMethodCall.writeLog();
    }
}
