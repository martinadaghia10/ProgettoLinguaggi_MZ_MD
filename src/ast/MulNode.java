package ast;

import java.util.ArrayList;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class MulNode implements Node {
    private Node left;
    private Node right;

    public MulNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    public Type typeCheck() {
        if ((left.typeCheck() instanceof IntType) && (right.typeCheck() instanceof IntType))
            return new IntType();
        else {
            System.out.println("Type Error: Non integers in multiplication");
            return new ErrorType();
        }
    }

    public String codeGeneration() {
        return "//start MulNode\n"
                + left.codeGeneration()
                + "pushr A0 \n"
                + right.codeGeneration()
                + "popr T1 \n"
                + "mul A0 T1 \n"
                + "popr A0 \n"
                + "//end MulNode\n";
    }

    public String toPrint(String s) {
        return s + "Mult\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
    }

}