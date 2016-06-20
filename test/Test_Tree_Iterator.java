
import planner.searchspace.datastructures.Node;
import planner.searchspace.datastructures.Tree;
import planner.types.OperationNotSupportedException;

import java.util.Iterator;

/**
 * Created by LUCA on 19/06/2016.
 */
public class Test_Tree_Iterator {
    public static void main(String[] args) throws OperationNotSupportedException {

        Tree searchspace = new Tree();
        Node<java.lang.Integer> root = new Node<>(0);
        Node<java.lang.Integer> a = new Node<>(1);
        Node<java.lang.Integer> b = new Node<>(2);
        Node<java.lang.Integer> c = new Node<>(3);
        Node<java.lang.Integer> d = new Node<>(4);
        Node<java.lang.Integer> e = new Node<>(5);
        Node<java.lang.Integer> f = new Node<>(6);
        Node<java.lang.Integer> g = new Node<>(7);
        Node<java.lang.Integer> h = new Node<>(8);

        searchspace.addNode(root); //root
        searchspace.addNode(a, root);
        searchspace.addNode(b, a);
        searchspace.addNode(c, b);
        searchspace.addNode(d, b);
        searchspace.addNode(e, a);
        searchspace.addNode(f,root);
        searchspace.addNode(g,f);
        searchspace.addNode(h,root);


        System.out.println("Explored " + searchspace.getSize() + " nodes");

        Iterator it = searchspace.iterator();

        while(it.hasNext())
            System.out.println(((Node)it.next()).getElement());
    }
}
