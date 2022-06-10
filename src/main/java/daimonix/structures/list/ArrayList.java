package daimonix.structures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[] {};
        size = 0;
    }

    public int getCapacity() {
        return elements.length;
    }

    private void grow() {
        Object[] temp = elements;
        elements = new Object[temp.length + 8];
        System.arraycopy(temp, 0, elements, 0, temp.length);
    }

    private void throwNullPointer() throws NullPointerException {
        if (elements.length == 0)
            throw new NullPointerException("List is empty!");
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++)
            if (get(i).equals(value))
                return i;
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getFirst() throws NullPointerException {
        throwNullPointer();
        return (T) elements[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getLast() throws NullPointerException {
        throwNullPointer();
        return (T) elements[size];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) throws NullPointerException, IndexOutOfBoundsException {
        throwNullPointer();
        return (T) elements[index];
    }

    @Override
    public T add(T value) {
        if (size + 1 >= getCapacity())
            grow();
        elements[size] = value;
        size++;
        return value;
    }

    @Override
    public void set(T value, int index) throws NullPointerException, IndexOutOfBoundsException {
        throwNullPointer();
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        elements[index] = value;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) > -1;
    }

    @Override
    public void remove(T value) {
        int index = indexOf(value);
        if (index < 0)
            return;
        removeAt(index);
    }

    @Override
    public void removeAt(int index) throws NullPointerException, IndexOutOfBoundsException {
        throwNullPointer();
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Object[] temp = elements;
        System.arraycopy(temp, index + 1, elements, index, size - index);
        elements[size] = null;
        size--;
    }

    @Override
    public void removeAll() {
        elements = new Object[] {};
        size = 0;

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList{");
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
        Object[] res = new Object[size];
        System.arraycopy(elements, 0, res, 0, size);
        return res;
    }

    private static class ArrayListIterator<V> implements Iterator<V> {
        private final ArrayList<V> list;
        private int index;

        public ArrayListIterator(ArrayList<V> list) {
            this.list = list;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < list.size;
        }

        @Override
        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return list.get(index++);
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

    @Override
    public T[] toArray(T[] array) {
        T[] res = Arrays.copyOf(array, size);
        System.arraycopy(elements, 0, res, 0, size);
        return res;
    }
}
