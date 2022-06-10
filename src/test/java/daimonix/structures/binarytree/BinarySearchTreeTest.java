package daimonix.structures.binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new BinarySearchTreeList<>(1);
    }

    @Test
    public void basicTest() {
        assertFalse(tree.isEmpty());
        tree.add(2);
        tree.add(3);
        assertEquals(3, tree.getNodeCount());
    }

    @Test
    public void getAscTest() {
        List<Integer> expectedList = new LinkedList<Integer>();
        tree.add(7);
        tree.add(8);
        tree.add(15);
        tree.add(7);
        expectedList.add(1);
        expectedList.add(7);
        expectedList.add(7);
        expectedList.add(8);
        expectedList.add(15);
        assertEquals(expectedList, tree.getElementsAsc());
    }

    @Test
    public void getDescTest() {
        List<Integer> expectedList = new LinkedList<Integer>();
        tree.add(-3);
        tree.add(5);
        tree.add(3);
        expectedList.add(5);
        expectedList.add(3);
        expectedList.add(1);
        expectedList.add(-3);
        assertEquals(expectedList, tree.getElementsDesc());
    }
}
