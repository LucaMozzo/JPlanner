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
            private boolean rootVisited = false;
            private int visited = 0;
            private Node currentNode = root;
            private Stack<Node> stack = new Stack<>();

            @Override
            public boolean hasNext() {
                return visited < size - 1;
            }

            @Override
            public Node next() {
                if(currentNode == null)
                    return null;
                else
                    return currentNode = (preorder(currentNode));
            }

            private Node preorder(Node prev){
                if(prev.isLeaf() && !stack.isEmpty()){ //get siblings/backtrack
                    ++visited;
                    return stack.pop();
                }
                else {
                    if(prev == root && !rootVisited) {
                        rootVisited = true;
                        return root;
                    }
                    //push all the children of the current node and pop the first
                    for(Node child : (ArrayList<Node>)prev.getChildrenReverse()){
                        stack.push(child);
                    }
                    ++visited;
                    return stack.isEmpty() ? null : stack.pop();
                }
            }
        };
        return iterator;
    }
}
