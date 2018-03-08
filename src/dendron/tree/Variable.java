package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/*
Name: Alexander Hurley
Date: 3/8/2018
 */
public class Variable implements ExpressionNode{

    private String name;

    public Variable(String name){
        this.name = name;
    }

    /*
    prints the name
     */
    public void infixDisplay(){
        System.out.println(name);
    }

    /*
    pushes name onto stack and returns stack
     */
    public java.util.List<Machine.Instruction> emit(){
        Machine.Load thingy = new Machine.Load(name);
        ArrayList<Machine.Instruction> thing = new ArrayList<Machine.Instruction>();
        thing.add(thingy);
        return thing;
    }

    /*
    returns value int from key value pair
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        return symTab.get(this.name);
    }
}
