package planner.problem;

import planner.domain.Action;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A TreeState is a State that also keeps track of the action applied from the initial state to it
 */
public class TreeState extends State {

    //we use a Plan data structure to keep track of the actions applied so far
    private Plan actionList;

    /**
     * If the state is a root state, it doesn't inherit any sequence of actions
     */
    public TreeState(){
        super();

        actionList = new Plan();
    }

    /**
     * In most cases we want a children in the tree to have the plan of the parent to which add more actions
     * @param parentPlan the plan of the parent
     */
    public TreeState(Plan parentPlan){
        super();

        actionList = parentPlan;
    }

    /**
     * Add an action to keep track of
     * @param action the action
     */
    public void addAction(Action action){
        actionList.addAction(action);
    }

    /**
     * Getter for the list of actions
     * @return the current plan
     */
    public Plan getPlan(){
        return actionList;
    }
}
