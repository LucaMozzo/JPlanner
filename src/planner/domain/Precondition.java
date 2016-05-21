package planner.domain;

import utils.Comparisons;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E> extends Fact<E> {

    private Comparisons operator;

    /**
     * The constructor takes the variable and its expected value
     * @param var the variable
     * @param expValue the expected value
     */
    public Precondition(Variable var, E expValue){
        this(var, expValue, Comparisons.EQUAL); //by default we set the equals operator
    }

    /**
     * The constructor takes the variable, its expected value and the operator
     * @param var the variable
     * @param expValue the expected value
     * @param operator the operator
     */
    public Precondition(Variable var, E expValue, Comparisons operator){
        super(var, expValue);

        this.operator = operator;
    }

    /**
     * Determines if the variable got the required attribute
     * If the variable is not Comparable<> an exception is thrown
     * @return whether is satisfied or not
     * @throws ClassCastException if the type of the variable is not comparable
     */
    public boolean isSatisfied() throws ClassCastException{
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
