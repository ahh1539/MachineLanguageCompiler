package dendron.tree;

import dendron.machine.Machine;
import org.omg.CORBA.PUBLIC_MEMBER;

public class UnaryOperation implements ExpressionNode {

    public static final String NEG = "_";
    public static final String SQRT = "#";

    public static final java.util.Collection<String> OPERATORS{

    }

    public UnaryOperation(String operator, ExpressionNode expr){

    }

    public int evaluate(java.util.Map<String,Integer> symTab){

    }

    public void infixDisplay(){

    }

    public java.util.List<Machine.Instruction> emit(){

    }




}
