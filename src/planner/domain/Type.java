package planner.domain;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A data type in the planner (unique in each domain, identified by the name)
 */
public class Type {

    private String name;

    /**
     * The constructor only takes the name as argument
     * @param name the name
     */
    public Type(String name){
        this.name = name;
    }

    /**
     * Getter for the name
     * @return the name
     */
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object otherType){
        if(otherType instanceof Type)
            return name.equals(((Type)otherType).getName());
        else
            return false;
    }

    @Override
    public String toString(){
        return name;
    }
}
