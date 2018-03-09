package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/*

Name: Alexander Hurley
Date: 3/8/2018
 */

public class Print implements ActionNode{

    private ExpressionNode printee;
    /*
    initialized print node
     */
    public Print(ExpressionNode printee){
        this.printee = printee;
    }
    /*
    This prints out the evaluated printee expression node
     */
    public void execute(java.util.Map<String,Integer> symTab){
        System.out.println(this.printee.evaluate(symTab) + "===");
    }

    /*
    prints out user code
     */
    public void infixDisplay(){
        System.out.print("Print");
        printee.infixDisplay();
    }

    /*
    This method returns the code emitted by the printee node that pushes the
    value of the printee expression onto the stack
     */
    public java.util.List<Machine.Instruction> emit(){
        Machine.Print printt = new Machine.Print();
        ArrayList<Machine.Instruction> llist = new ArrayList<Machine.Instruction>();
        llist.addAll(printee.emit());
        llist.add(printt);
        return llist;
    }


}
