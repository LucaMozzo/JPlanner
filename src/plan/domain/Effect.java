package plan.domain;

import plan.problem.Problem;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An effect is a fact that becomes true after an action
 */
public class Effect<E> extends Fact<E> {

    /**
     * The constructor takes the variable and its new value
     * @param var the variable
     * @param newValue the expected value
     */
    public Effect(Variable var, E newValue){
        super(var, newValue);
    }

    public void apply(Problem problem){

    }

}
