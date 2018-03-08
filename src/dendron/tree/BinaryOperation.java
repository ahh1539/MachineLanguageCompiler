package dendron.tree;

import dendron.machine.Machine;

public class BinaryOperation implements ExpressionNode {

    public static final String ADD = "+";

    public static final String SUB = "-";

    public static final String DIV = "/";

    public static final String MUL = "*";

    public static final java.util.Collection<String> OPERATORS;

    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){

    }

    public int evaluate(java.util.Map<String,Integer> symTab){

    }

    public void infixDisplay(){

    }

    public java.util.List<Machine.Instruction> emit(){

    }


}
