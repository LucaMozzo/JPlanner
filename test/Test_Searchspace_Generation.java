import planner.problem.TreeState;
import planner.searchspace.build.TreeBuilder;
import planner.domain.*;
import planner.problem.Problem;
import planner.problem.State;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.standard.Integer;
import utils.Comparison;
import utils.Operation;

import java.util.Iterator;

/**
 * Created by LUCA on 18/05/2016.
 */
public class Test_Searchspace_Generation {

    public static void main(String[] args) throws Exception {

        Tree searchspace = createPlan();
        System.out.println("Explored " + searchspace.getSize() + " nodes");

        Iterator it = searchspace.iterator();

        while(it.hasNext())
            System.out.println("\n" + ((TreeState)((Node)it.next()).getElement()).getActions());
    }

    public static Tree createPlan() throws Exception {
        Variable<Integer> btnPressedVar = new Variable<>("btnPressed", new Integer(0));
        Variable<Integer> btn2PressedVar = new Variable<>("btn2Pressed", new Integer(0));

        Precondition<Integer> btnPressedPre = new Precondition<>("btnPressed", new Integer(1), Comparison.LESS_OR_EQUAL);
        Precondition<Integer> btn2PressedPre = new Precondition<>("btn2Pressed", new Integer(4), Comparison.LESS);

        Effect<Integer> btnPressedEff = new Effect<>("btnPressed", Operation.INCREASE);
        Effect<Integer> btn2PressedEff = new Effect<>("btn2Pressed", Operation.INCREASE);

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

        Problem problem1 = new Problem(domain);
        problem1.setInitialState(init);

        return TreeBuilder.build(problem1, domain);
    }
}
