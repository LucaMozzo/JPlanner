package planner.domain.generic;

import planner.domain.IPrecondition;
import planner.domain.Variable;
import planner.problem.State;
import planner.types.CustomObject;
import planner.types.DefaultDataType;
import utils.Comparison;

import java.util.LinkedList;

/**
 * Created by LUCA on 13/07/2016.
 *
 * A precondition that works on types instead of instances
 */
public class ObjectPrecondition<T extends CustomObject, E extends DefaultDataType> implements IPrecondition{

    private Comparison operator;
    private String propertyName;
    private E expectedValue;
    private final T TYPE;

    /**
     * The default constructor requires all the parameters to define a generic precondition
     * @param objectType the type of object allowed (it should match the action's one)
     * @param property the property name of the object on which check the precondition
     * @param expectedValue the expected value of the property in order for the precondition to be satisfied
     * @param operator the comparison operator used between variable value and its expected value
     */
    public ObjectPrecondition(T objectType, String property, E expectedValue, Comparison operator) {
        TYPE = objectType;
        this.propertyName = property;
        this.expectedValue = expectedValue;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfied(State state) throws ClassCastException {
        LinkedList<CustomObject> objs = state.getObjectsByType(TYPE.getClass());

        for(CustomObject o : objs){
            Variable property = o.getPropertyByName(propertyName);
            if(property != null)//when at least one is satisfied, then return true
                switch(operator){
                    case EQUAL:
                        return expectedValue.equals(property.getValue());
                    case NOT_EQUAL:
                        return !expectedValue.equals(property.getValue());
                    case GREATER:
                        return ((Comparable<E>) property.getValue()).compareTo(expectedValue) > 0;
                    case LESS:
                        return ((Comparable<E>) property.getValue()).compareTo(expectedValue) < 0;
                    case GREATER_OR_EQUAL:
                        return ((Comparable<E>) property.getValue()).compareTo(expectedValue) >= 0;
                    case LESS_OR_EQUAL:
                        return ((Comparable<E>) property.getValue()).compareTo(expectedValue) <= 0;
                }
        }
        return false;
    }

    /**
     * Return a list of Objects that satisfy this precondition
     * @param state the state
     * @return the list of customObjects
     */
    public LinkedList<CustomObject> getQualifiedObjects(State state){
        LinkedList<CustomObject> objs = state.getObjectsByType(TYPE.getClass());
        LinkedList<CustomObject> qualified = new LinkedList<>();

        for(CustomObject o : objs){
            Variable property = o.getPropertyByName(propertyName);
            if(property != null)
                switch(operator){
                    case EQUAL:
                        if(expectedValue.equals(property.getValue())) {qualified.add(o);}
                    case NOT_EQUAL:
                        if(!expectedValue.equals(property.getValue())) {qualified.add(o);}
                    case GREATER:
                        if(((Comparable<E>) property.getValue()).compareTo(expectedValue) > 0) {qualified.add(o);}
                    case LESS:
                        if(((Comparable<E>) property.getValue()).compareTo(expectedValue) < 0) {qualified.add(o);}
                    case GREATER_OR_EQUAL:
                        if(((Comparable<E>) property.getValue()).compareTo(expectedValue) >= 0) {qualified.add(o);}
                    case LESS_OR_EQUAL:
                        if(((Comparable<E>) property.getValue()).compareTo(expectedValue) <= 0) {qualified.add(o);}
                }
        }
        return qualified;
    }
}
