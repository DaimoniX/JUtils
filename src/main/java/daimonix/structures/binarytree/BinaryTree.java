package daimonix.structures.binarytree;

import java.util.NoSuchElementException;

public interface BinaryTree<T> {
    /**
     * Returns true if tree has no elements.
     * 
     * @return true if tree has no elements
     */
    boolean isEmpty();

    /**
     * Returns number of nodes in binary tree.
     * 
     * @return number of nodes in binary tree
     */
    int getNodeCount();

    /**
     * Returns true if binary tree contains specified element.
     * 
     * @param value value to search for in tree
     * @throws NullPointerException if value is null
     * @return true if tree contains specified value
     */
    boolean contains(T value) throws NullPointerException;

    /**
     * Adds value to binary tree
     * 
     * @param value value to add
     * @throws NullPointerException if value is null
     */
    void add(T value) throws NullPointerException;

    /**
     * Adds child with value to specified parent node.
     * 
     * @param parent
     * @param value
     * @throws NoSuchElementException if specified parent does not exist
     * @throws NullPointerException   if value is null
     */
    void addChild(T parent, T value) throws NoSuchElementException, NullPointerException;

    /**
     * Removes specified value from tree
     * 
     * @param value value to remove
     * @throws NoSuchElementException if tree does not contain value
     * @throws NullPointerException   if value is null
     * @return removed value
     */
    T remove(T value) throws NoSuchElementException, NullPointerException;
}
