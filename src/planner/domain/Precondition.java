package planner.domain;

import planner.types.CustomObject;
import planner.types.Duplicable;
import utils.Comparison;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E extends Duplicable> extends Fact<E> {

    private Comparison operator;

    /**
     * The constructor takes the variable and its expected value
     * @param varName the variable name
     * @param expValue the expected value
     */
    public Precondition(String varName, E expValue){
        this(varName, expValue, Comparison.EQUAL); //by default we set the equals operator
    }

    /**
     * The constructor takes the variable, its expected value and the operator
     * @param varName the variable name
     * @param expValue the expected value
     * @param operator the operator
     */
    public Precondition(String varName, E expValue, Comparison operator){
        super(varName, expValue);

        this.operator = operator;
    }

    /**
     * Allows to specify a precondition with no expected value
     * To be used only with NULL and NOT_NULL
     * @param varName the name of the variable
     * @param operator the operator - either NULL or NOT_NULL
     */
    public Precondition(String varName, Comparison operator){
        super(varName, null);

        this.operator = operator;
    }

    /**
     * Determines if the property within the given object satisfied the required attribute
     * If the variable is not Comparable<> an exception is thrown
     * @param obj the object
     * @return whether is satisfied or not
     * @throws ClassCastException if the type of the variable is not comparable
     */
    public boolean isSatisfied(CustomObject obj) throws ClassCastException {
        Variable var = obj.getPropertyByName(varName);
        switch(operator){
            case EQUAL:
                return value.equals(var.getValue());
            case NOT_EQUAL:
                return !value.equals(var.getValue());
            case GREATER:
                return ((Comparable<E>) var.getValue()).compareTo(value) > 0;
            case LESS:
                return ((Comparable<E>) var.getValue()).compareTo(value) < 0;
            case GREATER_OR_EQUAL:
                return ((Comparable<E>) var.getValue()).compareTo(value) >= 0;
            case LESS_OR_EQUAL:
                return ((Comparable<E>) var.getValue()).compareTo(value) <= 0;
            case NULL:
                return obj == null;
            case NOT_NULL:
                return obj != null;
        }
        return false;
    }

}
