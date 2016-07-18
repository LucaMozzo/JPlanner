
import planner.domain.Domain;
import planner.domain.Variable;
import planner.domain.generic.ObjectAction;
import planner.domain.generic.ObjectEffect;
import planner.domain.generic.ObjectPrecondition;
import planner.problem.Problem;
import planner.problem.TreeState;
import planner.searchspace.build.TreeBuilder;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.CustomObject;
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
public class Test2 {

    public static void main(String[] args) throws OperationNotSupportedException, DuplicateVariableNameException {
        class Ball extends CustomObject {
            private Variable<Integer> timesMoved = new Variable<>("timesMoved", new Integer(0));
        }

        Ball b1 = new Ball();
        Ball b2 = new Ball();

        ObjectAction<Ball> move = new ObjectAction<>(new Ball(), "move");

        ObjectPrecondition<Ball, Integer> preMove = new ObjectPrecondition(new Ball(), "timesMoved", new Integer(2), Comparison.LESS_OR_EQUAL);
        ObjectEffect<Ball, Integer> effMove = new ObjectEffect<>(new Ball(), "timesMoved", Operation.INCREASE);

        move.addPrecondition(preMove);
        move.addEffect(effMove);

        State init = new State();
        init.addObject(b1);
        init.addObject(b2);

        State goal = new State();
        goal.addObject(b1);
        Ball b3 = new Ball();
        b3.timesMoved = new Variable<>("timesMoved", new Integer(1));
        goal.addObject(b3);

        Domain d = new Domain();
        d.addAction(move);
        Problem p1 = new Problem(d);
        p1.setInitialState(init);
        p1.setGoalState(goal);

        //searchspace
        Tree searchspace = TreeBuilder.build(p1, d);
        System.out.println("Explored " + searchspace.getSize() + " nodes");

        Iterator it = searchspace.iterator();

        while(it.hasNext())
            System.out.println("\n" + ((TreeState)((Node)it.next()).getElement()).getActions());
        //System.out.println(p1.solve());
    }
}
