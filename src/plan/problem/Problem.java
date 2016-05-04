package plan.problem;

import plan.domain.Domain;
import plan.domain.Variable;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * Represents a problem with all its components
 */
public class Problem {

    private Domain domain;
    private LinkedList<Variable> variables;

    /**
     * Default constructor
     * @param domain the domain
     */
    public Problem(Domain domain){

        this.domain = domain;
    }

    /**
     * Return all the variables of this instance
     * @return the variables
     */
    public LinkedList<Variable> getInstanceVariables(){
        return variables;
    }
}
