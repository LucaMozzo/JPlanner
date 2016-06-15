package planner.types.standard;

import planner.types.DefaultDataType;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Boolean data type
 */
public class Boolean extends DefaultDataType {

    protected boolean value;

    /**
     * The constructor assigns a value to the variable type
     * @param value the value
     */
    public Boolean(boolean value){
        this.value = value;
    }

    /**
     * Getter for the value
     * @return the value
     */
    public boolean getValue(){
        return value;
    }

    @Override
    public void onSetTrue(){
        value = true;
    }

    @Override
    public void onSetFalse(){
        value = false;
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Boolean && value == ((Boolean) other).value;
    }

    @Override
    public String toString(){
        return value == true ? "true" : "false";
    }
}
