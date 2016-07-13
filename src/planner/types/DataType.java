package planner.types;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Contains the default methods that a type has to have in order to be used
 */
public interface DataType{

    /**
     * When assign is applied on the data type
     * @param value the new value
     */
    void onAssign(DataType value) throws utils.exceptions.OperationNotSupportedException;

    /**
     * When set true is applied on the data type
     */
    void onSetTrue() throws utils.exceptions.OperationNotSupportedException;

    /**
     * When set false is applied on the data type
     */
    void onSetFalse() throws utils.exceptions.OperationNotSupportedException;
}
