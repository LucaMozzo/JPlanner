package planner.searchspace.build;

import planner.domain.Action;
import planner.domain.Domain;
import planner.problem.Problem;
import planner.problem.TreeState;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;

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
    public static Tree build(Problem problem, Domain domain){
        Tree searchSpaceTree = new Tree();

        //the root is the initial state
        TreeState init = (TreeState) problem.getInitialState();
        searchSpaceTree.addNode(new Node<>(init));

        expandNode(searchSpaceTree, searchSpaceTree.getRoot(), domain);

        return searchSpaceTree;
    }

    /*
    Recursive function that creates and adds States for each applicable action to the tree
     */
    private static Tree expandNode(Tree tree, Node<TreeState> parent, Domain domain){
        TreeState parentState = parent.getElement();
        if(domain.getApplicableActions(parentState).isEmpty())
            return tree; //base case, no applicable actions
        else
        //for each possible action
            for(Action a : domain.getApplicableActions(parentState)) {
                //create a new state and node
                TreeState childState = new TreeState(parentState.getPlan());
                Node<TreeState> node = new Node<>(childState, parent);

                //apply the action and add it to the list to keep track of it
                childState.addAction(a);
                a.applyEffects(childState);

                //add the node to the tree
                tree.addNode(node, parent);

                //recur
                return expandNode(tree, node, domain);
            }
        return null;
    }
}
