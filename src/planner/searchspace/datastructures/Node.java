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
     * The constructor requires the element and the parent
     * @param element the element
     * @param parent the parent
     */
    public Node(E element, Node parent){
        this.element = element;
        this.parent = parent;

        children = new LinkedList<>();
    }

    /**
     * Constructor for a root node
     * @param element the element
     */
    public Node(E element){
        this(element, null);
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
        newNode.setParent(this);
        children.addLast(newNode);
    }

    /**
     * Set the parent of the node
     * @param parent the parent
     */
    public void setParent(Node parent){
        this.parent = parent;
    }

    /*
    A node is equal to another node iff the children, element and parent are the same
     */
    @Override
    public boolean equals(Object otherNode){
        if(otherNode instanceof Node)
            return children.equals(((Node)otherNode).getChildren()) && element.equals(((Node)otherNode).getElement())
                    && parent.equals(((Node)otherNode).getParent());
        else
            return false;
    }
}
