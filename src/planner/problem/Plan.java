package planner.problem;

import planner.domain.Action;

import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A plan is a sequence of actions that bring the system from the Initial to the goal state
 */
public class Plan {

    private LinkedList<Action> actions;

    /**
     * The default constructor initializes the list of actions
     */
    public Plan(){
        actions = new LinkedList<>();
    }

    /**
     * Overloaded constructor inherits an already existing list of actions
     * @param actions the actions
     */
    public Plan(LinkedList<Action> actions){
        this.actions = new LinkedList<>(actions);
    }

    /**
     * Add an action at the end of the sequence
     * @param action the action
     */
    public void addAction(Action action){
        actions.add(action);
    }

    /**
     * Get the plan as a sequence of actions
     * @return the plan
     */
    public LinkedList<Action> getPlan(){
        return actions;
    }

    @Override
    public String toString(){
        String tmp = "";
        while(actions.iterator().hasNext())
            tmp += actions.iterator().next() + "\n";
        return tmp;
    }
}
