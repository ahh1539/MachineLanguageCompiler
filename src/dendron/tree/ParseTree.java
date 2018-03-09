package dendron.tree;

import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Name: Alexander Hurley
Date: 3/9/2018
 */
/**
 * Operations that are done on a Dendron code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author Alexander Hurley
 */
public class ParseTree {

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     *
     */
    private Program prgm;
    private HashMap hash = new HashMap();


    public ParseTree( List< String > program ) {
        this.prgm = new Program();
        while (true){
            if(program.isEmpty()){
                break;
            }
            if(program.get(0).equals(":=")) {
                program.remove(0);
                prgm.addAction(new Assignment(program.remove(0), parseExpr(program)));
            }
            else {
                program.remove(0);
                System.out.println(program);
                prgm.addAction(new Print(parseExpr(program)));
            }

        }


    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
        return null;
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        while (true) {

            if (program.isEmpty() || program.get(0).equals("@") || program.get(0).equals(":=")) {
                break;
            }
            else{
                if(program.get(0).equals("#")|| program.get(0).equals("_")){
                    String temp = program.remove(0);
                    return new UnaryOperation(temp,parseExpr(program));
                }
                if(program.get(0).equals("-")||program.get(0).equals("+")||program.get(0).equals("/")||program.get(0).equals("*")){
                    String temp = program.remove(0);
                    return new BinaryOperation(temp, parseExpr(program), parseExpr(program));
                }
                if(program.get(0).matches( "^[a-zA-Z].*" )){
                    String temp = program.remove(0);
                    return new Variable(temp);

                }
                else{
                    String temp = program.remove(0);
                    return new Constant(Integer.parseInt(temp));
                }
            }

        }
        return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        prgm.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
        prgm.execute(hash);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return prgm.emit();
    }

}
