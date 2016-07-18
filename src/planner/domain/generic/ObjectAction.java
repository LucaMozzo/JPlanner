package planner.domain.generic;

import planner.domain.IAction;
import planner.domain.IPrecondition;
import planner.problem.State;
import planner.types.CustomObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.exceptions.OperationNotSupportedException;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An action that is applied on any object of the given type
 */
public class ObjectAction<T extends CustomObject> implements IAction {

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
     * Returns a list of customObjects that satisfy all the preconditions
     * @param state the state
     * @return the qualified customObjects
     */
    public LinkedList<CustomObject> getQualifiedObjects(State state){
        LinkedList<CustomObject> qualified = new LinkedList<>();

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
     * Returns a list of customObjects that are contained in both lists
     * @param list1 the first list
     * @param list2 the second list
     * @return the common customObjects
     */
    private static LinkedList<CustomObject> getCommonObjects(LinkedList<CustomObject> list1, LinkedList<CustomObject> list2) {
        LinkedList<CustomObject> commonObjs = new LinkedList<>();

        for(CustomObject obj : list1)
            if(list2.contains(obj))
                commonObjs.add(obj);

        return commonObjs;
    }

    /**
     * Add a precondition
     * @param precondition the precondition
     */
    public void addPrecondition(ObjectPrecondition precondition) {
        preconditions.add(precondition);
    }

    /**
     * Add an effect
     * @param effect the effect
     */
    public void addEffect(ObjectEffect effect) {
        effects.add(effect);
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
    public void applyEffects(CustomObject customObject) throws OperationNotSupportedException {
        for(ObjectEffect oe : effects)
            oe.apply(customObject);
    }

    @Override
    public String toString() {
        return name;
    }
}
