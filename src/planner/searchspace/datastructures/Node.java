package planner.searchspace.datastructures;

import java.util.ArrayList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A node has an element and records its position in the tree
 */
public class Node<E> {

    protected E element;

    private Node parent;
    private ArrayList<Node<E>> children; //we use an array because when reversing, it takes less

    /**
     * The constructor sets the element and initialises the children array
     * @param element the element
     */
    public Node(E element){
        this.element = element;
        children = new ArrayList<>();
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
    public ArrayList<Node<E>> getChildren(){
        return children;
    }

    /**
     * Getter for the children from last to first
     * @return the reversed list of children
     */
    public ArrayList<Node<E>> getChildrenReverse(){
        ArrayList<Node<E>> temp = new ArrayList<>();

        for(int i = children.size() - 1; i >= 0; --i)
            temp.add(children.get(i));
        return temp;
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
        children.add(newNode);
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
