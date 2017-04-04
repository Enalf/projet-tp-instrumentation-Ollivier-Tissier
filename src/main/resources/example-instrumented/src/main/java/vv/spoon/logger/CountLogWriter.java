package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class CountLogWriter {

    private static PrintWriter fileWriter;
    protected static Map<String, Integer> methodes = new HashMap();


    public static void writeLog() {

        fileWriter.close();
    }

    public static void out (String string, boolean error) {

        try {
            PrintWriter writer = getWriter();
            Integer counterValue = 1;

            if (methodes.containsKey(string)) {
                counterValue = methodes.get(string)+1;
            }

            methodes.put(string, counterValue);
            for (Map.Entry<String, Integer> methode : methodes.entrySet()){
                writer.write(methode.getKey()+": "+methode.getValue()+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileWriter.close();
    }
    
    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            vv.spoon.logger.ShutdownHookCount shutdownHook = new vv.spoon.logger.ShutdownHookCount();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
