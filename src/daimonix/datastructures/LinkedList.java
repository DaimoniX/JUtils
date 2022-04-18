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

    public void removeLast() {
        ListNode<T> node;
        for(node = firstNode; node != lastNode; node = node.next);
        node.next = null;
        length--;
    }

    public T getFirst() {
        return firstNode.value;
    }

    public T getLast() {
        return lastNode.value;
    }

    public T getAt(int index) {
        if(index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        ListNode<T> node = firstNode;
        for(int i = 0; i < length; i++) {
            if(i == index)
                return node.value;
            node = node.next;
        }
        return null;
    }

    public void add(T element) {
        if (length == 0) {
            firstNode = new ListNode<>(element, null);
            lastNode = firstNode;
        } else {
            lastNode.next = new ListNode<>(element, null);
            lastNode = lastNode.next;
        }

        length++;
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

    private class ListNode<V> {
        V value;
        ListNode<V> next;

        public ListNode(V value, ListNode<V> next) {
            this.value = value;
            this.next = next;
        }
    }
}
