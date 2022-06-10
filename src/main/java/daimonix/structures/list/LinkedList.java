package daimonix.structures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements List<T> {
    private int size;
    private ListNode<T> firstNode;
    private ListNode<T> lastNode;

    public LinkedList() {
        this.size = 0;
        lastNode = firstNode = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void removeFirst() {
        if (size > 1)
            firstNode = firstNode.next;
        else
            firstNode = lastNode = null;
        size--;
    }

    @Override
    public void removeAt(int index) throws NullPointerException, IndexOutOfBoundsException {
        if (firstNode == null)
            throw new NullPointerException("List is empty!");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            removeFirst();
        else if (index == size - 1)
            removeLast();
        else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            size--;
        }
    }

    @Override
    public void remove(T value) {
        ListNode<T> node = firstNode;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                removeAt(i);
                return;
            }
            node = node.next;
        }
    }

    @Override
    public boolean contains(T value) {
        ListNode<T> node = firstNode;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value))
                return true;
            node = node.next;
        }
        return false;
    }

    @Override
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

    @Override
    public T getFirst() {
        return firstNode.value;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void set(T value, int index) throws NullPointerException, IndexOutOfBoundsException {
        if (firstNode == null)
            throw new NullPointerException("List is empty!");
        getNode(index).value = value;
    }

    @Override
    public T getLast() {
        return lastNode.value;
    }

    public void insert(T element) {
        ListNode<T> newNode = new ListNode<>(element, firstNode);
        if (firstNode == null)
            lastNode = newNode;
        firstNode = newNode;
        size++;
    }

    public void insertAt(T element, int index) {
        if (index == 0) {
            insert(element);
        } else if (index == size - 1) {
            add(element);
        } else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = new ListNode<>(element, prevNode.next);
            size++;
        }
    }

    @Override
    public T add(T value) {
        LinkedList.ListNode<T> newNode = new LinkedList.ListNode<>(value, null);
        if (lastNode == null)
            firstNode = newNode;
        else
            lastNode.next = newNode;
        lastNode = newNode;
        size++;
        return value;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    private ListNode<T> getNode(int index) throws IndexOutOfBoundsException {
        if (!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        ListNode<T> node = firstNode;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{");
        int index = 0;
        for (var el : this) {
            sb.append(el);
            if (index + 1 < size)
                sb.append(", ");
            index++;
        }
        return sb.append("}").toString();
    }

    @Override
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
            return node.next != null;
        }

        @Override
        public V next() {
            if (!hasNext())
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

    @Override
    public T[] toArray(T[] array) {
        T[] res = Arrays.copyOf(array, size);
        int index = 0;
        for (var value : this)
            res[index++] = value;
        return res;
    }
}
