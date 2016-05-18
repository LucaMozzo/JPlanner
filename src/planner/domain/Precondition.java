package planner.domain;

import utils.ComparisonOperator;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E> extends Fact<E> {

    private ComparisonOperator operator;

    /**
     * The constructor takes the variable and its expected value
     * @param var the variable
     * @param expValue the expected value
     */
    public Precondition(Variable var, E expValue){
        this(var, expValue, ComparisonOperator.EQUAL); //by default we set the equals operator
    }

    /**
     * The constructor takes the variable, its expected value and the operator
     * @param var the variable
     * @param expValue the expected value
     * @param operator the operator
     */
    public Precondition(Variable var, E expValue, ComparisonOperator operator){
        super(var, expValue);

        this.operator = operator;
    }

    /**
     * Determines if the variable got the expected result
     * @return whether is satisfied or not
     */
    public boolean isSatisfied(){
        return value.equals(var.getValue());
    }

}
