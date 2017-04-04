package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtExecutableReference;
import spoon.support.reflect.code.CtInvocationImpl;
import spoon.support.reflect.code.CtStatementImpl;
import spoon.support.reflect.declaration.CtConstructorImpl;
import spoon.support.reflect.declaration.CtMethodImpl;
import spoon.support.reflect.declaration.CtSimpleTypeImpl;

public class TreeProcessor extends AbstractProcessor<CtBlock> {

    @Override
    public boolean isToBeProcessed(CtBlock candidate) {
        int firstStatementIndex = 0;


        if (isConstructor(candidate.getParent().getClass()) && candidate.getStatements().size() > 1) {
            firstStatementIndex = 1;
        } else if (! isMethodBlock(candidate.getParent().getClass())) {
            return false;
        }

        CtInvocation firstStatement = (CtInvocation) candidate.getStatements().get(firstStatementIndex);

        try {
            Class type = firstStatement.getTarget().getType().getActualClass();
            CtExecutableReference executable = firstStatement.getExecutable();


            if(type.equals(java.io.PrintStream.class)
                    && isPrint(executable)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void process(CtBlock ctBlock) {

        // Récupération du premier et dernier statement
        int firstStatementIndex = 0;
        if (isConstructor(ctBlock.getParent().getClass()) && ctBlock.getStatements().size() > 1) {
            firstStatementIndex = 1;
        }
        CtInvocation firstStatement = (CtInvocation) ctBlock.getStatements().get(firstStatementIndex);
        CtStatement lastStatement = ctBlock.getStatements().get(ctBlock.getStatements().size()-1);


        if (lastStatement.toString().startsWith("return")) {
            lastStatement = ctBlock.getStatements().get(ctBlock.getStatements().size()-2);
        }

        // Appel en début de méthode
        SourcePosition positionFirstStatement = firstStatement.getPosition();
        CompilationUnit compileUnit = positionFirstStatement.getCompilationUnit();

        SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(positionFirstStatement.getSourceStart()), "/**", 0);
        compileUnit.addSourceCodeFragment(before);

        Object argument = firstStatement.getArguments().get(0);
        String snippet = "**/\n\t\tvv.spoon.logger.TreeBuilder.begin(" + argument
                + ");\n";

        SourceCodeFragment after = new SourceCodeFragment(compileUnit.nextLineIndex(positionFirstStatement.getSourceEnd()), snippet, 0);
        compileUnit.addSourceCodeFragment(after);

        // Appel en fin de méthode
        SourcePosition spEnd = lastStatement.getPosition();

        snippet = "\n\t\tvv.spoon.logger.TreeBuilder.end();\n";

        SourceCodeFragment after2 = new SourceCodeFragment(compileUnit.nextLineIndex(spEnd.getSourceEnd())+1, snippet, 0);
        compileUnit.addSourceCodeFragment(after2);
    }


    protected boolean isMethodBlock(Class block) {
        return CtMethod.class.isAssignableFrom(block);
    }

    protected boolean isConstructor(Class block) {
        return CtConstructor.class.isAssignableFrom(block);
    }

    protected boolean isPrint(CtExecutableReference executable) {
        String toString = executable.toString();
        return toString.startsWith("java.io.PrintStream.println(")
                || toString.startsWith("java.io.PrintStream.print(");
    }
}
