package planner.searchspace.datastructures;

import java.util.*;

/**
 * Created by LUCA on 17/05/2016.
 *
 * Iterable tree of nodes
 */
public class Tree implements NodeTree, Iterable {

    protected Node root;
    protected long size = 0;

    /**
     * By default add the node as child of the root, if the root doesn't exist, then it becomes the root
     */
    public void addNode(Node newNode){
        if(size == 0) { //the root doesn't exist
            root = newNode;
            ++size;
        }
        else
            addNode(newNode, root);
    }

    /**
     * Add a child of the specified parent
     * @param newNode the child
     * @param parent the parent
     */
    public void addNode(Node newNode, Node parent){
        parent.addChildren(newNode);
        newNode.setParent(parent);
        ++size;
    }

    /**
     * Return the number of elements
     * @return the number of elements
     */
    public long getSize(){
        return size;
    }

    @Override
    public Collection<Node> getChildren(Node node) {
        return node.getChildren();
    }

    @Override
    public Node getParent(Node node) {
        return node.getParent();
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf(Node node) {
        return node.isLeaf();
    }

    @Override
    public Iterator iterator() {

        /*
        The currentNode should never become null during the iteration, except when it's the first iteration, the one
        that precedes the root
         */

        Iterator iterator = new Iterator() {

            private Node currentNode = null;
            private Stack<Node> stack = new Stack<>();

            @Override
            public boolean hasNext() {
                if(currentNode == null)
                    return true; //first iteration
                else
                    if(!preorder(currentNode).equals(currentNode)) //avoid repetition of the last action
                        return preorder(currentNode) != null;
                    else
                        return false;
            }

            @Override
            public Node next() {
                if(currentNode == null) {
                    stack.push(root);
                    return currentNode = root; //the first call
                }
                else {
                    //prevent the currentNode to become null when the tree has finished (can be confused with the 1st iteration)
                    currentNode = (preorder(currentNode) == null ? currentNode : preorder(currentNode));
                    return currentNode;
                }
            }

            private Node preorder(Node prev){
                if(!stack.isEmpty())
                    if(prev.isLeaf()){ //get siblings/backtrack
                        return stack.pop();
                    }
                    else{
                        //push all the children of the current node and pop the first
                        for(Node child : (ArrayList<Node>)prev.getChildren()){
                                stack.push(child);
                        }
                        return stack.pop();
                    }
                else
                    return null;
            }
        };
        return iterator;
    }
}
