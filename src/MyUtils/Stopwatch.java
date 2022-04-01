package MyUtils;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch()
    {
        startTime = stopTime = System.nanoTime();
    }

    public void Start()
    {
        startTime = System.nanoTime();
        isRunning = true;
    }

    public void Stop()
    {
        stopTime = System.nanoTime();
        isRunning = false;
    }

    public long GetElapsedNanoseconds()
    {
        if(isRunning)
            return System.nanoTime() - startTime;
        return stopTime - startTime;
    }

    public long GetElapsedMilliseconds()
    {
        return GetElapsedNanoseconds() / 1000000;
    }
}
