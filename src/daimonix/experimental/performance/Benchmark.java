package daimonix.experimental.performance;

import java.util.function.Consumer;

import daimonix.time.Stopwatch;
import daimonix.time.TimeSpan;

public class Benchmark {
    private static final Stopwatch STOPWATCH = new Stopwatch();

    public static TimeSpan Run(Runnable method) {
        STOPWATCH.Start();
        method.run();
        STOPWATCH.Stop();
        return STOPWATCH.GetElapsedTime();
    }

    public static <T> TimeSpan Run(Consumer<T> method, T parameter) {
        STOPWATCH.Start();
        method.accept(parameter);
        STOPWATCH.Stop();
        return STOPWATCH.GetElapsedTime();
    }
}