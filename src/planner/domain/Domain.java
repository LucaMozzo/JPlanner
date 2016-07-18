package planner.domain;

import planner.problem.State;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * This class puts everything together to form a domain with all its components
 */
public class Domain {

    private LinkedList<IAction> actions;

    /**
     * The constructor only initializes the linked list
     */
    public Domain(){
        actions = new LinkedList<>();
    }

    /**
     * Adds an action to the domain
     * @param action the action
     */
    public void addAction(IAction action){
        actions.add(action);
    }

    /**
     * Return a list of applicable actions on a state
     * @param state the state
     * @return the applicable actions
     */
    public LinkedList<IAction> getApplicableActions(State state){

        LinkedList<IAction> applicableActions = new LinkedList<>();

        //check for each action specified in the domain which can be applied in the given state
        for(IAction a : actions)
            if(a.isApplicable(state))
                applicableActions.add(a);

        return applicableActions;
    }
}
