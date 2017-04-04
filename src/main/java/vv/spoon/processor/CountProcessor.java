package vv.spoon.processor;

import spoon.reflect.code.CtInvocation;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.reference.CtExecutableReference;
import vv.spoon.processor.LogProcessor;


public class CountProcessor extends LogProcessor {

    @Override
    public boolean isToBeProcessed(CtInvocation candidate) {
        try {
            Class type = candidate.getTarget().getType().getActualClass();
            CtExecutableReference executable = candidate.getExecutable();


            if(type.equals(java.io.PrintStream.class)
                    && isPrint(executable)
                    && isContentValid(candidate.getArguments().toString())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void process(CtInvocation element) {
        SourcePosition sp = element.getPosition();
        CompilationUnit compileUnit = sp.getCompilationUnit();

        //add /** before the invocation
        SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceStart()), "/**", 0);
        compileUnit.addSourceCodeFragment(before);

        //add **/ vv.spoon.logger.LogWriter.out( argument, newline, error); after the invocation
        Object argument = element.getArguments().get(0);
        String snippet = "**/\n\t\tvv.spoon.logger.CountMethodCall.out(" + argument
                + "," + isError(element.getTarget()) + ");\n";

        SourceCodeFragment after = new SourceCodeFragment(compileUnit.nextLineIndex(sp.getSourceEnd()), snippet, 0);
        compileUnit.addSourceCodeFragment(after);
    }

    protected boolean isContentValid(String content) {
        return ! (content.contains("result") || content.contains("error"));
    }
}
