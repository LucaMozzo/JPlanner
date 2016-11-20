package planner.domain;

import javafx.util.Pair;
import planner.types.CustomObject;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by lucam on 20/11/2016.
 *
 * Represents set of parameters with an object assigned to each of them
 */
public class ParameterSet{//TODO implement all

    protected Hashtable<Parameter, CustomObject> associations;

    /**
     * The constuctor just initialises the table
     */
    public ParameterSet(){ associations = new Hashtable<>();}

    /**
     * Inserts a parameter-object association
     * @param param the parameter
     * @param obj the object
     */
    public void addAssociation(Parameter param, CustomObject obj){
        associations.put(param, obj);
    }

    /**
     * Get the object associated with that parameter
     * @param param the parameter
     * @return the object
     */
    public CustomObject getAssociated(Parameter param){return associations.get(param);}
}
