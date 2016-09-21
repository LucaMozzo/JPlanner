package planner;

import planner.domain.Domain;
import planner.domain.Action;
import planner.problem.Problem;
import utils.exceptions.DuplicateVariableNameException;
import utils.exceptions.OperationNotSupportedException;

import java.util.LinkedList;

/**
 * Created by LUCA on 04/05/2016.
 *
 * A planner has a domain and one or more problem files
 */
public class Project {

    private Domain domain;
    private LinkedList<Problem> problems;

    /**
     * Default constructor only initializes the list of problems
     */
    public Project(){
        problems = new LinkedList<>();
    }

    /**
     * This overloaded constructor takes only the domain
     * @param domain the domain
     */
    public Project(Domain domain){
        this.domain = domain;
    }

    /**
     * This overloaded constructor takes both the problems and the domain
     * @param domain the domain
     * @param problems the list of problems
     */
    public Project(Domain domain, LinkedList<Problem> problems){
        this.domain = domain;
        this.problems = problems;
    }

    /**
     * Add a problem to the list
     * @param problem the problem to be added
     */
    public void addProblem(Problem problem){
        problems.add(problem);
    }

    /**
     * Setter for the domain
     * @param newDomain the new domain
     */
    public void setDomain(Domain newDomain){
        this.domain = newDomain;
    }

    /**
     * Removes a specific problem from the list
     * @param problem the problem to be removed
     */
    public void removeProblem(Problem problem){
        if(problems.contains(problem))
            problems.remove(problem);
    }

    /**
     * Removes all the problems from the planner
     */
    public void removeAll(){
        problems = new LinkedList<>();
    }

    /**
     * Solves all the problems in the project
     * @return the solutions
     */
    public LinkedList<LinkedList<Action>> solveAll() throws OperationNotSupportedException, DuplicateVariableNameException {
        LinkedList<LinkedList<Action>> solutions = new LinkedList<>();

        for(Problem p : problems)
            solutions.add(p.solve());

        return solutions;
    }
}
