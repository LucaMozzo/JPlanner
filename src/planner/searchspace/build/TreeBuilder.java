package planner.searchspace.build;

import planner.domain.Action;
import planner.domain.Domain;
import planner.domain.generic.ObjectAction;
import planner.problem.Problem;
import planner.problem.State;
import planner.problem.TreeState;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.CustomObject;
import utils.exceptions.DuplicateVariableNameException;
import utils.exceptions.OperationNotSupportedException;

import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * Builds the tree
 */
public final class TreeBuilder{

    /**
     * Builds the entire searchspace based on the applicable actions
     * @param problem the problem
     * @param domain the domain
     * @return the built tree
     */
    public static Tree build(Problem problem, Domain domain) throws OperationNotSupportedException, StackOverflowError, DuplicateVariableNameException{
        Tree searchSpaceTree = new Tree();

        //the root is the initial state
        TreeState init = State.convertToTreeState(problem.getInitialState());
        searchSpaceTree.addNode(new Node<>(init));

        searchSpaceTree = expandNode(searchSpaceTree, searchSpaceTree.getRoot(), domain);

        return searchSpaceTree;
    }

    /*
    Recursive function that creates and adds States for each applicable action to the tree
     */
    private static Tree expandNode(Tree tree, Node<TreeState> parent, Domain domain) throws OperationNotSupportedException {
        TreeState parentState = parent.getElement();
        LinkedList<IAction> applicableActions = domain.getApplicableActions(parentState);
        if(applicableActions.isEmpty())
            return tree; //base case, no applicable actions
        else {
            //for each possible action
            for (IAction a : applicableActions) {

                //differentiate between actions and object-actions
                if(a instanceof Action) {
                    generateChild(tree, parent, domain, parentState, a, null);

                }
                else {
                    LinkedList<CustomObject> objects = ((ObjectAction)a).getQualifiedObjects(parentState);
                    for(CustomObject o : objects){
                        generateChild(tree, parent, domain, parentState, a, o);
                    }
                }
            }
        }
        return tree;
    }

    private static void generateChild(Tree tree, Node<TreeState> parent, Domain domain, TreeState parentState, IAction a, CustomObject obj) throws OperationNotSupportedException {
        //create a new state and node
        TreeState childState = new TreeState(parentState.getActions(), parentState.getInstanceVariables()); //the child node will inherit everything from the parent
        Node<TreeState> node = new Node<>(childState);

        //apply the action and add it to the list to keep track of it
        childState.addAction(a);

        if(a instanceof Action)
            a.applyEffects(childState);
        else {
            a.applyEffects(obj);
            ((ObjectAction)a).appendName(" @" + obj.toString());
        }

        //add the node to the tree
        tree.addNode(node, parent);

        //recur
        expandNode(tree, node, domain);
    }
}
