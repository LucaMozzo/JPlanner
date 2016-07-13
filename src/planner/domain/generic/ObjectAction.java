package planner.domain.generic;

import planner.domain.IAction;
import planner.problem.State;
import planner.types.Object;
import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An action that is applied on any object of the given typoe
 */
public class ObjectAction<T extends Object> implements IAction {

    private final T TYPE;

    /**
     * The default constructor takes the type of object
     * @param objectType the type of the object
     */
    public ObjectAction(T objectType) {
        TYPE = objectType;
    }

    @Override
    public boolean isApplicable(State state) {
        return false;
    }

    @Override
    public void applyEffects(State state) throws OperationNotSupportedException {

    }
}
