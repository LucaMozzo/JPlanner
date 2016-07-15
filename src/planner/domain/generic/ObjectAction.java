package planner.domain.generic;

import planner.domain.IAction;
import planner.problem.State;
import planner.types.Object;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.exceptions.OperationNotSupportedException;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An action that is applied on any object of the given type
 */
public class ObjectAction<T extends Object> implements IAction {

    private final T TYPE;
    private String name;
    private LinkedList<ObjectPrecondition> preconditions;
    private LinkedList<ObjectEffect> effects;

    /**
     * The default constructor takes the type of object
     * @param objectType the type of the object
     * @param name the name of the action
     */
    public ObjectAction(T objectType, String name) {
        TYPE = objectType;
        preconditions = new LinkedList<>();
        effects = new LinkedList<>();
        this.name = name;
    }

    /**
     * Complete constructor taking previously-created preconditions and effects
     * @param objectType the type of the object
     * @param name the name of the action
     * @param preconditions the preconditions
     * @param effects the effects
     */
    public ObjectAction(T objectType, String name, LinkedList<ObjectPrecondition> preconditions, LinkedList<ObjectEffect> effects){
        TYPE = objectType;
        this.preconditions = preconditions;
        this.effects = effects;
        this.name = name;
    }

    /**
     * Returns a list of objects that satisfy all the preconditions
     * @param state the state
     * @return the qualified objects
     */
    public LinkedList<Object> getQualifiedObjects(State state){
        LinkedList<Object> qualified = new LinkedList<>();

        Iterator it = preconditions.iterator();
        if(it.hasNext()) {
            ObjectPrecondition current = (ObjectPrecondition) it.next();
            qualified = current.getQualifiedObjects(state);
            while(it.hasNext())
                qualified = getCommonObjects(qualified, ((ObjectPrecondition) it.next()).getQualifiedObjects(state));
        }
        return qualified;
    }

    /**
     * Returns a list of objects that are contained in both lists
     * @param list1 the first list
     * @param list2 the second list
     * @return the common objects
     */
    private static LinkedList<Object> getCommonObjects(LinkedList<Object> list1, LinkedList<Object> list2) {
        LinkedList<Object> commonObjs = new LinkedList<>();

        for(Object obj : list1)
            if(list2.contains(obj))
                commonObjs.add(obj);

        return commonObjs;
    }

    @Override
    public boolean isApplicable(State state) {
        for(ObjectPrecondition op : preconditions)
            if(!op.isSatisfied(state))
                return false;
        return true;
    }

    @Override
    public void applyEffects(State state) throws OperationNotSupportedException {
        //not needed
        throw new NotImplementedException();
    }

    @Override
    public void applyEffects(Object object) throws OperationNotSupportedException {
        for(ObjectEffect oe : effects)
            oe.apply(object);
    }

    @Override
    public String toString() {
        return name;
    }
}
