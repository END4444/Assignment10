package assignment10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Austin Allen and Ethan Doman
 * @version July 19, 2023
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {

		if(k> items.size()||k<0)
			throw new IllegalArgumentException("k is out of range");
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items);
		List<E> tempList = new ArrayList<E>();
		for (int i = 1; i <= k; ++i)
			tempList.add(heap.extractMax()) ;

		return tempList;

	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k> items.size()||k<0)
			throw new IllegalArgumentException("k is out of range");
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items,cmp);
		List<E> tempList = new ArrayList<E>();
		for (int i = 1; i <= k; ++i)
			tempList.add(heap.extractMax()) ;

		return tempList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {

		if(k> items.size()||k<0)
			throw new IllegalArgumentException("k is out of range");
		List<E> itemsCopy = new ArrayList<E>();
		for (E item: items) {
			itemsCopy.add(item);
		}
		itemsCopy.sort(Comparator.naturalOrder());
		List<E> tempList = new ArrayList<E>();
		for(int i = 0; i<=k;i++)
			tempList.add(items.get(i));
		return tempList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k> items.size()||k<0)
			throw new IllegalArgumentException("k is out of range");

		List<E> itemsCopy = new ArrayList<E>();
		for (E item: items) {
			itemsCopy.add(item);
		}
		itemsCopy.sort(cmp);
		List<E> tempList = new ArrayList<E>();
		for(int i = 0; i<=k;i++)
			tempList.add(items.get(i));
		return tempList;
	}
}
