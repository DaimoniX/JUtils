package daimonix.datastructures;

public class LinkedList<T> {
    private int length;
    private ListNode<T> firstNode;
    private ListNode<T> lastNode;

    public LinkedList() {
        this.length = 0;
        lastNode = firstNode = null;
    }

    public void removeFirst() {
        if(length > 1)
            firstNode = firstNode.next;
        else
            firstNode = lastNode = null;
        length--;
    }

    public void removeAt(int index) {
        if(index == 0)
            removeFirst();
        else if(index == length - 1)
            removeLast();
        else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            length--;
        }
    }

    public void remove(T value) {
        ListNode<T> node = firstNode;
        for(int i = 0; i < length; i++) {
            if(node.value.equals(value)) {
                removeAt(i);
                return;
            }
            node = node.next;
        }
    }

    public boolean contains(T value) {
        ListNode<T> node = firstNode;
        for(int i = 0; i < length; i++) {
            if(node.value.equals(value))
                return true;
            node = node.next;
        }
        return false;
    }

    public void removeAll(T value) {
        while (contains(value))
            remove(value);
    }

    public void removeLast() {
        ListNode<T> node = firstNode;
        while (node.next != null && node.next != lastNode)
            node = node.next;
        lastNode = node;
        node.next = null;
        length--;
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
        length++;
    }

    public void insertAt(T element, int index) {
        if(index == 0) {
            insert(element);
        } else if(index == length - 1) {
            add(element);
        }
        else {
            ListNode<T> prevNode = getNode(index - 1);
            prevNode.next = new ListNode<>(element, prevNode.next);
            length++;
        }
    }

    public void add(T element) {
        LinkedList.ListNode<T> newNode = new LinkedList.ListNode<>(element, null);
        if(lastNode == null)
            firstNode = newNode;
        else
            lastNode.next = newNode;
        lastNode = newNode;
        length++;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < length;
    }

    private ListNode<T> getNode(int index) {
        if(!isValidIndex(index))
            throw new IndexOutOfBoundsException();
        ListNode<T> node = firstNode;
        for(int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public int length() {
        return this.length;
    }

    public Object[] toArray() {
        Object[] arr = new Object[length];
        ListNode<T> node = firstNode;
        for (int i = 0; i < length; i++) {
            arr[i] = node.value;
            node = node.next;
        }
        return arr;
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
