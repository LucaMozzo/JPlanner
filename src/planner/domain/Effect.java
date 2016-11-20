package planner.domain;

import planner.problem.State;
import planner.types.CustomObject;
import planner.types.DataType;
import planner.types.DefaultDataType;
import planner.types.NumericDataType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.exceptions.OperationNotSupportedException;
import utils.Operation;
import utils.Validation;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An operation is a fact that becomes true after an action
 */
public class Effect<E extends DefaultDataType> extends Fact<E>{

    protected Operation operation;
    protected DataType argument;
    protected Parameter param;

    /**
     * The constructor takes the variable the operation on it
     * @param var the variable name
     * @param param the parameter on which apply the effect
     * @param operation the expected value
     */
    public Effect(String var, Parameter param, Operation operation){
        super(var);

        this.operation = operation;
        this.param = param;

        //check if the argument is needed
        if(!Validation.checkType(operation, false))
            throw new IllegalArgumentException("The operation " + operation.toString() + " needs an argument");
    }

    /**
     * The constructor takes the variable the operation on it and an argument
     * @param var the variable name
     * @param param the parameter on which apply the effect
     * @param operation the expected value
     * @param arg the argument
     */
    public Effect(String var, Parameter param, Operation operation, DataType arg){
        super(var);

        this.operation = operation;
        this.argument = arg;

        //check if the argument is needed
        if(!Validation.checkType(operation, true))
            throw new IllegalArgumentException("The operation " + operation.toString() + " doesn't need an argument");
    }

    /**
     * Apply the operation as an effect of an action to the given object
     * @param obj the target
     * @throws OperationNotSupportedException if the operator cannot be applied to the given type
     */
    public void apply(CustomObject obj) throws OperationNotSupportedException {
        Variable var = obj.getPropertyByName(varName);

        applyOperation(operation, var, argument);
    }

    /**
     * Returns the parameter where the effect will be applied
     * @return the parameter
     */
    public Parameter getParameter() { return param; }

    /**
     * Applies the operation to the variable
     * @param operation the operation
     * @param var the variable
     * @param argument the argument
     * @throws OperationNotSupportedException if the operation is not supported by the type
     */
    public static void applyOperation(Operation operation, Variable var, DataType argument)throws OperationNotSupportedException{
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
