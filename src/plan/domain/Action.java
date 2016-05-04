package plan.domain;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An action in order to start needs the preconditions to be satisfied
 */
public class Action {

    private LinkedList<Effect> effects;
    private LinkedList<Precondition> preconditions;

    /**
     * The constructor takes the basic arguments for defining an action
     * @param preconditions the preconditions
     * @param effects the effects
     */
    public Action(LinkedList<Precondition> preconditions, LinkedList<Effect> effects){
        this.preconditions = preconditions;
        this.effects = effects;
    }

    /**
     * Getter for the preconditions
     * @return the preconditions
     */
    public LinkedList<Precondition> getPreconditions(){
        return preconditions;
    }

    /**
     * Getter for the effects
     * @return the effects
     */
    public LinkedList<Effect> getEffects(){
        return effects;
    }
}
