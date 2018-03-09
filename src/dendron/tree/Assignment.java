package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/*
Name: Alexander Hurley
Date: 3/9/2018
 */

public class Assignment implements ActionNode {

    private String ident;
    private ExpressionNode rhs;

    /*
    stores Expression node and ident
     */
    public Assignment(String ident, ExpressionNode rhs){
        this.ident=ident;
        this.rhs=rhs;
    }


    /*
    Evaluates the RHS expression and assign the result value to the variable.
     */
    public void execute(java.util.Map<String,Integer> symTab){
        symTab.put(ident, rhs.evaluate(symTab));
    }

    /*
    displays code in infix form
     */
    public void infixDisplay(){
        System.out.print(ident + " " + ":=" + " ");
        rhs.infixDisplay();
        System.out.println();

    }

    /*
    stores a machine instruction, and the code made by rhs node that pushes to stack
     */
    public java.util.List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.addAll(rhs.emit());
        llist.add(new Machine.Store(ident));
        return llist;


    }

}
