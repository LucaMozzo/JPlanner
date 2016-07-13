package planner.domain;

import planner.problem.State;
import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An interface that contains the methods that every action should have
 */
public interface IAction {

    /**
     * Determines whether the action is currently applicable, by checking preconditions
     * @param state the state on which check if applicable
     * @return true if applicable
     */
    boolean isApplicable(State state);

    /**
     * Applies all the effects after it is performed
     * @param state the state on which apply the effects
     */
    void applyEffects(State state) throws OperationNotSupportedException;
}
