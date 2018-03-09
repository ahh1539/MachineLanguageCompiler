package dendron.tree;

import dendron.machine.Machine;
import org.omg.CORBA.INITIALIZE;
import org.omg.CORBA.MARSHAL;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
Name: Alexander Hurley
Date: 3/9/2018
 */
public class UnaryOperation implements ExpressionNode {

    public static final String NEG = "_";
    public static final String SQRT = "#";

    private ExpressionNode expr;
    private String operator;

    public static final java.util.Collection<String> OPERATORS = new ArrayList<String>(Arrays.asList(NEG,SQRT));

    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator=operator;
        this.expr=expr;
    }

    public int evaluate(java.util.Map<String,Integer> symTab){
        if(operator.equals(NEG)){
            return ((-1) * expr.evaluate(symTab));
        }
        if (operator.equals(SQRT)){
            return (int) Math.sqrt(expr.evaluate(symTab));
        }
        else{
            return -1;
        }
    }

    public void infixDisplay(){
        System.out.println("(");
        System.out.println(operator);
        expr.infixDisplay();
        System.out.println(")");
    }

    public java.util.List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.addAll(expr.emit());
        if (operator.equals(NEG)){
            llist.add(new Machine.Negate());
        }
        if  (operator.equals(SQRT)) {
            llist.add(new Machine.Negate());

        }
        return llist;

    }




}
