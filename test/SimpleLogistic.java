import planner.domain.*;
import planner.problem.State;
import planner.types.CustomObject;
import planner.types.standard.Boolean;
import planner.types.standard.Integer;
import utils.Comparison;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by lucam on 11/08/2017.
 *
 * There are 2 trucks and 3 containers to move from location1 to location 2
 */
public class SimpleLogistic {

    public static void main(String[] args){

        //1. create object definitions and instances
        class Location extends CustomObject{ }

        Location locA = new Location();
        Location locB = new Location();

        class Container extends CustomObject{
            public Variable<Location> location = new Variable<>("location", locA);
        }

        class Truck extends CustomObject {
            public Variable<Location> location = new Variable<>("location", locA);
            public Variable<Boolean> loaded = new Variable<>("loaded", new Boolean(false));
            public Variable<Container> load = new Variable<Container>("load", null);
        }

        Container container1 = new Container();
        Container container2 = new Container();
        Container container3 = new Container();

        Truck truck1 = new Truck();
        Truck truck2 = new Truck();

        //2. set initial state
        State initialState = new State();
        initialState.addObject(container1);
        initialState.addObject(container2);
        initialState.addObject(container3);

        //3. define a goal state TODO improve
        State finalState = new State();
        LinkedList<Container> containers = new LinkedList<>();
        for(int i = 0; i < 3; ++i){
            Container c = new Container();
            c.location = new Variable<>("location", locB);
        }
        finalState.addObjects((Collection)containers);  //ultimately we want all containers to be in location B

        //4. define the actions (in this case only the trucks can perform actions)
        Action move = new Action("move");
        Action load = new Action("load");
        Action unload = new Action("unload");

        //5. identify the parameters needed by the action
        Parameter<Location> moveDestination = new Parameter<>();

        Parameter<Container> toLoad = new Parameter<>();

        Parameter<Container> toUnload = new Parameter<>();

        //6. specify the preconditions for these actions

        /*
         * Move: the location parameter needs to be different than the current location TODO
         * Load needs the truck not to be already loaded and the container to be in that location
         * Unload needs the truck to be already loaded
         */
        Precondition<Location> destinationPrecondition = new Precondition<Location>(moveDestination, Comparison.NOT_EQUAL, )
                Precondition<Boolean> loadPrec = new Precondition<>("loaded", new Boolean(false));
        Precondition<Boolean> unloadPrec = new Precondition<>("loaded", new Boolean(true));

        load.addPrecondition(loadPrec);
        unload.addPrecondition(unloadPrec);

        //7. define the effects of each action

        /*
         * Move changes the location from the location variable to the other location
         * Load changes the location of the container to null, the loaded variable of the truck to true and the load variable of the truck to the container
         * Unload changes the location of the container to the loc of the truck, the loaded variable to false and the load variable to null
         */
        Effect<> moveEff
    }
}
