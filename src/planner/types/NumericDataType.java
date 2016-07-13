package planner.types;

import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 15/06/2016.
 *
 * A DataType which is also a number
 */
public interface NumericDataType extends DataType{
    /**
     * When increase is applied on the data type
     */
    void onIncrease() throws OperationNotSupportedException;

    /**
     * When decrease is applied on the data type
     */
    void onDecrease() throws OperationNotSupportedException;

    /**
     * When sum is applied on the data type
     * @param value the value to sum
     */
    void onSum(DataType value) throws OperationNotSupportedException;

    /**
     * When subtract is applied on the data type
     * @param value the value to subtract
     */
    void onSubtract(DataType value) throws OperationNotSupportedException;

    /**
     * When multiply is applied on the data type
     * @param value the value by which multiply
     */
    void onMultiply(DataType value) throws OperationNotSupportedException;

    /**
     * When divide is applied on the data type
     * @param value the value by which divide
     */
    void onDivide(DataType value) throws OperationNotSupportedException;

    /**
     * When square is applied on the data type
     */
    void onSquare() throws OperationNotSupportedException;

    /**
     * When square root is applied on the data type
     */
    void onSqrt() throws OperationNotSupportedException;

    /**
     * When power is applied on the data type
     * @param value the index of the power
     */
    void onPower(DataType value) throws OperationNotSupportedException;
}
