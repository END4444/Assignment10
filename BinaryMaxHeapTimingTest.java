package assignment10;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import static assignment10.FindKLargest.findKLargestHeap;
import static assignment10.FindKLargest.findKLargestSort;

public class BinaryMaxHeapTimingTest {
    List<Integer> largeIntegerList = new ArrayList<Integer>();
    List<Integer> nextIntegerList = new ArrayList<Integer>();
    @BeforeEach
    void setUp()
    {
        for(int i = 0; i<=1000000;i++)
        {
            largeIntegerList.add(i);
            nextIntegerList.add(i);
        }
        largeIntegerList.add(1000001);
        Collections.shuffle(nextIntegerList);
    }

    @Test
    void addTime() {

        for (int i = 1; i <= 100; i++) {
            BinaryMaxHeap<Integer> tester = new BinaryMaxHeap<Integer>();
            for (int y = 0; y < 10000 * i; y++) {
                tester.add(largeIntegerList.get(y));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                tester.add((largeIntegerList.get((i * 10000) + 1)) + 1);
                tester.extractMax();
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                largeIntegerList.get((i * 10000) + 1);
                tester.extractMax();
            }
            long costForLoop = System.nanoTime();
            System.out.println((endTime - startTime - (costForLoop - endTime)) / 10000);


        }
    }

    @Test
    void addBestTime() {

        for (int i = 1; i <= 100; i++) {
            BinaryMaxHeap<Integer> tester = new BinaryMaxHeap<Integer>();
            for (int y = 0; y < 10000 * i; y++) {
                tester.add(largeIntegerList.get(y));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                tester.add(nextIntegerList.get(((i * 10000) + 1)) + 1);
                tester.extractMax();
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                largeIntegerList.get(0);
                tester.extractMax();
            }
            long costForLoop = System.nanoTime();
            System.out.println(Math.abs(endTime - startTime - (costForLoop - endTime)) / 10000);


        }
    }
    @Test
    void ExtractMaxTime() {

        for (int i = 1; i <= 100; i++) {
            BinaryMaxHeap<Integer> tester = new BinaryMaxHeap<Integer>();
            for (int y = 0; y < 10000 * i; y++) {
                tester.add(largeIntegerList.get(y));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {

                tester.extractMax();
                tester.add((largeIntegerList.get((i * 10000) + 1)) + 1);
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {
                tester.add((largeIntegerList.get((i * 10000) + 1)) + 1);
            }
            long costForLoop = System.nanoTime();
            System.out.println((endTime - startTime - (costForLoop - endTime)) / 10000);


        }
    }
    @Test
    void PeekTime() {

        for (int i = 1; i <= 100; i++) {
            BinaryMaxHeap<Integer> tester = new BinaryMaxHeap<Integer>();
            for (int y = 0; y < 10000 * i; y++) {
                tester.add(largeIntegerList.get(y));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {

                tester.peek();
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10000; j++) {

            }
            long costForLoop = System.nanoTime();
            System.out.println((endTime - startTime - (costForLoop - endTime)) / 10000);


        }
    }

    @Test
    void kthLargestHeapTime() {

        for (int i = 1; i <= 100; i++) {

            long startTime = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                findKLargestHeap(nextIntegerList,i*10000);
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10; j++) {

            }
            long costForLoop = System.nanoTime();
            System.out.println((endTime - startTime - (costForLoop - endTime)) / 10);


        }
    }

    @Test
    void kthLargestSortTime() {

        for (int i = 1; i <= 100; i++) {

            long startTime = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                findKLargestSort(nextIntegerList,i*10000);
            }
            long endTime = System.nanoTime();
            for (int j = 0; j < 10; j++) {

            }
            long costForLoop = System.nanoTime();
            System.out.println((endTime - startTime - (costForLoop - endTime)) / 10);


        }
    }
}
