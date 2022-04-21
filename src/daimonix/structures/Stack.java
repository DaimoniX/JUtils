package daimonix.structures;

import java.util.EmptyStackException;

public class Stack<T> {
    private final LinkedList<T> list;

    public Stack() {
        list = new LinkedList<T>();
    }

    public T push(T item) {
        list.add(item);
        return list.getLast();
    }

    public T pop() throws EmptyStackException {
        if(empty())
            throw new EmptyStackException();
        T el = list.getLast();
        list.removeLast();
        return el;
    }

    public T peek() throws EmptyStackException {
        if(empty())
            throw new EmptyStackException();
        return list.getLast();
    }

    public boolean empty() {
        return list.empty();
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
        return "Stack{" + list + '}';
    }
}
