package daimonix.time;

public final class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch() {
        startTime = stopTime = System.nanoTime();
    }

    /**
     * Starts the stopwatch, does nothing if watch is already running.
     */
    public void start() {
        if (isRunning)
            return;
        startTime = System.nanoTime();
        isRunning = true;
    }

    /**
     * Retarts the stopwatch, does nothing if watch is not running.
     */
    public void restart() {
        if (!isRunning)
            return;
        startTime = System.nanoTime();
    }

    /**
     * Stops the stopwatch.
     */
    public void stop() {
        if (!isRunning)
            return;
        stopTime = System.nanoTime();
        isRunning = false;
    }

    /**
     * Returs if stop watch is running or not.
     * 
     * @return true if stopwatch is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Returns difference in nanoseconds from the start.
     * 
     * @return difference in nanoseconds
     */
    public long getElapsedNanoseconds() {
        if (isRunning)
            return System.nanoTime() - startTime;
        return stopTime - startTime;
    }

    /**
     * Returns difference in milliseconds from the start.
     * 
     * @return difference in milliseconds
     */
    public long getElapsedMilliseconds() {
        return getElapsedNanoseconds() / 1000000;
    }

    /**
     * Returns difference in TimeSpan from the start.
     * 
     * @return difference in TimeSpan
     */
    public TimeSpan getElapsedTime() {
        return TimeSpan.fromNano(getElapsedNanoseconds());
    }
}
