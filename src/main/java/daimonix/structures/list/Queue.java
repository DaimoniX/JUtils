package daimonix.structures.list;

import java.util.NoSuchElementException;

public class Queue<T> {
    private final int maxSize;
    private final boolean areNullElementsAllowed;
    private final LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
        maxSize = -1;
        areNullElementsAllowed = true;
    }

    public Queue(int maxSize, boolean areNullElementsAllowed) {
        list = new LinkedList<>();
        this.maxSize = maxSize;
        this.areNullElementsAllowed = areNullElementsAllowed;
    }

    public boolean add(T item) {
        if (!canAdd())
            throw new IllegalStateException();
        if (item == null && !areNullElementsAllowed)
            throw new NullPointerException();
        list.add(item);
        return true;
    }

    public T element() {
        if(list.empty())
            throw new NoSuchElementException();
        return list.getFirst();
    }

    public boolean offer(T e) {
        if (!canAdd())
            return false;
        if (e == null && !areNullElementsAllowed)
            throw new NullPointerException();
        list.add(e);
        return true;
    }

    public T poll() {
        if(list.empty())
            return null;
        T el = list.getFirst();
        list.removeFirst();
        return el;
    }

    public T remove() {
        if(list.empty())
            throw new NoSuchElementException();
        T el = list.getFirst();
        list.removeFirst();
        return el;
    }

    public T peek() {
        if(list.empty())
            return null;
        return list.getFirst();
    }

    public boolean empty() {
        return list.empty();
    }

    private boolean canAdd() {
        return maxSize < 1 || (list.size() + 1 <= maxSize);
    }

    public int search(T o) {
        int index = 0;
        for (var el : list) {
            if(el.equals(o))
                return index + 1;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Queue{" + list + '}';
    }
}
