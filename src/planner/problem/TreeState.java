package planner.problem;

import planner.domain.Action;
import planner.types.CustomObject;

import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A TreeState is a State that also keeps track of the actions applied from the initial state to it
 */
public class TreeState extends State {

    //keep track of the actions applied so far
    private LinkedList<Action> actions;

    /**
     * If the state is a root state, it doesn't inherit any sequence of actions
     */
    public TreeState(){
        super();

        actions = new LinkedList<>();
    }

    /**
     * In most cases we want a children in the tree to have the plan of the parent to which add more actions
     * @param parentPlan the plan of the parent
     */
    public TreeState(LinkedList<Action> parentPlan, LinkedList<CustomObject> objs){
        super(objs);

        actions = new LinkedList<>(parentPlan);
    }

    /**
     * Add an action to keep track of
     * @param action the action
     */
    public void addAction(Action action){
        actions.add(action);
    }

    /**
     * Getter for the list of actions
     * @return the list of actions
     */
    public LinkedList<Action> getActions(){
        return actions;
    }

    @Override
    public String toString(){
        String tmp = "Actions: [";
        String tmp2 = "Objects: [";

        for(Action a : actions)
            tmp += a + ", ";

        for(CustomObject v : customObjects)
            tmp2 += v + ", ";

        tmp = tmp.length() > 11 ? tmp.substring(0, tmp.length() - 2) + "]" : tmp + "]";
        tmp2 = tmp2.length() > 13 ? tmp2.substring(0, tmp2.length() - 2) + "]" : tmp2 + "]";

        return tmp2 + " " + tmp;
    }
}
