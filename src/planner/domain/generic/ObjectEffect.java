package planner.domain.generic;

import planner.domain.Effect;
import planner.domain.IEffect;
import planner.domain.Variable;
import planner.problem.State;
import planner.types.CustomObject;
import planner.types.DefaultDataType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.Operation;
import utils.Validation;
import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 14/07/2016.
 *
 * An effect that is applied on any object of the given type
 */
public class ObjectEffect<T extends CustomObject, E extends DefaultDataType> implements IEffect {

    private final T TYPE;
    private String propertyName;
    private Operation operation;
    private E arg;

    /**
     * Default constructor
     * @param objectType the object type
     * @param propertyName the property name
     * @param operation the operation
     */
    public ObjectEffect(T objectType, String propertyName, Operation operation){
        TYPE = objectType;
        this.propertyName = propertyName;
        this.operation = operation;

        //check if the argument is needed
        if(!Validation.checkType(operation, false))
            throw new IllegalArgumentException("The operation " + operation.toString() + " needs an argument");
    }

    /**
     * Default constructor
     * @param objectType the object type
     * @param propertyName the property name
     * @param operation the operation
     * @param arg the argument - if supported by the operation
     */
    public ObjectEffect(T objectType, String propertyName, Operation operation, DefaultDataType arg) {
        TYPE = objectType;
        this.propertyName = propertyName;
        this.operation = operation;
        this.arg = (E) arg;

        //check if the argument is needed
        if(!Validation.checkType(operation, true))
            throw new IllegalArgumentException("The operation " + operation.toString() + " doesn't need an argument");
    }

    @Override
    public void apply(State state) throws OperationNotSupportedException {
        //not needed
        throw new NotImplementedException();
    }

    @Override
    public void apply(CustomObject obj) throws OperationNotSupportedException {
        Variable property = obj.getPropertyByName(propertyName);

        Effect.applyOperation(operation, property, arg);
    }
}
