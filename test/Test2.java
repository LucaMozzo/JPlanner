import planner.domain.*;
import planner.domain.generic.GenericPrecondition;
import planner.problem.Problem;
import planner.problem.State;
import planner.types.Object;
import planner.types.OperationNotSupportedException;
import planner.types.standard.Integer;
import utils.Comparison;
import utils.Operation;

/**
 * Created by LUCA on 21/06/2016.
 */
public class Test2 {
    public static void main(String[] args) throws OperationNotSupportedException {

        class Room extends Object{ }
        class Ball extends Object{ }
        class Robot extends Object{ }

        Variable<Room> vRoomA = new Variable<>("roomA", new Room());
        Variable<Room> vRoomB = new Variable<>("roomV", new Room());

        Variable<Ball> vBall1 = new Variable<>("ball1", new Ball());
        Variable<Ball> vBall2 = new Variable<>("ball2", new Ball());
        Variable<Ball> vBall3 = new Variable<>("ball3", new Ball());

        Variable<Robot> vRobot = new Variable<>("robot", new Robot());

        //properties
        vBall1.getValue().addProperty(new Variable<Room>("position", vRoomA.getValue()));
        vBall2.getValue().addProperty(new Variable<Room>("position", vRoomA.getValue()));
        vBall3.getValue().addProperty(new Variable<Room>("position", vRoomA.getValue()));

        vRobot.getValue().addProperty(new Variable<Room>("position", vRoomB.getValue()));

        GenericPrecondition<Robot> preMove = new GenericPrecondition<>("btnPressed", new Integer(1), Comparison.LESS_OR_EQUAL);
        Precondition<Integer> prePickup = new Precondition<>("btn2Pressed", new Integer(4), Comparison.LESS);
        Precondition<Integer> preDrop = new Precondition<>("btn3Pressed", new Integer(5), Comparison.LESS);

        Effect<Integer> afterMove = new Effect<>("btnPressed", Operation.INCREASE);
        Effect<Integer> afterPickup = new Effect<>("btn2Pressed", Operation.INCREASE);
        Effect<Integer> afterDrop = new Effect<>("btn3Pressed", Operation.INCREASE);

        Action move = new Action("move");
        move.addPrecondition(preMove);
        move.addEffect(afterMove);

        Action pickup = new Action("pickup");
        pickup.addPrecondition(prePickup);
        pickup.addEffect(afterPickup);

        Action drop = new Action("drop");
        drop.addPrecondition(preDrop);
        drop.addEffect(afterDrop);

        Domain domain = new Domain();
        domain.addAction(move);
        domain.addAction(pickup);
        domain.addAction(drop);

        State init = new State();
        init.addVariable(vRoomA);
        init.addVariable(vRoomB);
        init.addVariable(vBall1);
        init.addVariable(vBall2);
        init.addVariable(vBall3);
        init.addVariable(vRobot);

        State goal = new State();
        goal.addVariable(new Variable("ball1", new Ball(roomB)));
        goal.addVariable(new Variable("ball2", new Ball(roomB)));
        goal.addVariable(new Variable("ball3", new Ball(roomB)));

        Problem problem1 = new Problem(domain);
        problem1.setInitialState(init);
        problem1.setGoalState(goal);

        System.out.println("Plan: " + problem1.solve());
    }
}
