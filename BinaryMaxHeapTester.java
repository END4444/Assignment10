package assignment10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static assignment10.FindKLargest.findKLargestHeap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryMaxHeapTester {
    List<Integer> list = new ArrayList<Integer>();
    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 100; i++){
            list.add(i);
        }

    }

    @Test
    void addTest() {
        BinaryMaxHeap<Integer> IntHeap = new BinaryMaxHeap<Integer>();
        for (int i = 0; i < 10; i++) {
            IntHeap.add(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(IntHeap.extractMax());
        }
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
