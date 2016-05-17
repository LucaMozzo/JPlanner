package planner.searchspace.datastructures;

import java.util.Collection;

/**
 * Created by LUCA on 17/05/2016.
 *
 * Tree supporting all kinds of nodes
 */
public class Tree implements NodeTree {

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
        ++size;
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
}
