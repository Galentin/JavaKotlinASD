package lesson3;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BinaryTreeIteratorTest {
    @Test
    public void next() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(12);
        tree.add(18);
        tree.add(4);
        tree.add(9);
        tree.add(29);

        Iterator<Integer> iteratot = tree.iterator();
        assertEquals(4,(int) iteratot.next());
        assertEquals(5,(int) iteratot.next());
        assertEquals(9,(int) iteratot.next());
        assertEquals(10,(int) iteratot.next());
        assertEquals(12,(int) iteratot.next());
        assertEquals(18,(int) iteratot.next());
        assertEquals(29,(int) iteratot.next());

    }

}