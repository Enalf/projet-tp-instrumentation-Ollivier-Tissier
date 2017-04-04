package vv.spoon.logger;

public class ShutdownHookCounter extends Thread {

    public void run() {
        CountMethodCall.writeLog();
    }
}
