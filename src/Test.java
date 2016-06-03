import planner.problem.TreeState;
import planner.searchspace.build.TreeBuilder;
import planner.domain.*;
import planner.problem.Problem;
import planner.problem.State;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import utils.Comparisons;
import utils.Effects;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 18/05/2016.
 */
public class Test {

    public static void main(String[] args){

        Tree plan = createPlan();
        System.out.println("Explored " + plan.getSize() + " nodes");

        Iterator it = plan.iterator();

        while(it.hasNext())
            System.out.println("\n" + ((TreeState)((Node)it.next()).getElement()).getActions());
    }

    private static Tree createPlan(){
        Variable<Integer> btnPressedVar = new Variable<>("btnPressed", 0);
        Precondition<Integer> btnPressedPre = new Precondition<>(btnPressedVar, 1, Comparisons.LESS_OR_EQUAL);
        Effect<Integer> btnPressedEff = new Effect<>(btnPressedVar, Effects.INCREASE);
        LinkedList<Precondition> pres = new LinkedList<>();
        pres.add(btnPressedPre);
        LinkedList<Effect> effs = new LinkedList<>();
        effs.add(btnPressedEff);
        Action pressBtn = new Action("press the button", pres, effs);

        Domain domain = new Domain();
        domain.addAction(pressBtn);

        State init = new State();
        init.addVariable(btnPressedVar);

        Problem problem1 = new Problem(domain);
        problem1.setInitialState(init); //lets skip the goal for now

        return TreeBuilder.build(problem1, domain);
    }
}
