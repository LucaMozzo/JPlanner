package utils;

/**
 * Created by LUCA on 20/05/2016.
 *
 * A set of operations on default data types
 */
public enum Operation {
    INCREASE("Increase"), DECREASE("Decrease"), ASSIGN("Assign"), MULTIPLY("Multiply"), DIVIDE("Divide"), SUM("Sum"), SUBTRACT("Subtract"),
    SQUARE("Square"), SQRT("Square root"), POWER("Power"), SET_TRUE("Set true"), SET_FALSE("Set false");

    private String name;

    //used to print the name of the operation
    private Operation(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
