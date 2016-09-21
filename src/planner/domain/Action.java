package planner.domain;

import planner.problem.State;
import planner.searchspace.datastructures.CombinationTree;
import planner.searchspace.datastructures.Node;
import planner.types.CustomObject;
import utils.exceptions.OperationNotSupportedException;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An action in order to start needs the preconditions to be satisfied
 */
public class Action{

    private LinkedList<Parameter> parameters;
    private LinkedList<Effect> effects;
    private LinkedList<Precondition> preconditions;
    private String name;

    /**
     * The constructor takes the basic arguments for defining an action
     * @param name the name of the action
     * @param preconditions the preconditions
     * @param effects the effects
     * @param parameters the parameters supported by the action
     */
    public Action(String name, LinkedList<Precondition> preconditions, LinkedList<Effect> effects, LinkedList<Parameter> parameters){
        this.preconditions = preconditions;
        this.effects = effects;
        this.parameters = parameters;
        this.name = name;
    }

    /**
     * Constructor that takes no preconditions and effects
     * @param name the name
     */
    public Action(String name){
        preconditions = new LinkedList<>();
        effects = new LinkedList<>();
        parameters = new LinkedList<>();
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
     * Allows to add a parameter to the list
     * @param param the parameter to be added
     */
    public void addParameter(Parameter param) { parameters.addLast(param); }

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
     * Getter for the parameters
     * @return the parameters
     */
    public LinkedList<Parameter> getParameters() { return parameters;}

    /**
     * Determines whether the action is currently applicable, by checking preconditions
     * @param state the state on which check if applicable
     * @return a list of the lists of candidates for each parameter
     */
    public LinkedList<LinkedList<CustomObject>> getApplicable(State state){
        LinkedList<LinkedList<CustomObject>> applicableCombinations = new LinkedList<>();
        LinkedList<CustomObject> instanceObjects  = state.getInstanceObjects(); //will temporary store instance objects of the problem
        Variable var = null;

        LinkedList<LinkedList<CustomObject>> allCandidates = new LinkedList<>();

        for(Parameter p : parameters){
            allCandidates.add(p.getCandidates(state));
        }

        CombinationTree<CustomObject> tree = new CombinationTree<>();
        tree.addNode(new Node<>(null));

        //I manually add the first layer of candidates
        for(CustomObject o : allCandidates.get(0))
            tree.addNode(new Node<>(o));

        for(int i = 0; i < allCandidates.get(0).size(); ++i)
            tree = buildCombinationTree(tree, allCandidates, (Node)tree.getRoot().getChildren().get(i), 1);

        //check if all the parameters satisfy the preconditions
        outer: for(LinkedList<CustomObject> combination : tree.getCombinations()){
            for(CustomObject obj : combination)
                for(Precondition precondition : preconditions){
                    if(!precondition.isSatisfied(obj))
                        continue outer; //one precondition that is not satisfied is enough for a combination to not be applicable
                }
            applicableCombinations.add(combination);
        }

        return applicableCombinations;
    }

    /**
     * Expands the tree recursively with all combinations of parameters
     * @param tree the current tree
     * @param candidates the candidate objects qualified as parameters
     * @param parent the parent node of this layer
     * @param index the index of the parameter's list
     * @return the updated combination tree
     */
    private CombinationTree<CustomObject> buildCombinationTree(CombinationTree tree, LinkedList<LinkedList<CustomObject>> candidates, Node parent, int index){

        if(index < candidates.size()){

            //temporary tree for multiple recursion
            CombinationTree<CustomObject> tmpTree = tree;

            for(CustomObject o : candidates.get(index)){
                Node child = new Node<>(o);
                tree.addNode(child, parent);

                tmpTree = buildCombinationTree(tree, candidates, child, index + 1);
            }

            return tmpTree;
        }
        else {
            //end of recursion
            return tree;
        }
    }

    /**
     * Applies all the effects after it is performed
     * @param obj the object on which apply the effects
     * @throws OperationNotSupportedException if the operation is not supported by the data type
     */
    public void applyEffects(CustomObject obj) throws OperationNotSupportedException {
        for(Effect effect : effects)
            effect.apply(obj);
    }

    @Override
    public String toString(){
        return name;
    }
}
