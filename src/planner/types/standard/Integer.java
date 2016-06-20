package planner.types.standard;

import planner.types.DataType;
import planner.types.DefaultDataType;
import planner.types.NumericDataType;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Integer data type
 */
public class Integer extends DefaultDataType implements Comparable<Integer>{

    protected int value;

    /**
     * The constructor assigns a value to the variable type
     * @param value the value
     */
    public Integer(int value){
        this.value = value;
    }

    /**
     * Getter for the value
     * @return the value
     */
    public int getValue(){
        return value;
    }

    @Override
    public void onIncrease(){
        ++value;
    }

    @Override
    public void onDecrease(){
        --value;
    }

    @Override
    public void onSum(DataType toSum) throws ClassCastException{
        if(toSum instanceof Integer)
            value += ((Integer)toSum).value;
        else
            throw new ClassCastException("Impossible to sum <" + toSum.getClass() + "> to <Integer>");
    }

    @Override
    public void onSubtract(DataType toSub) throws ClassCastException{
        if(toSub instanceof Integer)
            value -= ((Integer)toSub).value;
        else
            throw new ClassCastException("Impossible to subtract <" + toSub.getClass() + "> from <Integer>");
    }

    @Override
    public void onMultiply(DataType toMult) throws ClassCastException{
        if(toMult instanceof Integer)
            value *= ((Integer)toMult).value;
        else
            throw new ClassCastException("Impossible to multiply <" + toMult.getClass() + "> by <Integer>");
    }

    @Override
    public void onDivide(DataType toDiv) throws ClassCastException{
        if(toDiv instanceof Integer)
            value /= ((Integer)toDiv).value;
        else
            throw new ClassCastException("Impossible to divide <Integer> by <" + toDiv.getClass() + ">");
    }

    @Override
    public void onSquare(){
        value *= value;
    }

    @Override
    public void onPower(DataType index) throws ClassCastException{
        //we only allow indexes > 0 for integer types
        if(index instanceof Integer && ((Integer)index).value >= 0)
            Math.pow(value, ((Integer)index).value);
        else
            throw new ClassCastException("Impossible to have an index of type <" + index.getClass() + "> or less than 0 for type <Integer>");
    }

    @Override
    public void onAssign(DataType newValue) throws ClassCastException{
        if(newValue instanceof Integer)
            value = ((Integer)newValue).value;
        else
            throw new ClassCastException("Impossible to assign <" + newValue.getClass() + "> to <Integer>");
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Integer && value == ((Integer) other).value;
    }

    @Override
    public String toString(){
        return "" + value;
    }

    @Override
    public int compareTo(Integer other) {
        return value - other.value;
    }

    @Override
    public Integer copyByValue(){
        return new Integer(value);
    }
}
