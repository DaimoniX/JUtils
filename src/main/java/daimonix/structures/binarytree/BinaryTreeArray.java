package daimonix.structures.binarytree;

import java.util.NoSuchElementException;

public class BinaryTreeArray<T> implements BinaryTree<T> {
    private T[] nodes;
    private int nodeCount;
    private int capacity;

    public BinaryTreeArray(T root) {
        if (root == null)
            throw new NullPointerException();
        capacity = 2;
        growArray();
        nodeCount = 1;
        nodes[0] = root;
    }

    @Override
    public boolean isEmpty() {
        return nodeCount == 0;
    }

    @Override
    public int getNodeCount() {
        growArray();
        return nodeCount;
    }

    private int getElementIndex(T value) {
        if (value == null)
            throw new NullPointerException();
        for (int i = 0; i < nodes.length; i++)
            if (nodes[i] == value)
                return i;
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return getElementIndex(value) > -1;
    }

    @Override
    public void addChild(T parent, T value) throws NoSuchElementException, NullPointerException {
        if (value == null)
            throw new NullPointerException();
        int parentIndex = getElementIndex(parent);

        if (parentIndex < 0)
            throw new NoSuchElementException();
        
        int childIndex = parentIndex * 2 + 1;

        while (childIndex + 1 >= capacity)
            growArray();
        
        if (nodes[childIndex] == null)
            nodes[childIndex] = value;
        else if (nodes[childIndex + 1] == null)
            nodes[childIndex + 1] = value;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public T remove(T value) throws NoSuchElementException, NullPointerException {
        int targetIndex = getElementIndex(value);

        if (targetIndex < 0)
            throw new NoSuchElementException();
        
        boolean hasLeftChild = targetIndex * 2 + 1 < capacity && nodes[targetIndex * 2 + 1] != null;
        boolean hasRightChild = targetIndex * 2 + 2 < capacity && nodes[targetIndex * 2 + 2] != null;

        if (!hasLeftChild && !hasRightChild) {
            nodes[targetIndex] = null;
        } else if (hasLeftChild && hasRightChild) {
            int freeNode = findFreeNode(targetIndex);
            nodes[targetIndex] = nodes[freeNode];
            nodes[freeNode] = null;
        } else {
            if (hasLeftChild) {
                nodes[targetIndex] = nodes[targetIndex * 2 + 1];
                nodes[targetIndex * 2 + 1] = null;
            } else {
                nodes[targetIndex] = nodes[targetIndex * 2 + 2];
                nodes[targetIndex * 2 + 2] = null;
            }
        }

        nodeCount--;
        return value;
    }

    private int findFreeNode(int root) {
        while (root * 2 + 1 < capacity && nodes[root * 2 + 1] != null)
            root = root * 2 + 1;
        return root;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        T[] temp = nodes;
        capacity *= 2;
        nodes = (T[]) new Object[capacity];
        if (temp != null)
            System.arraycopy(temp, 0, nodes, 0, temp.length);
    }

    @Override
    public String toString() {
        return printTree(0);
    }

    private String printTree(int rootIndex) {
        if (rootIndex >= capacity || nodes[rootIndex] == null)
            return "()";
        String subtree = "(" + nodes[rootIndex];
        subtree += printTree(rootIndex * 2 + 1);
        subtree += printTree(rootIndex * 2 + 2);
        return subtree + ")";
    }
}
