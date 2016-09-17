package planner.problem;

import planner.domain.Domain;
import planner.searchspace.build.TreeBuilder;
import planner.searchspace.datastructures.Tree;
import planner.searchspace.search.DFS;
import utils.exceptions.DuplicateVariableNameException;
import utils.exceptions.OperationNotSupportedException;

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

    /**
     * Getter for the initial state
     * @return the initial state
     */
    public State getInitialState(){
        return initialState;
    }

    /**
     * Getter for the goal state
     * @return the goal state
     */
    public State getGoalState(){
        return goalState;
    }

    /**
     * Setter for the initial state
     * @param newState the initial state
     */
    public void setInitialState(State newState){
        initialState = newState;
    }

    /**
     * Setter for the goal state
     * @param newState the goal state
     */
    public void setGoalState(State newState){
        goalState = newState;
    }

    //time spent building the tree in the last execution
    private long timeBuildingTree = 0;
    //time spent searching the tree in the last execution
    private long timeSearching = 0;

    /**
     * Solves the current problem
     * @return the plan
     */
    public LinkedList<IAction> solve() throws OperationNotSupportedException, DuplicateVariableNameException {
        long start = System.currentTimeMillis();
        Tree searchspace = TreeBuilder.build(this, domain);
        timeBuildingTree = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        LinkedList<IAction> tmp = DFS.search(searchspace, State.convertToTreeState(goalState));
        timeSearching = System.currentTimeMillis() - start;

        return tmp;
    }

    /**
     * Returns the time spent for building the tree and searching in the last execution
     * @return the time spent building [0], and the time spent searching [1]
     */
    public long[] getLastExecutionTime(){
        long[] arr = new long[2];
        arr[0] = timeBuildingTree;
        arr[1] = timeSearching;

        return arr;
    }

}
