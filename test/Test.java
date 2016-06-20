import planner.domain.Variable;
import planner.problem.State;
import planner.searchspace.datastructures.Tree;
import planner.searchspace.search.DFS;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;

/**
 * Created by LUCA on 20/06/2016.
 */
public class Test {

    public static void main(String[] args) throws OperationNotSupportedException {
        Tree searchspace = Test_Searchspace_Generation.createPlan();
        System.out.println("Explored " + searchspace.getSize() + " nodes");


        State goal = new State();
        goal.addVariable(new Variable<Integer>("btnPressed", new Integer(1)));
        goal.addVariable(new Variable<Integer>("btn2Pressed", new Integer(2)));
        //problem1.setGoalState(goal); just for testing

        System.out.println("Plan: " + DFS.search(searchspace, State.convertToTreeState(goal)));
    }
}
