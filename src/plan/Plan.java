package plan;

import plan.domain.Domain;
import plan.problem.Problem;

import java.util.LinkedList;

/**
 * Created by LUCA on 04/05/2016.
 *
 * A plan has a domain and one or more problem files
 */
public class Plan {

    private Domain domain;
    private LinkedList<Problem> problems;

    /**
     * Default constructor only initializes the list of problems
     */
    public Plan(){
        problems = new LinkedList<>();
    }

    /**
     * This overloaded constructor takes only the domain
     * @param domain the domain
     */
    public Plan(Domain domain){
        this.domain = domain;
    }

    /**
     * This overloaded constructor takes both the problems and the domain
     * @param domain the domain
     * @param problems the list of problems
     */
    public Plan(Domain domain, LinkedList<Problem> problems){
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
     * Removes all the problems from the plan
     */
    public void removeAll(){
        problems = new LinkedList<>();
    }

    public void solveAll(){

    }
}
