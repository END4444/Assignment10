package assignment10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creates a binary max heap where the first node is the maximum and each child is less then its parents
 * implements the PriorityQueue interface
 *
 * @author Austin Allen and Ethan Doman
 * @version July 19, 2023
 *
 * @param <E>
 */
public class BinaryMaxHeap <E> implements PriorityQueue<E>{

    private E[] backingArray;
    private int size;
    private int startingArraySize =10;
    private Comparator<? super E> cmp;

    /**
     * Constructor with no arguments
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(){
        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }

    /**
     * Constructor for a custom comparator
     *
     * @param cmp
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(Comparator<? super E> cmp){
        this.cmp=cmp;
        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }

    /**
     * Constructor for building a max heap given a list already in order
     *
     * @param list
     */
    public BinaryMaxHeap(List<? extends E> list){
        size = list.size();
        buildHeap(list);
    }

    /**
     * Constructor for building a max heap given a list and a custom comparator
     *
     * @param list
     * @param cmp
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp){
        this.cmp = cmp;
        backingArray = (E[]) new Object[list.size()];
        size = list.size();
        buildHeap(list);
    }

    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item
     */
    public void add(E item) {
        if(size == backingArray.length - 1)
            this.growArray(backingArray.length*2 + 1);
        size++;
        percolateUp(item, size);
    }

    /**
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    public E peek() throws NoSuchElementException {
        if (backingArray[1]==null)
            throw new NoSuchElementException("there is no max element to return");
        return backingArray[1];
    }

    /**
     * Returns and removes the maximum item this priority queue.
     * O(log N)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    public E extractMax() throws NoSuchElementException {
        E maxItem = this.peek();
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        percolateDown(1);
        return maxItem;
    }

    /**
     * Returns the number of items in this priority queue.
     * O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     *
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for(int i = 1; i <= size; i++)
            newArray[i-1] = backingArray[i];
        return newArray;
    }

    /**
     * Private helper method for building the heap using an ordered list.
     *
     * @param list
     */
    @SuppressWarnings("unchecked")
    private void buildHeap(List<? extends E> list){
         backingArray = (E[]) new Object[list.size()+1];
        for (int i=1; i< backingArray.length;i++)
            backingArray[i] = list.get(i-1);
        for(int i = (backingArray.length-1)/2; i > 0; i--)
            percolateDown(i);
    }

    /**
     * Finds the position the item should be at by starting at the bottom.
     *
     * @param item
     * @param i
     */
    private void percolateUp(E item, int i){
        for( backingArray[0]=item; innerCompare(item, backingArray[i/2]) < 0; i /= 2)
            backingArray[i] = backingArray[i/2];
        backingArray[i] = item;
        backingArray[0] = null;
    }

    /**
     * Finds the position the item should be at by starting at the top.
     *
     * @param i
     */
    private void percolateDown( int i){
        int child;
        int loc = i;
        E temp = backingArray[i];
        for( ; loc*2 <= size; loc = child){
            child = loc*2;
            if(child != size && innerCompare( backingArray[child],backingArray[child+1] )> 0)
                child++;
            if(innerCompare(temp, backingArray[child])>0)
                backingArray[loc] = backingArray[child];
            else
                break;
        }
        backingArray[loc] = temp;
    }

    /**
     * Private helper method for comparing to elements.
     *
     * @param lhs
     * @param rhs
     * @return
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E lhs, E rhs)
    {
        if(lhs == null | rhs == null)
            return -1;
        if(cmp!=null)
            return cmp.compare(rhs,lhs);
        return ((Comparable<? super E>)rhs).compareTo(lhs);
    }

    /**
     * Private helper method for increasing the size of the backing array.
     *
     * @param newSize
     */
    @SuppressWarnings("unchecked")
    private void growArray(int newSize){
        E[] tempArray = (E[]) new Object[newSize];
        for(int i = 1; i< backingArray.length; i++)
        {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    }
}
