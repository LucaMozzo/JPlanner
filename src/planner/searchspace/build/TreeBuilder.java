package planner.searchspace.build;

import planner.domain.Action;
import planner.domain.Domain;
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

    /**
     * Recursive function that creates and adds States for each applicable action to the tree
     *
     * 1. Create a new TreeState and Node for the child
     * 2. Add action to actions list
     * 3. Apply effects on the object
     * 4. Add the node to the searchspace
     * 5. Call itself
     * @param tree the tree
     * @param parent the parent
     * @param domain the domain
     * @return the tree with the new child in it
     * @throws OperationNotSupportedException
     */
    private static Tree expandNode(Tree tree, Node<TreeState> parent, Domain domain) throws OperationNotSupportedException {
        TreeState parentState = parent.getElement();
        LinkedList<Action> applicableActions = domain.getApplicableActions(parentState);

        if(applicableActions.isEmpty())
            return tree; //base case, no applicable actions
        else {
            //for each possible action
            for (Action a : applicableActions) {

                //create a new state and node
                TreeState childState = new TreeState(parentState.getActions(), parentState.getObjects()); //the child node will inherit everything from the parent
                Node<TreeState> node = new Node<>(childState);

                //apply the action and add it to the list to keep track of it
                childState.addAction(a);

                a.applyEffects(childState); //TODO fix problem with effects

                //add the node to the tree
                tree.addNode(node, parent);

                //recur
                expandNode(tree, node, domain);

            }
        }
        return tree;
    }

}
