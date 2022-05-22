package daimonix.structures.binarytree;

import java.util.NoSuchElementException;

public interface BinaryTree<T> {
    boolean isEmpty();

    int getNodeCount();

    boolean contains(T value) throws NullPointerException;

    void addChild(T parent, T value) throws NoSuchElementException, NullPointerException;

    T remove(T value) throws NoSuchElementException, NullPointerException;

    String toString();
}
