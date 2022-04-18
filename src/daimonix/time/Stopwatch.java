package daimonix.time;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch() {
        startTime = stopTime = System.nanoTime();
    }

    public void start() {
        if (isRunning)
            return;
        startTime = System.nanoTime();
        isRunning = true;
    }

    public void restart() {
        if (!isRunning)
            return;
        startTime = System.nanoTime();
    }

    public void stop() {
        if (!isRunning)
            return;
        stopTime = System.nanoTime();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getElapsedNanoseconds() {
        if (isRunning)
            return System.nanoTime() - startTime;
        return stopTime - startTime;
    }

    public long getElapsedMilliseconds() {
        return getElapsedNanoseconds() / 1000000;
    }

    public TimeSpan getElapsedTime() {
        return TimeSpan.fromNano(getElapsedNanoseconds());
    }
}
