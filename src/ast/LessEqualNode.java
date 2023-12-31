package ast;

import java.util.ArrayList;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class LessEqualNode implements Node {
    private Node left;
    private Node right;

    public LessEqualNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck() {
        if (left.typeCheck() instanceof IntType && right.typeCheck() instanceof IntType) {
            return new BoolType();
        } else {
            System.out.println("Type Error: Non integers in less than or equal to comparison");
            return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String labelTrue = SimpLanlib.freshLabel();
        String labelEnd = SimpLanlib.freshLabel();

        return "//start LessEqualNode\n" +
                left.codeGeneration() +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "bleq T1 A0 " + labelTrue + " \n" + //branch if less than or equal to
                "storei A0 0\n" +
                "b " + labelEnd + " \n" +
                labelTrue + ": \n" +
                "storei A0 1 \n" +
                labelEnd + ": \n" +
                "//end LessEqualNode\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "Lessequal\n" +
                left.toPrint(s + "  ") +
                right.toPrint(s + "  ");
    }
}
