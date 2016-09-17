package planner.domain;

import planner.types.CustomObject;
import planner.problem.State;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * Created by LUCA on 01/09/2016.
 *
 * Specifies the objects supported by an action
 */
public class Parameter<E extends CustomObject> {

    private Class type;

    /**
     * The constructors stores into type, the class of the parameter E
     */
    public Parameter(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        type = (Class<E>) paramType.getActualTypeArguments()[0];
    }

    /**
     * Getter for the type of the parameter
     * @return the class of the parameter
     */
    public Class getType() {
        return type;
    }

    /**
     * Returns true if obj qualifies to be a valid parameter
     * @param obj the object
     * @return whether it qualifies or not
     */
    public boolean typeMatches(CustomObject obj) {
        return obj.getClass() == type;
    }

    /**
     * Returns a list of objects that qualify as candidates for this parameter
     * @param state the state
     * @return the list of qualified objects
     */
    public LinkedList<CustomObject> getCandidates(State state){
        LinkedList<CustomObject> objects = new LinkedList<>();

        for(CustomObject obj : state.getInstanceObjects())
            if(typeMatches(obj))
                objects.add(obj);

        return objects;
    }
}
