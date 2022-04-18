package daimonix.experimental.performance;

import java.util.function.Consumer;

import daimonix.time.Stopwatch;
import daimonix.time.TimeSpan;

public class Benchmark {
    private static final Stopwatch STOPWATCH = new Stopwatch();

    public static TimeSpan Run(Runnable method) {
        STOPWATCH.start();
        method.run();
        STOPWATCH.stop();
        return STOPWATCH.getElapsedTime();
    }

    public static <T> TimeSpan Run(Consumer<T> method, T parameter) {
        STOPWATCH.start();
        method.accept(parameter);
        STOPWATCH.stop();
        return STOPWATCH.getElapsedTime();
    }
}