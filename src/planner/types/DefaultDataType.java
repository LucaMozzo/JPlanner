package planner.types;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Abstract class for a custom data type, containing default implementation for every method in the interface
 */
public abstract class DefaultDataType implements NumericDataType {

    @Override
    public void onIncrease() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"increase\"");
    }

    @Override
    public void onDecrease() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"decrease\"");
    }

    @Override
    public void onSum(NumericDataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"sum\"");
    }

    @Override
    public void onSubtract(NumericDataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"subtract\"");
    }

    @Override
    public void onMultiply(NumericDataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"multiply\"");
    }

    @Override
    public void onDivide(NumericDataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"divide\"");
    }

    @Override
    public void onSquare() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"square\"");
    }

    @Override
    public void onSqrt() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"square root\"");
    }

    @Override
    public void onPower(NumericDataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"power\"");
    }

    @Override
    public void onAssign(DataType value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"assign\"");
    }

    @Override
    public void onSetTrue() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"set true\"");
    }

    @Override
    public void onSetFalse() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This data type doesn't support the operation \"set false\"");
    }
}
