package MyUtils;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch() {
        startTime = stopTime = System.nanoTime();
    }

    public void Start() {
        if (isRunning)
            return;
        startTime = System.nanoTime();
        isRunning = true;
    }

    public void Restart() {
        if (!isRunning)
            return;
        startTime = System.nanoTime();
    }

    public void Stop() {
        if (!isRunning)
            return;
        stopTime = System.nanoTime();
        isRunning = false;
    }

    public boolean IsRunning() {
        return isRunning;
    }

    public long GetElapsedNanoseconds() {
        if (isRunning)
            return System.nanoTime() - startTime;
        return stopTime - startTime;
    }

    public long GetElapsedMilliseconds() {
        return GetElapsedNanoseconds() / 1000000;
    }
}
