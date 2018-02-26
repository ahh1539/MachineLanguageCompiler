package dendron.machine;

import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import dendron.Errors;
import java.lang.Math;

/**
 * An abstraction of a computing machine that reads instructions
 * and executes them. It has an instruction set, a symbol table
 * for variables (instead of general-purpose memory), and a
 * value stack on which calculations are performed.
 *
 * (Everything is static to avoid the need to master the subtleties
 * of nested class instantiation or to pass the symbol table and
 * stack into every instruction when it executes.)
 *
 * THIS CLASS IS INCOMPLETE. The student must add code to it.
 *
 * @author James Heliotis
 * @author Alexander Hurley
 */
public class Machine {

    /** Do not instatiate this class. */
    private Machine() {}

    public static interface Instruction {
        /**
         * Run this instruction on the Machine, using the Machine's
         * value stack and symbol table.
         */
        void execute();

        /**
         * Show the instruction using text so it can be understood
         * by a person.
         * @return a short string describing what this instruction will do
         */
        @Override
        String toString();
    }

    private static Map< String, Integer > table = null;
    private static Stack< Integer > stack = null;

    /**
     * Reset the Machine to a pristine state.
     * @see Machine#execute
     */
    private static void reset() {
        stack = new Stack<>();
        table = new HashMap<>();
    }

    /**
     * Generate a listing of a program on standard output by
     * calling the toString() method on each instruction
     * contained therein, in order.
     *
     * @param program the list of instructions in the program
     */
    public static void displayInstructions(
            List< Machine.Instruction > program ) {
        System.out.println( "\nCompiled code:" );
        for ( Machine.Instruction instr: program ) {
            System.out.println( instr );
        }
        System.out.println();
    }

    /**
     * Run a "compiled" program by executing in order each instruction
     * contained therein.
     * Report on the final size of the stack (should normally be empty)
     * and the contents of the symbol table.
     * @param program a list of Machine instructions
     */
    public static void execute( List< Instruction > program ) {
        reset();
        System.out.println("Executing compiled code...");
        for ( Instruction instr: program ) {
            instr.execute();
        }
        System.out.println( "Machine: execution ended with " +
                stack.size() + " items left on the stack." );
        System.out.println();
        Errors.dump( table );
    }

    /**
     * The ADD instruction
     */
    public static class Add implements Instruction {
        /**
         * Run the microsteps for the ADD instruction.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 + op2 );
        }

        /**
         * Show the ADD instruction as plain text.
         * @return "ADD"
         */
        @Override
        public String toString() {
            return "ADD";
        }
    }

    /**
     * The STORE instruction
     */
    public static class Store implements Instruction {
        /** stores name of target variable */
        private String name;

        /**
         * Create a STORE instruction
         * @param ident the name of the target variable
         */
        public Store( String ident ) {
            this.name = ident;
        }
        /**
         * Run the microsteps for the STORE instruction.
         */
        @Override
        public void execute() {
            table.put( this.name, stack.pop() );
        }
        /**
         * Show the STORE instruction as plain text.
         * @return "STORE" followed by the target variable name
         */
        @Override
        public String toString() {
            return "STORE " + this.name;
        }
    }


    /*
    this takes two items from the stack and divides them then returns the value to the stack
     */
    public static class Divide implements Instruction{
        @Override
        public void execute() {
            if(stack.size() < 2){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            int op2 = stack.pop();
            int op1 = stack.pop();
            if(op2 == 0){
                Errors.report(Errors.Type.DIVIDE_BY_ZERO, null);
            }
            else {
                stack.push(op1 / op2);
            }
        }

        @Override
        public String toString() {
            return "DIVIDE";
        }

    }

    public static class Subtract implements Instruction{

        @Override
        public void execute() {
            if(stack.size() < 2){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 - op2 );
        }

        @Override
        public String toString() {
            return "SUBTRACT";
        }

    }

    /*
    this takes a value out of the stack and returns its square root
     */
    public static class SquareRoot implements Instruction{
        @Override
        public void execute() {
            if(stack.size() < 1){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            int op2 = stack.pop();
            double op1 = Math.sqrt(op2);
            int op3 = (int)op1;
            stack.push(op3);
        }

        @Override
        public String toString() {
            return "SQUAREROOT";
        }

    }
    /*
this pushes a given constant value into the stack
     */
    public static class PushConst implements Instruction{
        private int constant;

        public PushConst(int constant){
            this.constant = constant;
        }
        @Override
        public void execute() {
            stack.push(constant);
        }
        @Override
        public String toString() {
            return "PUSHCONST";
        }

    }

    /*
this prints an item from a stack
     */
    public static class Print implements Instruction{
        @Override
        public void execute() {
            if(stack.size() < 1){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }

            int op1 = stack.pop();
            System.out.println("***" + op1);
        }
        @Override
        public String toString() {
            return "PRINT";
        }

    }
    /*
    this takes a value out of stack and makes it negative
     */
    public static class Negate implements Instruction{
        @Override
        public void execute() {
            if(stack.size() < 1){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            int op1 = stack.pop();
            int op2 = (op1 * -1);
            stack.push(op2);
        }

        @Override
        public String toString() {
            return "NEGATE";
        }

    }
    /*
    This takes two values from stack multiplies them and pushes the value
     */
    public static class Multiply implements Instruction{
        @Override
        public void execute() {
            if(stack.size() < 2){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 * op2 );
        }
        @Override
        public String toString() {
            return "MULTIPLY";
        }

    }
    /*
    searches table for given value then pushes that value to stack
     */
    public static class Load implements Instruction{
        private String ident;

        public Load(String ident){
            this.ident = ident;
        }

        @Override
        public void execute() {

            stack.push(table.get(ident));

        }
        @Override
        public String toString() {
            return "LOAD";
        }


    }


    //
    // ENTER YOUR CODE FOR THE OTHER INSTRUCTION CLASSES HERE.
    //
}
