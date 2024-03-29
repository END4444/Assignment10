package assignment10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static assignment10.FindKLargest.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for the binary max heap.
 *
 * @author Austin Allen and Ethan Doman
 * @version July 18, 2023
 */
public class BinaryMaxHeapTester {

    BinaryMaxHeap<Integer> emptyHeap = new BinaryMaxHeap<Integer>();
    BinaryMaxHeap<Integer> intHeap;
    BinaryMaxHeap<String> stringHeap = new BinaryMaxHeap<String>();
    List<Integer> list = new ArrayList<Integer>();
    List<Integer> copyList = new ArrayList<Integer>();
    List<Integer> smallList = new ArrayList<Integer>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 100; i++){
            copyList.add(i);
            list.add(i);
        }
        for (int i = 0; i <= 5; i++){
            smallList.add(i);
        }
        Collections.shuffle(copyList);
        intHeap=new BinaryMaxHeap<Integer>(copyList);
        stringHeap.add("apple");
        stringHeap.add("banana");
        stringHeap.add("cherry");
        stringHeap.add("date");
        stringHeap.add("eggplant");
    }

    // Binary heap tests
    @Test
    void addAndExtractMaxTest() {
        for (int i = 1; i <= 10; i++) {
            emptyHeap.add(i);
        }
        for (int i = 10; i > 0; i--) {
            assertEquals(emptyHeap.extractMax(), i);
        }
    }

    @Test
    void extractMaxStringTest() {
        assertEquals(stringHeap.extractMax(), "eggplant");
        assertEquals(stringHeap.extractMax(), "date");
        assertEquals(stringHeap.extractMax(), "cherry");
        assertEquals(stringHeap.extractMax(), "banana");
        assertEquals(stringHeap.extractMax(), "apple");
    }

    @Test
    void reverseTest() {
        BinaryMaxHeap<Integer> reverseHeap = new BinaryMaxHeap<Integer>(Comparator.reverseOrder());
        for (int i = 0; i <= 10; i++) {
            reverseHeap.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            assertEquals(reverseHeap.extractMax(), i);
        }
    }

    @Test
    void reverseListTest() {
        BinaryMaxHeap<Integer> reverseHeap = new BinaryMaxHeap<Integer>(list, Comparator.reverseOrder());
        for (int i = 0; i <= 100; i++) {
            assertEquals(reverseHeap.extractMax(), i);
        }
    }

    @Test
    void extractMaxThrowsTest() {
        assertThrows(NoSuchElementException.class, () -> { emptyHeap.extractMax(); });
    }

    @Test
    void peekTest() {
        assertEquals(intHeap.peek(), 100);
    }

    @Test
    void stringPeekTest() {
        assertEquals(stringHeap.peek(), "eggplant");
    }

    @Test
    void peekThrowTest() {
        assertThrows(NoSuchElementException.class, () -> { emptyHeap.peek(); });
    }

    @Test
    void sizeTest() {
        assertEquals(intHeap.size(), 101);
        assertEquals(emptyHeap.size(), 0);
        assertEquals(stringHeap.size(), 5);
    }

    @Test
    void isEmptyTest() {
        assertTrue(emptyHeap.isEmpty());
        assertFalse(intHeap.isEmpty());
    }

    @Test
    void clearTest() {
        assertFalse(intHeap.isEmpty());
        intHeap.clear();
        assertTrue(intHeap.isEmpty());
    }

    // Kth largest tests
    @Test
    void kthLargestTest() {
        List<Integer> temp = findKLargestHeap(list, 4);
        List<Integer> tester = new ArrayList<Integer>();
        tester.add(100);
        tester.add(99);
        tester.add(98);
        tester.add(97);
        assertEquals(tester, temp);
    }

    @Test
    void kthLargestReverseTest() {
        List<Integer> temp = findKLargestHeap(list, 4, Comparator.reverseOrder());
        List<Integer> tester = new ArrayList<Integer>();
        tester.add(0);
        tester.add(1);
        tester.add(2);
        tester.add(3);
        assertEquals(tester, temp);
    }

    @Test
    void kthLargestSortTest() {
        List<Integer> temp = findKLargestSort(list, 4);
        List<Integer> tester = new ArrayList<Integer>();
        tester.add(100);
        tester.add(99);
        tester.add(98);
        tester.add(97);
        assertEquals(tester, temp);
    }

    @Test
    void kthLargestReverseSortTest() {
        List<Integer> temp = findKLargestSort(list, 4, Comparator.reverseOrder());
        List<Integer> tester = new ArrayList<Integer>();
        tester.add(0);
        tester.add(1);
        tester.add(2);
        tester.add(3);
        assertEquals(tester, temp);
    }

    @Test
    void kethLargestThrowTestHeap() {
        assertThrows(IllegalArgumentException.class, () -> { findKLargestHeap(list, -1); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestHeap(list, 1000); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestHeap(list, -1, Comparator.reverseOrder()); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestHeap(list, 1000, Comparator.reverseOrder()); });
    }

    @Test
    void kethLargestThrowTestSort() {
        assertThrows(IllegalArgumentException.class, () -> { findKLargestSort(list, -1); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestSort(list, 1000); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestSort(list, -1, Comparator.reverseOrder()); });
        assertThrows(IllegalArgumentException.class, () -> { findKLargestSort(list, 1000, Comparator.reverseOrder()); });
    }

    @Test
    void toArrayTest() {
        Integer[] integerArray = new Integer[]{5,4,2,3,1,0};
        BinaryMaxHeap<Integer> smallHeap = new BinaryMaxHeap<Integer>(smallList);
        assertArrayEquals(integerArray, smallHeap.toArray());
    }
}
