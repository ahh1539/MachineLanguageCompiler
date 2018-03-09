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

    public Assignment(String ident, ExpressionNode rhs){
        this.ident=ident;
        this.rhs=rhs;
    }

    public void execute(java.util.Map<String,Integer> symTab){
        System.out.println(symTab.put(ident, rhs.evaluate(symTab)));
    }

    public void infixDisplay(){
        System.out.println(":==" + ident);

    }

    public java.util.List<Machine.Instruction> emit(){
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.addAll(rhs.emit());
        llist.add(new Machine.Store(ident));
        return llist;


    }

}
