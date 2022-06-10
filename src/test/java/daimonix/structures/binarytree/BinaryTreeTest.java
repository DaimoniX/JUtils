package daimonix.structures.binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void basicArrayTreeTest() {
        BinaryTree<Integer> tree = new BinaryTreeArray<>();
        assertTrue(tree.isEmpty());
        tree.add(0);
        assertFalse(tree.isEmpty());
        tree.add(1);
        tree.add(2);
        assertEquals(3, tree.getNodeCount());
    }

    @Test
    public void basicListTreeTest() {
        BinaryTree<Integer> tree = new BinaryTreeList<>();
        assertTrue(tree.isEmpty());
        tree.add(0);
        assertFalse(tree.isEmpty());
        tree.add(1);
        tree.add(2);
        assertEquals(3, tree.getNodeCount());
    }

    @Test
    public void toStringTest() {
        BinaryTree<Integer> arrayTree = new BinaryTreeArray<>();
        BinaryTree<Integer> listTree = new BinaryTreeArray<>();
        assertEquals("()", arrayTree.toString());
        assertEquals("()", listTree.toString());
        String expectedString = "(1(2()())(3(4()())(5()())))";
        arrayTree.add(1);
        arrayTree.addChild(1, 2);
        arrayTree.addChild(1, 3);
        arrayTree.addChild(3, 4);
        arrayTree.addChild(3, 5);
        listTree.add(1);
        listTree.addChild(1, 2);
        listTree.addChild(1, 3);
        listTree.addChild(3, 4);
        listTree.addChild(3, 5);
        assertEquals(expectedString, arrayTree.toString());
        assertEquals(expectedString, listTree.toString());
    }
}
