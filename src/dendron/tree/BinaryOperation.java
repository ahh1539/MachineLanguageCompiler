package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.Arrays;

/*
Name: Alexander Hurley
Date: 3/9/2018
 */
public class BinaryOperation implements ExpressionNode {

    public static final String ADD = "+";

    public static final String SUB = "-";

    public static final String DIV = "/";

    public static final String MUL = "*";

    public static final java.util.Collection<String> OPERATORS = new ArrayList<String>(Arrays.asList(ADD,SUB,DIV,MUL));

    private String operator;

    private ExpressionNode leftChild;

    private ExpressionNode rightChild;

    /*
    stores operator, leftChild, and rightChild
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        this.operator=operator;
        this.leftChild=leftChild;
        this.rightChild=rightChild;

    }

    /*
    Compute the result of evaluating both operands and applying the operator to them
     */
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

    /*
    the infixDisplay of the two child nodes separated by the operator and surrounded by parentheses
     */
    public void infixDisplay(){
        System.out.println("(");
        leftChild.infixDisplay();
        System.out.println(operator);
        rightChild.infixDisplay();
        System.out.println(")");

    }

    /*
    pops 2 values off stack then changes them based on conditional then pushes them again
     */
    public java.util.List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.addAll(leftChild.emit());
        llist.addAll(rightChild.emit());
        if (operator.equals(ADD)){
            llist.add(new Machine.Add());
        }
        if (operator.equals(SUB)){
            llist.add(new Machine.Subtract());
        }
        if (operator.equals(DIV)){
            llist.add(new Machine.Divide());
        }
        if (operator.equals(MUL)){
            llist.add(new Machine.Multiply());
        }
        return llist;

    }


}
