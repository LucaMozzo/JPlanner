package planner.searchspace.search;

import planner.problem.State;
import planner.problem.TreeState;
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 14/05/2016.
 *
 * Depth-first search
 */
public final class DFS {

    /**
     * Searched the goal state in the searchspace
     * @param searchSpace the tree
     * @param goalState the goal
     * @return the plan
     */
    public static LinkedList<IAction> search(Tree searchSpace, State goalState){
        //the iterator uses preorder traversal, which is the same as DFS
        Iterator preorderTraversal = searchSpace.iterator();

        State element = null;

        while(preorderTraversal.hasNext()){
            element = (TreeState)((Node)preorderTraversal.next()).getElement();
            if(((State)element).equals(((State)goalState)))
                return ((TreeState)element).getActions();
        }
        return null;
    }
}
