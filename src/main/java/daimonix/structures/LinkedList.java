package daimonix.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {
    private int size;
    private ListNode<T> firstNode;
    private ListNode<T> lastNode;
    
    public LinkedList() {
        this.size = 0;
        lastNode = firstNode = null;
    }
    
    public int size() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }
    
    public void removeFirst() {
        if(size > 1)
            firstNode = firstNode.next;
        else
            firstNode = lastNode = null;
        size--;
    }

    public void removeAt(int index) {
        if(index == 0)
            removeFirst();
        else if(index == size - 1)
            removeLast();
        else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            size--;
        }
    }

    public void remove(T value) {
        ListNode<T> node = firstNode;
        for(int i = 0; i < size; i++) {
            if(node.value.equals(value)) {
                removeAt(i);
                return;
            }
            node = node.next;
        }
    }

    public boolean contains(T value) {
        ListNode<T> node = firstNode;
        for(int i = 0; i < size; i++) {
            if(node.value.equals(value))
                return true;
            node = node.next;
        }
        return false;
    }

    public void removeAll() {
        size = 0;
        firstNode = lastNode = null;
    }

    public void removeLast() {
        ListNode<T> node = firstNode;
        while (node.next != null && node.next != lastNode)
            node = node.next;
        lastNode = node;
        node.next = null;
        size--;
    }

    public T getFirst() {
        return firstNode.value;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    public T getLast() {
        return lastNode.value;
    }

    public void insert(T element) {
        ListNode<T> newNode = new ListNode<>(element, firstNode);
        if(firstNode == null)
            lastNode = newNode;
        firstNode = newNode;
        size++;
    }

    public void insertAt(T element, int index) {
        if(index == 0) {
            insert(element);
        } else if(index == size - 1) {
            add(element);
        }
        else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = new ListNode<>(element, prevNode.next);
            size++;
        }
    }

    public void add(T element) {
        LinkedList.ListNode<T> newNode = new LinkedList.ListNode<>(element, null);
        if(lastNode == null)
            firstNode = newNode;
        else
            lastNode.next = newNode;
        lastNode = newNode;
        size++;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    private ListNode<T> getNode(int index) {
        if(!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        ListNode<T> node = firstNode;
        for(int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        ListNode<T> node = firstNode;
        for (int i = 0; i < size; i++) {
            arr[i] = node.value;
            node = node.next;
        }
        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    private static class LinkedListIterator<V> implements Iterator<V> {
        private final LinkedList<V> list;
        private ListNode<V> node;

        public LinkedListIterator(LinkedList<V> list) {
            this.list = list;
            node = new ListNode<>(null, this.list.firstNode);
        }

        @Override
        public boolean hasNext() {
            return node != list.lastNode;
        }

        @Override
        public V next() {
            if(!hasNext())
                throw new NoSuchElementException();
            node = node.next;
            return node.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private static class ListNode<V> {
        V value;
        ListNode<V> next;

        public ListNode(V value, ListNode<V> next) {
            this.value = value;
            this.next = next;
        }
    }
}
