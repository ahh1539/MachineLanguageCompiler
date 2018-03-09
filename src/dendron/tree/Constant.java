package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/*
Name: Alexander Hurley
Date: 3/8/2018
 */
public class Constant implements ExpressionNode{

    private int value;

    /*
    Stores integer value
     */
    public Constant(int value){
        this.value = value;
    }

    /*
    prints out value
     */
    public void infixDisplay(){
        System.out.print(this.value);

    }

    /*
    evaluates and returns the constant
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        return this.value;

    }

    /*
    Emit an instruction to push the value onto the stack.
     */
    public java.util.List<Machine.Instruction> emit(){
        Machine.PushConst cnst = new Machine.PushConst(this.value);
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.add(cnst);
        return llist;

    }

}
