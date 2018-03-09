package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/*
Name: Alexander Hurley
Date: 3/9/2018
 */

public class Program implements ActionNode {

    private ArrayList<ActionNode> ary = new ArrayList<ActionNode>();

    /*
    Add a child of this Program node.
     */
    public void addAction(ActionNode newNode) {
        ary.add(newNode);
    }

    /*
    Execute each ActionNode in this object
     */
    public void execute(java.util.Map<String, Integer> symTab) {
        for (ActionNode thing : ary) {
            thing.execute(symTab);
        }
    }

    /*
    Show the infix displays of all children
     */
    public void infixDisplay() {
        for (ActionNode thing : ary) {
            thing.infixDisplay();
        }
    }

    /*
    Create a list of instructions emitted by each child
     */
    public java.util.List<Machine.Instruction> emit() {
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        for (ActionNode thing : ary) {
            llist.addAll(thing.emit());
        }
        return llist;
    }
}
