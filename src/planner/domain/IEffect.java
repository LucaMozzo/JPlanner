package planner.domain;

import utils.exceptions.OperationNotSupportedException;
import planner.problem.State;

/**
 * Created by LUCA on 14/07/2016.
 *
 * An abstraction of an effect
 */
public interface IEffect {

    /**
     * Apply the operation as an effect of an action to the given state
     * @param state the state
     */
    void apply(State state) throws OperationNotSupportedException;
}
