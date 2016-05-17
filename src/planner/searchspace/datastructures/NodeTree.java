package planner.searchspace.datastructures;

import java.util.Collection;

/**
 * Created by LUCA on 17/05/2016.
 *
 * Specifies the methods of a generic tree
 */
public interface NodeTree {

    /**
     * Get a collection of children of the node
     * @param node the parent
     * @return the collection of children
     */
    Collection<Node> getChildren(Node node);

    /**
     * Get the parent of the node
     * @param node the node
     * @return the parent
     */
    Node getParent(Node node);

    /**
     * Get the root of the tree
     * @return the root
     */
    Node getRoot();

    /**
     * Return true iff the node is an external node
     * @param node the node
     * @return whether it's a leaf or not
     */
    boolean isLeaf(Node node);
}
