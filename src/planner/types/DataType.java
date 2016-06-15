package planner.types;

import javax.naming.*;

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
    void onAssign(DataType value) throws OperationNotSupportedException;

    /**
     * When set true is applied on the data type
     */
    void onSetTrue() throws OperationNotSupportedException;

    /**
     * When set false is applied on the data type
     */
    void onSetFalse() throws OperationNotSupportedException;
}
