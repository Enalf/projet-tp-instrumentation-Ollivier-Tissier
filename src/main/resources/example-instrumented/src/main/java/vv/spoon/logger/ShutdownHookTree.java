package vv.spoon.logger;

public class ShutdownHookTree extends Thread {

    public void run() {
        TreeBuilder.writeLog();
    }
}
