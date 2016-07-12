import planner.domain.*;
import planner.problem.Problem;
import planner.problem.State;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;
import utils.Comparisons;
import utils.Operations;

import java.util.LinkedList;

/**
 * Created by LUCA on 20/06/2016.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Variable<Integer> btnPressedVar = new Variable<>("btnPressed", new Integer(0));
        Variable<Integer> btn2PressedVar = new Variable<>("btn2Pressed", new Integer(0));
        Variable<Integer> btn3PressedVar = new Variable<>("btn3Pressed", new Integer(0));

        Precondition<Integer> btnPressedPre = new Precondition<>("btnPressed", new Integer(1), Comparisons.LESS_OR_EQUAL);
        Precondition<Integer> btn2PressedPre = new Precondition<>("btn2Pressed", new Integer(4), Comparisons.LESS);
        Precondition<Integer> btn3PressedPre = new Precondition<>("btn3Pressed", new Integer(5), Comparisons.LESS);

        Effect<Integer> btnPressedEff = new Effect<>("btnPressed", Operations.INCREASE);
        Effect<Integer> btn2PressedEff = new Effect<>("btn2Pressed", Operations.INCREASE);
        Effect<Integer> btn3PressedEff = new Effect<>("btn3Pressed", Operations.INCREASE);

        Action pressBtn = new Action("press the button");
        pressBtn.addPrecondition(btnPressedPre);
        pressBtn.addEffect(btnPressedEff);

        Action pressBtn2 = new Action("press the second button");
        pressBtn2.addPrecondition(btn2PressedPre);
        pressBtn2.addEffect(btn2PressedEff);

        Action pressBtn3 = new Action("press the third button");
        pressBtn3.addPrecondition(btn3PressedPre);
        pressBtn3.addEffect(btn3PressedEff);

        Domain domain = new Domain();
        domain.addAction(pressBtn);
        domain.addAction(pressBtn2);
        domain.addAction(pressBtn3);

        State init = new State();
        init.addVariable(btnPressedVar);
        init.addVariable(btn2PressedVar);
        init.addVariable(btn3PressedVar);

        State goal = new State();
        goal.addVariable(new Variable("btnPressed", new Integer(1)));
        goal.addVariable(new Variable("btn2Pressed", new Integer(2)));
        goal.addVariable(new Variable("btn3Pressed", new Integer(3)));

        Problem problem1 = new Problem(domain);
        problem1.setInitialState(init);
        problem1.setGoalState(goal);

        LinkedList<Action> plan = problem1.solve();
        System.out.println("Searchspace built in " + problem1.getLastExecutionTime()[0] + "ms.\nPlan found in " + problem1.getLastExecutionTime()[1] + "ms.");
        for(int i = 0; i < plan.size(); ++i)
            System.out.println(i + ". " + plan.get(i));
    }
}
