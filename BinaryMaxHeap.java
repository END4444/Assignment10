package assignment10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap <E> implements PriorityQueue<E>{
    private E[] backingArray;
    int size;
    int startingArraySize =10;
    Comparator<? super E> cmp;
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(){

        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(Comparator<? super E> cmp){
        this.cmp=cmp;
        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list){
        size = list.size();
        buildHeap(list);
    }
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp){
        this.cmp = cmp;
        backingArray = (E[]) new Object[list.size()];
        size = list.size();
        buildHeap(list);
    }

    @Override
    public void add(E item) {
        if(size == backingArray.length - 1)
            this.growArray(backingArray.length*2 + 1);
        size++;

        percolateUp(item, size);

    }

    @Override
    public E peek() throws NoSuchElementException {
        if (backingArray[1]==null)
            throw new NoSuchElementException("there is no max element to return");
        return backingArray[1];
    }

    @Override
    public E extractMax() throws NoSuchElementException {
        E maxItem = this.peek();

        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        percolateDown(1);


        return maxItem;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        backingArray = (E[]) new Object[startingArraySize];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for(int i = 1; i<size; i++)
            newArray[i] = backingArray[i];
        return newArray;
    }
    @SuppressWarnings("unchecked")
    private void buildHeap(List<? extends E> list){
         backingArray = (E[]) new Object[list.size()+1];
        for (int i=1; i< backingArray.length;i++)
            backingArray[i] = list.get(i-1);
        for(int i = (backingArray.length-1)/2; i > 0; i--)
            percolateDown(i);


    }

    private void percolateUp(E item, int i){
        for( backingArray[0]=item; innerCompare(item, backingArray[i/2]) < 0; i /= 2)
            backingArray[i] = backingArray[i/2];
        backingArray[i] = item;
        backingArray[0] = null;
    }
    private void percolateDown( int i){
        int child;
        int loc = i;
        E temp = backingArray[i];

        for( ; loc*2 < backingArray.length; loc = child){
            child = loc*2;
            if(child != backingArray.length && innerCompare(backingArray[child+1] , backingArray[child])< 0)
                child++;
            if(innerCompare(temp, backingArray[child])>0)
                backingArray[loc] = backingArray[child];
            else
                break;
        }

        backingArray[loc] = temp;
    }
    @SuppressWarnings("unchecked")
    private int innerCompare(E lhs, E rhs)
    {
        if(lhs == null | rhs == null)
            return -1;
        if(cmp!=null)
            return cmp.compare(rhs,lhs);
        return ((Comparable<? super E>)rhs).compareTo(lhs);
    }
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
