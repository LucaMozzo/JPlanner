
import planner.domain.*;
import planner.types.CustomObject;
import planner.types.standard.Boolean;
import planner.types.standard.Integer;
import planner.problem.State;
import utils.Comparison;
import utils.Operation;
import utils.exceptions.DuplicateVariableNameException;
import utils.exceptions.OperationNotSupportedException;

import java.util.Iterator;

/**
 * Created by LUCA on 21/06/2016.
 */
public class Test_Object_Oriented {

    public static void main(String[] args) throws OperationNotSupportedException, DuplicateVariableNameException {

        //object definitions

        class Block extends CustomObject {
            Variable<Boolean> onTable = new Variable<>("ontable", new Boolean(true));
            Variable<Boolean> isClear = new Variable<>("clear", new Boolean(true));
            Variable<Block> above = new Variable<Block>("above", null);
        }

        class Gripper extends CustomObject {
            Variable<Block> holding = new Variable<>("holding", null);
        }

        //object instances

        Block block1 = new Block();
        Block block2 = new Block();
        Block block3 = new Block();
        Block block4 = new Block();

        Gripper gripper = new Gripper();

        //actions definitions

        Action pickup = new Action("pick up");
        Action putdown = new Action("put down");
        Action stack = new Action("stack");
        Action unstack = new Action("unstack");

        Parameter<Gripper> gripperParameter = new Parameter<Gripper>();
        Parameter<Block> blockParameter = new Parameter<Block>();
        Parameter<Block> secondBlockParameter = new Parameter<Block>();

        pickup.addParameter(gripperParameter);
        pickup.addParameter(blockParameter);

        putdown.addParameter(gripperParameter);
        putdown.addParameter(blockParameter);

        stack.addParameter(gripperParameter);
        stack.addParameter(blockParameter);
        stack.addParameter(secondBlockParameter);

        unstack.addParameter(gripperParameter);
        unstack.addParameter(blockParameter);
        unstack.addParameter(secondBlockParameter);

        Precondition<Boolean> beclear = new Precondition<>("clear", new Boolean(true));
        Precondition<Boolean> beontable = new Precondition<>("ontable", new Boolean(true));
        Precondition<Block> handempty = new Precondition<>("holding", null);

        pickup.addPrecondition(beclear);
        pickup.addPrecondition(beontable);
        pickup.addPrecondition(handempty);

        Precondition<Block> holding = new Precondition<>("holding", new Block()); //fix

        putdown.addPrecondition(holding);

        stack.addPrecondition(holding);
        stack.addPrecondition(beclear);


    }
}
