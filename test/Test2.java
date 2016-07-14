
import planner.domain.Variable;
import planner.types.Object;
import planner.types.standard.Integer;
import utils.exceptions.OperationNotSupportedException;

/**
 * Created by LUCA on 21/06/2016.
 */
public class Test2 {
    public static void main(String[] args) throws OperationNotSupportedException {
        class Ball extends Object {
            private Variable<Integer> number = new Variable<>("vName", new Integer(10));
        }

        Ball b = new Ball();
        System.out.print(b.getPropertyByName("number"));
    }
}
