package assignment10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    BinaryMaxHeap<Integer> intHeap = new BinaryMaxHeap<Integer>();
    List<Integer> list = new ArrayList<Integer>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 100; i++){
            intHeap.add(i);
            list.add(i);
        }
    }

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
    void extractMaxThrowsTest() {
        assertThrows(NoSuchElementException.class, () -> { emptyHeap.extractMax(); });
    }

    @Test
    void peekTest() {
        assertEquals(intHeap.peek(), 100);
    }

    @Test
    void peekThrowTest() {
        assertThrows(NoSuchElementException.class, () -> { emptyHeap.peek(); });
    }

    @Test
    void sizeTest() {
        assertEquals(intHeap.size(), 101);
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
}
