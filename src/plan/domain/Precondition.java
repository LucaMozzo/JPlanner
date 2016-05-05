package plan.domain;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E> extends Fact<E> {

    /**
     * The constructor takes the variable and its expected value
     * @param var the variable
     * @param expValue the expected value
     */
    public Precondition(Variable var, E expValue){
        super(var, expValue);
    }

    /**
     * Determines if the variable got the expected result
     * @return whether is satisfied or not
     */
    public boolean isSatisfied(){
        return value.equals(var.getValue());
    }

}
