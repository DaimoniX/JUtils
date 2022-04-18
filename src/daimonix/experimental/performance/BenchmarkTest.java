package daimonix.experimental.performance;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import daimonix.algorithms.sorting.*;
import daimonix.utils.ArrayUtils;

class ArrayFactory {
    private long seed;

    public ArrayFactory() {
        seed = System.nanoTime();
    }

    public Integer[] GetArray(String mode, int size) {
        switch (mode) {
            case "sorted":
                return ArrayUtils.createSortedArray(size);
            case "random":
                return ArrayUtils.createRandomArray(size, seed);
            case "reversed":
                return ArrayUtils.createReversedArray(size);
            default:
                return null;
        }
    }
}

public class BenchmarkTest {
    public void Run() throws FileNotFoundException {
        PrintStream ostream = new PrintStream(String.format("out-%s.txt", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        ArrayFactory arrayFactory = new ArrayFactory();

        Integer[] testSizes = new Integer[] { 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072 };
        Sorter[] sorters = new Sorter[] { new BubbleSorter(), new InsertionSorter(), new SelectionSorter(), new CombSorter(),
                new ShellSorter(), new HeapSorter(), new MergeSorter(), new QuickSorter() };
        String[] modes = new String[] { "sorted", "random", "reversed" };

        for (Sorter sorter : sorters) {
            ostream.println("===========================================================\n" +
                    "Running benchmarks for " + sorter.getClass().getSimpleName() +
                    "\n===========================================================\n");
            for (Integer size : testSizes) {
                for (String mode : modes) {
                    ostream.println("[" + LocalTime.now().truncatedTo(ChronoUnit.SECONDS) + "] Benchmark for " + sorter.getClass().getSimpleName() + ", array size: " + size
                            + ", mode: " + mode + "\nExecution time: {"
                            + Benchmark.Run(sorter::sort, arrayFactory.GetArray(mode, size)).toPreciseString() + "}\n");
                }
            }
        }

        ostream.close();
    }
}
