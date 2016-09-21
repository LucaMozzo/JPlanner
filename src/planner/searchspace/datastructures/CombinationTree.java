package planner.searchspace.datastructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Luca Mozzo
 *
 * This tree provides functionality for extracting all root-to-leaf combinations
 */
public class CombinationTree<E> extends Tree {

    /**
     * Returns all the root-to-leaf combinations, i.e. all the possible paths to get from the root to each leaf
     * @return a list of paths
     */
    public LinkedList<LinkedList<E>> getCombinations(){
        LinkedList<LinkedList<E>> combinations = new LinkedList<>();

        Iterator it = iterator();

        //because we exclude the root which is always empty, if the tree has no other children, we return null
        if(size <= 1)
            return null;

        it.next(); //skip root iteration

        while(it.hasNext()){
            Node currentNode = (Node) it.next();

            //reached the end of the tree
            if(currentNode.isLeaf()){
                combinations.add(stackToList(getAncestorsStack(currentNode)));
            }
        }

        return combinations;
    }

    /**
     * Build the path from the root (excluded) to the given node
     * @param node the node
     * @return the path from the root
     */
    private Stack<E> getAncestorsStack(Node node){
        Stack<E> stack = new Stack<>();

        while(!node.getParent().equals(root)){
            stack.push((E) node.getElement());
            node = node.getParent();
        }

        return stack;
    }

    /**
     * Transform a stack to a list of nodes, skipping the root (which is always empty)
     * @param stack the stack
     * @return a list containing the elements of the stack
     */
    private LinkedList<E> stackToList(Stack<E> stack){
        LinkedList<E> nodes = new LinkedList<>();

        for(int i = 0; i < stack.size() - 2; ++i){
            nodes.addLast(stack.pop());
        }

        return nodes;
    }
}
