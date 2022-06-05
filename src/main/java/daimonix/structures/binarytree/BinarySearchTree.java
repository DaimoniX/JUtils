package daimonix.structures.binarytree;

import java.util.List;

public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    List<T> getElementsAsc();

    List<T> getElementsDesc();
}
