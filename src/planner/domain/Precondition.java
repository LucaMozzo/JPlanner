package planner.domain;

import planner.problem.State;
import planner.types.CustomObject;
import planner.types.DefaultDataType;
import planner.types.standard.Integer;
import utils.Comparison;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E extends DefaultDataType, T extends CustomObject> extends Fact<E> {

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
        }
        return false;
    }

}
