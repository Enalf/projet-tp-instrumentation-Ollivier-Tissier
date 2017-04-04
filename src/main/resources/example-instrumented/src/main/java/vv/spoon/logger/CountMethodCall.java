package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class CountMethodCall {

    private static PrintWriter fileWriter;
    protected static Map<String, Integer> methodes = new HashMap();


    public static void writeLog() {
    	toStringCount();
        fileWriter.close();
    }

    public static void out (String string, boolean error) {

        	try {
				getWriter();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
            Integer counterValue = 1;

            if (methodes.containsKey(string)) {
                counterValue = methodes.get(string)+1;
            }

            methodes.put(string, counterValue);
            
    }
    
    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            vv.spoon.logger.ShutdownHookCount shutdownHook = new vv.spoon.logger.ShutdownHookCount();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
    
    protected static void toStringCount(){
    	try {
            PrintWriter writer = getWriter();
	    	for (Map.Entry<String, Integer> methode : methodes.entrySet()){
	            writer.write(methode.getKey()+": "+methode.getValue()+"\n");}
	        }
	    catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }

	    	
    }
}
