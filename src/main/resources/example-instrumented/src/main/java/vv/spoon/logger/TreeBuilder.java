package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TreeBuilder {

    private static PrintWriter fileWriter;
    private static Tree currentTree;
    private static ArrayList<Tree> treeList = new ArrayList<Tree>();

    public static void beginMethod(String label) {
    	
    	try {
			getWriter();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Tree newTree = new Tree();
        newTree.setNom(label);
        newTree.setPere(currentTree);

        if (currentTree != null) {
        	currentTree.addFils(newTree);
        }

        treeList.add(newTree);
        currentTree = newTree;
    }

    public static void endMethod() {
    	currentTree = currentTree.getPere();
    }

    public static void writeLog() {
    	out();
        fileWriter.close();
    }
    
    public static void out (){
    	try {
            PrintWriter writer = getWriter();

            writer.write(TreeBuilder.buildTree(TreeBuilder.currentTree, ""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	fileWriter.close();

    }

    protected static String buildTree(Tree currentNode, String separator) {
        String raw = separator + currentNode.getNom() + "\n";
        List<Tree> childrenTreeNode = currentNode.getFils();

        for (Tree child: childrenTreeNode) {
            raw += buildTree(child, separator + " | ");
        }

        return raw;
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookTree shutdownHook = new ShutdownHookTree();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
