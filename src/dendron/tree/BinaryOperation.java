package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryOperation implements ExpressionNode {

    public static final String ADD = "+";

    public static final String SUB = "-";

    public static final String DIV = "/";

    public static final String MUL = "*";

    public static final java.util.Collection<String> OPERATORS = new ArrayList<String>(Arrays.asList(ADD,SUB,DIV,MUL));

    private String operator;

    private ExpressionNode leftChild;

    private ExpressionNode rightChild;

    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        this.operator=operator;
        this.leftChild=leftChild;
        this.rightChild=rightChild;

    }

    public int evaluate(java.util.Map<String,Integer> symTab){
        if(operator.equals(ADD)){
            return leftChild.evaluate(symTab) + rightChild.evaluate(symTab);
        }
        if(operator.equals(SUB)){
            return leftChild.evaluate(symTab) - rightChild.evaluate(symTab);

        }
        if(operator.equals(DIV)){
            return leftChild.evaluate(symTab) / rightChild.evaluate(symTab);
        }
        if(operator.equals(MUL)) {
            return leftChild.evaluate(symTab) * rightChild.evaluate(symTab);

        }

        else{
            return -1;
        }
    }

    public void infixDisplay(){
        leftChild.infixDisplay();
        System.out.println(operator);
        rightChild.infixDisplay();
        System.out.println();

    }

    public java.util.List<Machine.Instruction> emit(){

    }


}
