package daimonix.structures.binarytree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTreeList<T extends Comparable<T>> implements BinarySearchTree<T> {
    private TreeNode<T> root;
    private int nodeCount;

    public BinarySearchTreeList(T root) {
        if (root == null)
            throw new NullPointerException();
        this.root = new TreeNode<>(root);
        nodeCount = 1;
    }

    private TreeNode<T> findNode(T value, TreeNode<T> root) {
        if (root == null)
            return null;
        if (root.value.equals(value))
            return root;
        TreeNode<T> child = findNode(value, root.leftChild);
        if (child != null)
            return child;
        child = findNode(value, root.rightChild);
        return child;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public boolean contains(T value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        return findNode(value, root) != null;
    }

    public void add(T value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        if (isEmpty())
            root = new TreeNode<>(value);
        else
            add(value, root);
    }

    private void add(T value, TreeNode<T> root) {
        if (value.compareTo(root.value) < 0) {
            if (root.leftChild == null)
                root.leftChild = new TreeNode<>(value, root, null, null);
            else
                add(value, root.leftChild);
        } else {
            if (root.rightChild == null)
                root.rightChild = new TreeNode<>(value, root, null, null);
            else
                add(value, root.rightChild);
        }
    }

    @Override
    public void addChild(T parent, T value) throws NoSuchElementException, NullPointerException {
        TreeNode<T> parentNode = findNode(parent, root);

        if (parentNode.leftChild == null)
            parentNode.leftChild = new TreeNode<>(value, parentNode, null, null);
        else if (parentNode.rightChild == null)
            parentNode.rightChild = new TreeNode<>(value, parentNode, null, null);
        else
            throw new NoSuchElementException();

        nodeCount++;
    }

    @Override
    public T remove(T value) throws NoSuchElementException, NullPointerException {
        if (value == null)
            throw new NullPointerException();
        var targetNode = findNode(value, root);
        if (targetNode == null)
            throw new NoSuchElementException();

        var parent = targetNode.root;

        if (targetNode.leftChild == null && targetNode.rightChild == null) {
            if (targetNode != root) {
                if (parent.leftChild == targetNode)
                    parent.leftChild = null;
                else
                    parent.rightChild = null;
            } else
                root = null;
        } else if (targetNode.leftChild != null && targetNode.rightChild != null) {
            TreeNode<T> freeNode = findFreeNode(targetNode.rightChild);
            targetNode.value = freeNode.value;
            if (freeNode.root.leftChild == freeNode)
                freeNode.root.leftChild = null;
            else
                freeNode.root.rightChild = null;
        } else {
            TreeNode<T> child = targetNode.leftChild != null ? targetNode.leftChild : targetNode.rightChild;
            if (targetNode != root) {
                if (targetNode == parent.leftChild)
                    parent.leftChild = child;
                else
                    parent.rightChild = child;
            } else
                root = child;
        }

        nodeCount--;
        return value;
    }

    private TreeNode<T> findFreeNode(TreeNode<T> root) {
        while (root.leftChild != null)
            root = root.leftChild;
        return root;
    }

    @Override
    public String toString() {
        return printTree(root);
    }

    private String printTree(TreeNode<T> root) {
        if (root == null)
            return "()";
        String tree = "(" + root.value;
        tree += printTree(root.leftChild);
        tree += printTree(root.rightChild);
        return tree + ")";
    }

    @Override
    public List<T> getElementsAsc() {
        var list = new LinkedList<T>();
        traverse(list, root);
        return list;
    }

    private void traverse(LinkedList<T> l, TreeNode<T> node) {
        if (node.leftChild != null)
            traverse(l, node.leftChild);
        l.add(node.value);
        if (node.rightChild != null)
            traverse(l, node.rightChild);
    }

    @Override
    public List<T> getElementsDesc() {
        var list = getElementsAsc();
        Collections.reverse(list);
        return list;
    }

    private static class TreeNode<V extends Comparable<V>> {
        TreeNode<V> root;
        V value;
        TreeNode<V> leftChild;
        TreeNode<V> rightChild;

        public TreeNode(V value) {
            this(value, null, null, null);
        }

        public TreeNode(V value, TreeNode<V> root, TreeNode<V> leftChild, TreeNode<V> rightChild) {
            this.value = value;
            this.root = root;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
}
