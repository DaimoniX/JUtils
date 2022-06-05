package daimonix.structures.binarytree;

import java.util.NoSuchElementException;

public class BinaryTreeList<T> implements BinaryTree<T> {
    private TreeNode<T> root;
    private int nodeCount;

    public BinaryTreeList(T root) {
        if (root == null)
            throw new NullPointerException();
        this.root = new TreeNode<>(root);
        nodeCount = 1;
    }

    private TreeNode<T> findNode(T value, TreeNode<T> root) {
        if (root == null)
            return null;
        if (root.value == value)
            return root;
        var child = findNode(value, root.leftChild);
        if (child != null)
            return child;
        child = findNode(value, root.rightChild);
        return child;
    }

    @Override
    public boolean isEmpty() {
        return nodeCount == 0;
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

    @Override
    public void addChild(T parent, T value) throws NoSuchElementException, NullPointerException {
        var parentNode = findNode(parent, root);
        
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
            var freeNode = findFreeNode(targetNode);
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

    private static class TreeNode<V> {
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
