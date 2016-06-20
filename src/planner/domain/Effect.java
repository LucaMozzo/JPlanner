package planner.domain;

import planner.problem.State;
import planner.types.DataType;
import planner.types.DefaultDataType;
import planner.types.NumericDataType;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;
import utils.Operations;
import utils.Validation;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An operation is a fact that becomes true after an action
 */
public class Effect<E extends DefaultDataType> extends Fact<E> {

    Operations operation;
    DataType argument;

    /**
     * The constructor takes the variable the operation on it
     * @param var the variable name
     * @param operation the expected value
     */
    public Effect(String var, Operations operation){
        super(var);

        this.operation = operation;

        //check if the arguments is needed
        if(!Validation.checkType(operation, false))
            throw new IllegalArgumentException("The operation " + operation.toString() + " needs an argument");
    }

    /**
     * The constructor takes the variable the operation on it and an argument
     * @param var the variable name
     * @param operation the expected value
     * @param arg the argument
     */
    public Effect(String var, Operations operation, DataType arg){
        super(var);

        this.operation = operation;
        this.argument = arg;

        //check if the arguments is needed
        if(!Validation.checkType(operation, true))
            throw new IllegalArgumentException("The operation " + operation.toString() + " doesn't need an argument");
    }

    /**
     * Apply the value change in the given problem
     * @param state the problem
     */
    public void apply(State state) throws OperationNotSupportedException {
        Variable var = state.getVariableByName(varName);
        switch(operation){
            case INCREASE:
                ((NumericDataType)var.getValue()).onIncrease();
                break;
            case DECREASE:
                ((NumericDataType)var.getValue()).onDecrease();
                break;
            case SUM:
                ((NumericDataType)var.getValue()).onSum(argument);
                break;
            case SUBTRACT:
                ((NumericDataType)var.getValue()).onSubtract(argument);
                break;
            case MULTIPLY:
                ((NumericDataType)var.getValue()).onMultiply(argument);
                break;
            case DIVIDE:
                ((NumericDataType)var.getValue()).onDivide(argument);
                break;
            case ASSIGN:
                ((NumericDataType)var.getValue()).onAssign(argument);
                break;
            case SQRT:
                ((NumericDataType)var.getValue()).onSqrt();
                break;
            case SQUARE:
                ((NumericDataType)var.getValue()).onSquare();
                break;
            case POWER:
                ((NumericDataType)var.getValue()).onPower(argument);
                break;
            case SET_TRUE:
                ((DataType)var.getValue()).onSetTrue();
                break;
            case SET_FALSE:
                ((DataType)var.getValue()).onSetFalse();
                break;
        }
    }
}
