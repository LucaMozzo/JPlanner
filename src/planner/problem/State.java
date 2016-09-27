package planner.problem;

import planner.types.CustomObject;
import utils.exceptions.DuplicateVariableNameException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A state represents an initial state, a goal or whatever falls between these two
 */
public class State {

    protected LinkedList<CustomObject> customObjects;

    /**
     * The constructor initializes the list of objects
     */
    public State(){
        customObjects = new LinkedList<>();
    }

    /**
     * Overloaded constructor allows to inherit objects from another state
     * This method also has to create NEW customObjects to prevent the parent's variables to point to the children's ones
     * @param objs the objects
     */
    public State(LinkedList<CustomObject> objs){
        customObjects = new LinkedList<>();
        for(CustomObject o : objs)
            customObjects.add(o.duplicate());
    }

    /**
     * Return all the customObjects of this instance
     * @return the customObjects
     */
    public LinkedList<CustomObject> getInstanceObjects() {
        return customObjects;
    }

    /**
     * Add an object to the list
     * @param obj the object
     */
    public void addObject(CustomObject obj) {
        if(!customObjects.contains(obj))
            customObjects.add(obj);
    }

    /**
     * Add a collection of customObjects to the list
     * @param objs the collection of customObjects
     */
    public void addObjects(Collection<CustomObject> objs){
        for(CustomObject o : objs)
            addObject(o);
    }

    /**
     * Utility to convert a State to a TreeState
     * @param state the state to be converted
     * @return the converted TreeState
     */
    public static TreeState convertToTreeState(State state) throws DuplicateVariableNameException {
        TreeState ts = new TreeState();
        ts.addObjects(state.getInstanceObjects());

        return ts;
    }

    /**
     * Returns all the customObjects of the given type
     * @param type the type
     * @return a list of objects of that type
     */
    public LinkedList<CustomObject> getObjectsByType(Class type){
        LinkedList<CustomObject> tmp = new LinkedList<>();

        for(CustomObject o : customObjects)
            if(o.getClass() == type)
                tmp.add(o);

        return tmp;
    }

    /**
     * Returns all the customObjects
     * @return all the objects in this state
     */
    public LinkedList<CustomObject> getObjects() {
        return customObjects;
    }

    @Override
    public String toString(){ //TODO fix this
        /*String tmp = "Variables: [";
        for(Variable v : variables)
            tmp += v + ", ";
        return tmp.length() > 13 ? tmp.substring(0, tmp.length() - 2) + "]" : tmp + "]";*/
        return "";
    }

    @Override
    public boolean equals(java.lang.Object other){
        if(other instanceof State){
            //checking the size first saves time if it's not necessary to loop through the array
            if(customObjects.size() != ((State) other).customObjects.size())
                return false;

            Iterator iterator = customObjects.iterator();
            Iterator otherIterator = ((State) other).customObjects.iterator();

            while(iterator.hasNext()){
                if(!iterator.next().equals(otherIterator.next()))
                    return false;
            }

            return true;
        }
        else
            return false;
    }
}
