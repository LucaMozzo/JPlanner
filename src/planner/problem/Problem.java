package planner.problem;

import planner.domain.Domain;
import planner.domain.Variable;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * Represents a problem with all its components
 */
public class Problem {

    private Domain domain;
    private State initialState;
    private State goalState;

    /**
     * Default constructor
     * @param domain the domain
     */
    public Problem(Domain domain){

        this(domain, null, null);
    }

    /**
     * Overloaded constructor allows specification of initial and goal state
     * @param domain the domain
     * @param initialState the initial state
     * @param goalState the goal state
     */
    public Problem(Domain domain, State initialState, State goalState){

        this.domain = domain;
        this.initialState = initialState;
        this.goalState = goalState;
    }

    /*TODO public Plan solve(){
    }*/
}
