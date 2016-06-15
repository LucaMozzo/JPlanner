package planner.domain;

import planner.problem.State;
import planner.types.DefaultDataType;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;
import utils.Operations;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An effect is a fact that becomes true after an action
 */
public class Effect<E extends DefaultDataType> extends Fact<E> {

    Operations effect;

    /**
     * The constructor takes the variable the effect on it
     * @param var the variable
     * @param effect the expected value
     */
    public Effect(Variable var, Operations effect){
        super(var);

        this.effect = effect;
    }

    /**
     * Apply the value change in the given problem
     * @param state the problem
     */
    public void apply(State state) throws OperationNotSupportedException {
        switch(effect){
            case INCREASE:
                ((Integer)var.getValue()).onIncrease();
                //TODO operations
        }
    }

}
