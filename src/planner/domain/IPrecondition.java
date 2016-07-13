package planner.domain;

import planner.problem.State;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An abstraction of a precondition
 */
public interface IPrecondition {

    /**
     * Determines if the variable got the required attribute
     * If the variable is not Comparable<> an exception is thrown
     * @param state the current state
     * @return whether is satisfied or not
     * @throws ClassCastException if the type of the variable is not comparable
     */
    boolean isSatisfied(State state) throws ClassCastException;
}
