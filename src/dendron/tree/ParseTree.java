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
                prgm.addAction(new Assignment(program.get(0), parseExpr(program)));
            }
            else {
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
        return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return null;
    }

}
