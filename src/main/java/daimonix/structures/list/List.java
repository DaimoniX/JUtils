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
     * @param index index of element
     * @throws NullPointerException if list is empty
     * @throws IndexOutOfBoundsException if index is outside of list bounds
     * @return element with specified index
     */
    public T get(int index) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Sets the value of element at the specified index in list
     * @param value value of element
     * @param index index of element
     * @throws NullPointerException if list is empty
     * @throws IndexOutOfBoundsException if index is outside of list bounds
     */
    public void set(T value, int index) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Adds an element to the end of list.
     * @param value element to add to list
     * @return added value
     */
    public T add(T value);

    /**
     * Returns true if list contains specified element.
     * @param value element to search for in list
     * @return true if list contains specified element
     */
    public boolean contains(T value);

    /**
     * Removes specified value from list
     * @param value element to remove from list
     */
    public void remove(T value);

    /**
     * Removes element with specified index from list
     * @param index index of element
     * @throws NullPointerException if list is empty
     * @throws IndexOutOfBoundsException if index is outside of list bounds
     */
    public void removeAt(int index) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Removes all elements in list
     */
    public void removeAll();

    /**
     * Returns array of elements in list as object array
     * @return array of elements as object array
     */
    public Object[] toArray();

    /**
     * Returns array of elements in list
     * @param array array to reference type
     * @return array of elements
     */
    public T[] toArray(T[] array);
}
