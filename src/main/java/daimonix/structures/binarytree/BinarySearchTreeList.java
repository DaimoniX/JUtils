package daimonix.structures.binarytree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTreeList<T extends Comparable<T>> extends BinaryTreeList<T> implements BinarySearchTree<T> {
    public BinarySearchTreeList(T root) {
        super(root);
    }

    @Override
    public void add(T value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        if (isEmpty())
            root = new TreeNode<>(value);
        else
            add(value, root);
        nodeCount++;
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
        if(parentNode == null)
            throw new NoSuchElementException();

        if (parentNode.leftChild == null)
            parentNode.leftChild = new TreeNode<>(value, parentNode, null, null);
        else if (parentNode.rightChild == null)
            parentNode.rightChild = new TreeNode<>(value, parentNode, null, null);
        else
            throw new NoSuchElementException();

        nodeCount++;
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
}
