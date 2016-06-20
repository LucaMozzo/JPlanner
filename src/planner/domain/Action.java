package planner.domain;

import planner.problem.Problem;
import planner.problem.State;
import planner.types.OperationNotSupportedException;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An action in order to start needs the preconditions to be satisfied
 */
public class Action {

    private LinkedList<Effect> effects;
    private LinkedList<Precondition> preconditions;
    private String name;

    /**
     * The constructor takes the basic arguments for defining an action
     * @param preconditions the preconditions
     * @param effects the effects
     */
    public Action(String name, LinkedList<Precondition> preconditions, LinkedList<Effect> effects){
        this.preconditions = preconditions;
        this.effects = effects;
        this.name = name;
    }

    /**
     * Constructor that takes no preconditions and effects
     * @param name the name
     */
    public Action(String name){
        preconditions = new LinkedList<>();
        effects = new LinkedList<>();
        this.name = name;
    }

    /**
     * Allows to add a precondition to the list
     * @param precondition the precondition to be added
     */
    public void addPrecondition(Precondition precondition){
        preconditions.addLast(precondition);
    }

    /**
     * Allows to add an effect to the list
     * @param effect the effect to be added
     */
    public void addEffect(Effect effect){
        effects.addLast(effect);
    }

    /**
     * Getter for the preconditions
     * @return the preconditions
     */
    public LinkedList<Precondition> getPreconditions(){
        return preconditions;
    }

    /**
     * Getter for the effects
     * @return the effects
     */
    public LinkedList<Effect> getEffects(){
        return effects;
    }

    /**
     * Determines whether the action is currently applicable, by checking preconditions
     * @param state the state on which check if applicable
     * @return true if applicable
     */
    public boolean isApplicable(State state){
        LinkedList<Variable> temp; //will temporary store instance variables of the problem
        Variable var = null;

        for(Precondition precondition : preconditions) {
            temp = state.getInstanceVariables();

            var = state.getVariableByName(precondition.getVariableName());

            int index = temp.indexOf(var);
            Variable tmpVar;

            //check if the precondition variable is declared in the problem
            if(index > -1)
                tmpVar = temp.get(index);
            else
                return false;

            /*
                the first condition checks whether there's an instance of the needed variable in that problem
                to avoid users' mistakes, whereas the second checks whether the current value of the variable
                satisfies the expected value of the precondition
             */
            if(var.getName().equals(tmpVar.getName()) && precondition.isSatisfied(state))
                continue;
            else
                return false;
        }
        return true;
    }

    /**
     * Applies all the effects after it is performed
     * @param state the state on which apply the effects
     */
    public void applyEffects(State state) throws OperationNotSupportedException {
        for(Effect effect : effects)
            effect.apply(state);
    }

    @Override
    public String toString(){
        return name;
    }
}
