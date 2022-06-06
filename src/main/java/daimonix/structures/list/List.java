package daimonix.structures.list;

public interface List<T> extends Iterable<T> {
    /**
     * Returns true if list has no elements.
     * @return true if list has no elements
     */
    public boolean empty();

    /**
     * Returns number of elements in list.
     * @return number of elements in list
     */
    public int size();
    
    /**
     * Returns first element in list or throws NullPointerException if list is empty.
     * @throws NullPointerException if list is empty
     * @return first element in list
     */
    public T getFirst() throws NullPointerException;

    /**
     * Returns last element in list or throws NullPointerException if list is empty.
     * @throws NullPointerException if list is empty
     * @return last element in list
     */
    public T getLast() throws NullPointerException;
    
    /**
     * Returns element in list with specified index, throws NullPointerException if list is empty or IndexOutOfBoundsException if index is outside of list bounds.
     * @throws NullPointerException if list is empty
     * @throws IndexOutOfBoundsException if index is outside of list bounds
     * @return element with specified index
     */
    public T get(int index) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Adds an element to the end of list.
     * @return added value
     */
    public T add(T value);

    /**
     * Returns true if list contains specified element.
     * @return true if list contains specified element
     */
    public boolean contains(T value);

    /**
     * Removes specified value from list
     */
    public void remove(T value);

    /**
     * Removes element with specified index from list
     * @throws NullPointerException if list is empty
     * @throws IndexOutOfBoundsException if index is outside of list bounds
     */
    public void removeAt(int index) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Removes all elements in list
     */
    public void removeAll();
}
