package planner.searchspace.build;

import planner.domain.Action;
import planner.domain.Domain;
import planner.problem.Problem;
import planner.problem.TreeState;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.OperationNotSupportedException;

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
    public static Tree build(Problem problem, Domain domain) throws OperationNotSupportedException, StackOverflowError{
        Tree searchSpaceTree = new Tree();

        //the root is the initial state
        TreeState init = new TreeState();
        init.addVariables(problem.getInitialState().getInstanceVariables());
        searchSpaceTree.addNode(new Node<>(init));

        expandNode(searchSpaceTree, searchSpaceTree.getRoot(), domain);

        return searchSpaceTree;
    }

    /*
    Recursive function that creates and adds States for each applicable action to the tree
     */
    private static Tree expandNode(Tree tree, Node<TreeState> parent, Domain domain) throws OperationNotSupportedException {
        TreeState parentState = parent.getElement();
        LinkedList<Action> applicableActions = domain.getApplicableActions(parentState);
        if(applicableActions.isEmpty())
            return tree; //base case, no applicable actions
        else {
            //for each possible action
            Node<TreeState> node = null;

            for (Action a : applicableActions) {
                //create a new state and node
                TreeState childState = new TreeState(parentState.getActions(), parentState.getInstanceVariables()); //the child node will inherit everything from the parent
                node = new Node<>(childState);

                //apply the action and add it to the list to keep track of it
                childState.addAction(a);
                a.applyEffects(childState);

                //add the node to the tree
                tree.addNode(node, parent);
            }
            //recur
            return expandNode(tree, node, domain);
        }
    }
}
