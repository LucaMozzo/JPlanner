package planner.searchspace.datastructures;

import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A node has an element and records its position in the tree
 */
public class Node<E> {

    protected E element;

    private Node parent;
    private LinkedList<Node<E>> children;

    /**
     * The costructor requires the element and the parent
     * @param element the element
     * @param parent the parent
     */
    public Node(E element, Node parent){
        this.element = element;
        this.parent = parent;

        children = new LinkedList<>();
    }

    /**
     * Getter for the element
     * @return the element
     */
    public E getElement(){
        return element;
    }

    /**
     * Getter for the parent
     * @return the parent
     */
    public Node getParent(){
        return parent;
    }

    /**
     * Getter for the children
     * @return the list of children
     */
    public LinkedList<Node<E>> getChildren(){
        return children;
    }

    /**
     * If the node has no child, then it's a leaf
     * @return whether it's a leaf or not
     */
    public boolean isLeaf(){
        return children.isEmpty();
    }

    /**
     * Add a new child
     * @param newNode the child
     */
    public void addChildren(Node newNode){
        children.addLast(newNode);
    }
}
