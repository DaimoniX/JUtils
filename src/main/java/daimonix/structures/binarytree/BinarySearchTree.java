package daimonix.structures.binarytree;

import java.util.List;

public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    /**
     * Returns list of tree elements in ascending order.
     * 
     * @return list of tree elements
     */
    List<T> getElementsAsc();

    /**
     * Returns list of tree elements in descending order.
     * 
     * @return list of tree elements
     */
    List<T> getElementsDesc();
}
