import planner.problem.TreeState;
import planner.searchspace.build.TreeBuilder;
import planner.domain.*;
import planner.problem.Problem;
import planner.problem.State;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;
import utils.Comparisons;
import utils.Operations;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 18/05/2016.
 */
public class Test {

    public static void main(String[] args) throws OperationNotSupportedException {

        Tree plan = createPlan();
        System.out.println("Explored " + plan.getSize() + " nodes");

        Iterator it = plan.iterator();

        while(it.hasNext())
            System.out.println("\n" + ((TreeState)((Node)it.next()).getElement()).getActions());
    }

    private static Tree createPlan() throws OperationNotSupportedException {
        Variable<Integer> btnPressedVar = new Variable<>("btnPressed", new Integer(0));
        Variable<Integer> btn2PressedVar = new Variable<>("btn2Pressed", new Integer(0));

        Precondition<Integer> btnPressedPre = new Precondition<>("btnPressed", new Integer(1), Comparisons.LESS_OR_EQUAL);
        Precondition<Integer> btn2PressedPre = new Precondition<>("btn2Pressed", new Integer(4), Comparisons.LESS);

        Effect<Integer> btnPressedEff = new Effect<>("btnPressed", Operations.INCREASE);
        Effect<Integer> btn2PressedEff = new Effect<>("btn2Pressed", Operations.INCREASE);

        Action pressBtn = new Action("press the button");
        pressBtn.addPrecondition(btnPressedPre);
        pressBtn.addEffect(btnPressedEff);

        Action pressBtn2 = new Action("press the second button");
        pressBtn2.addPrecondition(btn2PressedPre);
        pressBtn2.addEffect(btn2PressedEff);

        Domain domain = new Domain();
        domain.addAction(pressBtn);
        domain.addAction(pressBtn2);

        State init = new State();
        init.addVariable(btnPressedVar);
        init.addVariable(btn2PressedVar);

        State goal = new State();
        goal.addVariable(new Variable<Integer>("btnPressed", new Integer(1)));
        goal.addVariable(new Variable<Integer>("btn2Pressed", new Integer(2)));

        Problem problem1 = new Problem(domain);
        problem1.setInitialState(init);
        problem1.setGoalState(goal);

        return TreeBuilder.build(problem1, domain); //TODO stop the construction of the searchspace when you find the goal
    }
}
