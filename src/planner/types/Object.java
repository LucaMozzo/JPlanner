package planner.types;

import planner.domain.Variable;

import java.util.ArrayList;


/**
 * Created by LUCA on 30/06/2016.
 *
 * Represents a custom object
 */
public abstract class Object {

    protected ArrayList<Variable> properties = new ArrayList<>();

    /**
     * Add a property to the list
     * @param property the property to be added
     */
    public void addProperty(Variable property){
        properties.add(property);
    }

    /**
     * Returns an iterable collection of properties
     * @return the properties
     */
    public ArrayList<Variable> getProperties(){
        return properties;
    }

    /**
     * Returns the property with the give name
     * @param name the name
     * @return the property
     */
    public Variable getProperty(String name) {
        for(Variable v : properties)
            if(v.getName().equals(name))
                return v;
        return null;
    }

    /**
     * Set a new value for a property
     * @param name the name of the property
     * @param newValue the new value
     */
    public void setProperty(String name, DefaultDataType newValue) throws Exception{
        for(Variable v : properties)
            if(v.getName().equals(name)) {
                v.setValue(newValue);
                return;
            }
        throw new Exception("No variable called \"" + name + "\" in current context");
    }
}
