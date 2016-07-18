package planner.domain;

import planner.problem.State;
import planner.types.CustomObject;
import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 14/07/2016.
 *
 * An abstraction of an effect
 */
public interface IEffect {

    /**
     * Apply the operation as an effect of an action to the given state
     * @param state the state
     * @throws OperationNotSupportedException if the operator cannot be applied to the given type
     */
    void apply(State state) throws OperationNotSupportedException;

    /**
     * Apply the operation as an effect of an action to the given customObject
     * @param customObject the customObject
     * @throws OperationNotSupportedException if the operator cannot be applied to the given type
     */
    void apply(CustomObject customObject) throws OperationNotSupportedException;
}
