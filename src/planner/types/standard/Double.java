package planner.types.standard;

import planner.types.DataType;
import planner.types.DefaultDataType;
import planner.types.NumericDataType;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Double data type
 */
public class Double extends DefaultDataType implements  Comparable<Double> {

    protected double value;

    /**
     * The constructor assigns a value to the variable type
     * @param value the value
     */
    public Double(double value){
        this.value = value;
    }

    /**
     * Getter for the value
     * @return the value
     */
    public double getValue(){
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
        if(toSum instanceof Double)
            value += ((Double)toSum).value;
        else if(toSum instanceof Integer)
            value += ((Integer)toSum).getValue();
        else
            throw new ClassCastException("Impossible to sum <" + toSum.getClass() + "> to <Double>");
    }
    
    @Override
    public void onSubtract(DataType toSub) throws ClassCastException{
        if(toSub instanceof Double)
            value -= ((Double)toSub).value;
        else if(toSub instanceof Integer)
            value -= ((Integer)toSub).getValue();
        else
            throw new ClassCastException("Impossible to subtract <" + toSub.getClass() + "> from <Double>");
    }
    
    @Override
    public void onMultiply(DataType toMult) throws ClassCastException{
        if(toMult instanceof Double)
            value *= ((Double)toMult).value;
        else if(toMult instanceof Integer)
            value *= ((Integer)toMult).getValue();
        else
            throw new ClassCastException("Impossible to multiply <" + toMult.getClass() + "> by <Double>");
    }

    @Override
    public void onDivide(DataType toDiv) throws ClassCastException{
        if(toDiv instanceof Double)
            value /= ((Double)toDiv).value;
        else if(toDiv instanceof Integer)
            value /= ((Integer)toDiv).getValue();
        else
            throw new ClassCastException("Impossible to divide <Double> by <" + toDiv.getClass() + ">");
    }

    @Override
    public void onSquare(){
        value *= value;
    }
    
    @Override
    public void onSqrt(){
        value = Math.sqrt(value);
    }
    
    @Override
    public void onPower(DataType index) throws ClassCastException{
        if(index instanceof Double)
            Math.pow(value, ((Double)index).getValue());
        else if(index instanceof Integer)
            Math.pow(value, ((Integer)index).getValue());
        else
            throw new ClassCastException("Impossible to have an index of type <" + index.getClass() + ">");
    }
    
    @Override
    public void onAssign(DataType newValue) throws ClassCastException{
        if(newValue instanceof Double)
            value = ((Double)newValue).getValue();
        else if(newValue instanceof Integer)
            value = ((Integer)newValue).getValue();
        else
            throw new ClassCastException("Impossible to assign <" + newValue.getClass() + "> to <Double>");
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Double && value == ((Double) other).value;
    }

    @Override
    public String toString(){
        return "" + value;
    }

    @Override
    public int compareTo(Double other) {
        if(value == other.value)
            return 0;
        else
            return value > other.value ? 1 : -1;
    }

    @Override
    public Double copyByValue(){
        return new Double(value);
    }
}