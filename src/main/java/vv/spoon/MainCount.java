package vv.spoon;

import vv.spoon.processor.CountProcessor;

import java.io.IOException;

public class MainCount {
    public static void main(String[] args) throws IOException {
        InstruCount instru = new InstruCount(args[0], args[1], new CountProcessor());

        //copy the project (args[0]) in the output directory (args[1])
        instru.initOutputDirectory();

        //instrumentalize the java code of output directory with LogProcessor
        instru.instru();
    }
}
