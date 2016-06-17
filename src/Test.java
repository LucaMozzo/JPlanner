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

        /*Tree plan = createPlan();
        System.out.println("Explored " + plan.getSize() + " nodes");

        Iterator it = plan.iterator();

        while(it.hasNext())
            System.out.println("\n" + ((TreeState)((Node)it.next()).getElement()).getActions());*/


        Tree plan = new Tree();
        Node<java.lang.Integer> root = new Node<>(0);
        Node<java.lang.Integer> a = new Node<>(1);
        Node<java.lang.Integer> b = new Node<>(2);
        Node<java.lang.Integer> c = new Node<>(3);
        Node<java.lang.Integer> d = new Node<>(4);
        Node<java.lang.Integer> e = new Node<>(5);
        Node<java.lang.Integer> f = new Node<>(6);
        Node<java.lang.Integer> g = new Node<>(7);
        Node<java.lang.Integer> h = new Node<>(8);

        plan.addNode(root); //root
        plan.addNode(a, root);
        plan.addNode(b, a);
        plan.addNode(c, b);
        plan.addNode(d, b);
        plan.addNode(e, a);
        plan.addNode(f,root);
        plan.addNode(g,f);
        plan.addNode(h,root);


        System.out.println("Explored " + plan.getSize() + " nodes");

        Iterator it = plan.iterator();

        while(it.hasNext())
            System.out.println(((Node)it.next()).getElement());
    }

    private static Tree createPlan() throws OperationNotSupportedException {
        Variable<Integer> btnPressedVar = new Variable<>("btnPressed", new Integer(0));
        Variable<Integer> btn2PressedVar = new Variable<>("btn2Pressed", new Integer(0));

        Precondition<Integer> btnPressedPre = new Precondition<>(btnPressedVar, new Integer(1), Comparisons.LESS_OR_EQUAL);
        Precondition<Integer> btn2PressedPre = new Precondition<>(btn2PressedVar, new Integer(4), Comparisons.LESS);

        Effect<Integer> btnPressedEff = new Effect<>(btnPressedVar, Operations.INCREASE);
        Effect<Integer> btn2PressedEff = new Effect<>(btn2PressedVar, Operations.INCREASE);

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
