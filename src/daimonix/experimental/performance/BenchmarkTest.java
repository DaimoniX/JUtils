package daimonix.experimental.performance;

import java.io.FileNotFoundException;
import java.io.PrintStream;

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
                return ArrayUtils.CreateSortedArray(size);
            case "random":
                return ArrayUtils.CreateRandomArray(size, seed);
            case "reversed":
                return ArrayUtils.CreateReversedArray(size);
            default:
                return null;
        }
    }
}

public class BenchmarkTest {
    public void Run() throws FileNotFoundException {
        PrintStream ostream = new PrintStream(String.format("output-%d.txt", System.nanoTime()));

        Integer[] testSizes = new Integer[] { 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072 };
        Sorter[] sorters = new Sorter[] { new BubbleSorter(), new InsertionSorter(), new SelectionSorter(),
                new ShellSorter(), new HeapSorter(), };
        String[] modes = new String[] { "sorted", "random", "reversed" };
        ArrayFactory arrayFactory = new ArrayFactory();

        for (Sorter sorter : sorters) {
            ostream.println("===========================================================\n" +
                    "Running benchmarks for " + sorter.getClass().getSimpleName() +
                    "\n===========================================================\n");
            for (Integer size : testSizes) {
                for (String mode : modes) {
                    ostream.println("Benchmark for " + sorter.getClass().getSimpleName() + ", array size: " + size
                            + ", mode: " + mode + "\nExecution time: "
                            + Benchmark.Run(sorter::Sort, arrayFactory.GetArray(mode, size)) + "\n");
                }
            }
        }

        ostream.close();
    }
}
