package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Timer;
import org.junit.Test;
import scala.Int;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InsertionSortBasicTest {

    @Test
    public void testSortFull1() {
        String[] words = new String[]{"Dog", "Cat", "ferret", "Aardvark", "Fox", "Bat"};
        String[] expectedNormal = new String[]{"Aardvark", "Bat", "Cat", "Dog", "Fox", "ferret"};
        InsertionSortBasic<String> sorter = InsertionSortBasic.create();
        sorter.sort(words);
        assertArrayEquals(expectedNormal, words);
    }

    @Test
    public void testSortFull2() {
        String[] words = new String[]{"Dog", "Cat", "ferret", "Aardvark", "Fox", "Bat"};
        String[] expectedIgnoreCase = new String[]{"Aardvark", "Bat", "Cat", "Dog", "ferret", "Fox"};
        InsertionSortBasic<String> sorter = new InsertionSortBasic<>(String.CASE_INSENSITIVE_ORDER);
        sorter.sort(words);
        assertArrayEquals(expectedIgnoreCase, words);
    }

    @Test
    public void testSortFull3() {
        String[] words = new String[]{"Dog", "Cat", "ferret", "Aardvark", "Fox", "Bat"};
        String[] expectedIgnoreCase = new String[]{"Fox", "ferret", "Dog", "Cat", "Bat", "Aardvark"};
        InsertionSortBasic<String> sorter = new InsertionSortBasic<>(String.CASE_INSENSITIVE_ORDER.reversed());
        sorter.sort(words);
        assertArrayEquals(expectedIgnoreCase, words);
    }

    @Test
    public void testSortPartition() {
        String[] words = new String[]{"Dog", "Cat", "ferret", "Aardvark", "Fox", "Bat"};
        String[] expectedNormal = new String[]{"Dog", "Cat", "Aardvark", "ferret", "Fox", "Bat"};
        InsertionSortBasic<String> sorter = InsertionSortBasic.create();
        sorter.sort(words, 2, 4);
        assertArrayEquals(expectedNormal, words);
    }


    @Test
    public void testInsertionSortBenchmarkTime() {
        int n = 1000;
        System.out.println("n:" + n);
        Integer[] reverse = new Integer[n];
        Integer[] ordered = new Integer[n];
        Integer[] partial = new Integer[n];
        Integer[] rand = new Integer[n];
        for (int i = 0; i < n; i++) {
            reverse[i] = n - i;
            ordered[i] = i;
            rand[i] = (int) (Math.random() * n) + 1;
            if (i < n/2) {
                partial[i] = i;
            } else {
                partial[i] = (int) (Math.random() * n) + 1;
                ;
            }
        }

        InsertionSortBasic<Integer> sorter = InsertionSortBasic.create();
        System.out.println("Reversed:");
        Benchmark<Boolean> bm = new Benchmark_Timer<>(
                "testReverseSort", b -> {
            sorter.sort(reverse);
            return;
        });

        for (int i = 2; i <= 50; i *= 2) {
            double x = bm.run(true, i);
            System.out.println(i + "\t" + x);
        }

        System.out.println("Ordered:");
        Benchmark<Boolean> bm2 = new Benchmark_Timer<>(
                "testOrderedSort", b -> {
            sorter.sort(ordered);
            return;
        });

        for (int i = 2; i <= 50; i *= 2) {
            double x = bm2.run(true, i);
            System.out.println(i + "\t" + x);
        }

        System.out.println("Partial:");
        Benchmark<Boolean> bm3 = new Benchmark_Timer<>(
                "testPartialSort", b -> {
            sorter.sort(partial);
            return;
        });

        for (int i = 2; i <= 50; i *= 2) {
            double x = bm3.run(true, i);
            System.out.println(i + "\t" + x);
        }

        System.out.println("Random:");
        Benchmark<Boolean> bm4 = new Benchmark_Timer<>(
                "testRandomSort", b -> {
            sorter.sort(rand);
            return;
        });

        for (int i = 2; i <= 50; i *= 2) {
            double x = bm4.run(true, i);
            System.out.println(i + "\t" + x);
        }
    }
}