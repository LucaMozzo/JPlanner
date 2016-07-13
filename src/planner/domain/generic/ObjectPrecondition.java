package planner.domain.generic;

import planner.domain.IPrecondition;
import planner.domain.Variable;
import planner.problem.State;
import planner.types.DefaultDataType;
import planner.types.Object;
import utils.Comparison;

import java.util.LinkedList;

/**
 * Created by LUCA on 13/07/2016.
 *
 * A precondition that works on types instead of instances
 */
public class ObjectPrecondition<T extends Object, E extends DefaultDataType> implements IPrecondition{

    private Comparison operator;
    private String propertyName;
    private E expectedValue;
    private final T TYPE;

    /**
     * The default constructor requires all the parameters to define a generic precondition
     * @param objectType the type of object allowed (it should match the action's one)
     * @param property the property name of the object on which check the precondition
     * @param expectedValue the expected value of the property in order for the precondition to be satisfied
     * @param operator the comparison operator used between variable value and its expected value
     */
    public ObjectPrecondition(T objectType, String property, E expectedValue, Comparison operator) {
        TYPE = objectType;
        this.propertyName = property;
        this.expectedValue = expectedValue;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfied(State state) throws ClassCastException {
        LinkedList<Object> objs = state.getObjectsByType(TYPE.getClass());
        return false;
    }
}
