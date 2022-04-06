package daimonix.time;

import java.time.Duration;

public class TimeSpan implements Comparable<TimeSpan> {
    private final String _string;
    private final String _preciseString;
    public final int Days;
    public final int Hours;
    public final int Minutes;
    public final int Seconds;
    public final int Milliseconds;
    public final int Microseconds;
    public final int Nanoseconds;
    public final long TotalSeconds;
    public final long TotalMilliseconds;
    public final long TotalNanoseconds;

    public TimeSpan(long nanoseconds) {
        this.TotalNanoseconds = nanoseconds;
        this.TotalMilliseconds = this.TotalNanoseconds / 1000000L;
        this.TotalSeconds = this.TotalNanoseconds / 1000L;
        this.Nanoseconds = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.Microseconds = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.Milliseconds = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.Seconds = (int) (nanoseconds % 60);
        nanoseconds /= 60;
        this.Minutes = (int) (nanoseconds % 60);
        nanoseconds /= 60;
        this.Hours = (int) (nanoseconds % 24);
        nanoseconds /= 24;
        this.Days = (int) nanoseconds;
        if (TotalNanoseconds == 0)
            _string = _preciseString = "0.0s";
        else {
            StringBuilder builder = new StringBuilder();

            if (Days > 0)
                builder.append(Days).append("d ");
            if (Hours > 0)
                builder.append(Hours).append("h ");
            if (Minutes > 0)
                builder.append(Minutes).append("m ");
            builder.append(Seconds);
            if (Milliseconds > 0)
                builder.append(".").append(String.format("%03d", Milliseconds));

            _string = builder.toString() + "s";

            if (this.Nanoseconds > 0)
                builder.append(String.format("%03d", Microseconds)).append(String.format("%03d", Nanoseconds));
            else if (this.Microseconds > 0)
                builder.append(String.format("%03d", Microseconds));

            _preciseString = builder.append("s").toString();
        }
    }

    public static TimeSpan fromSeconds(long seconds) {
        return new TimeSpan(seconds * 1000000000L);
    }

    public static TimeSpan fromMillis(long milliseconds) {
        return new TimeSpan(milliseconds * 1000000L);
    }

    public static TimeSpan fromMicro(long microseconds) {
        return new TimeSpan(microseconds * 1000L);
    }

    public static TimeSpan fromNano(long nanoseconds) {
        return new TimeSpan(nanoseconds);
    }

    public Duration toDuration() {
        return Duration.ofNanos(TotalNanoseconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeSpan)
            return this.compareTo((TimeSpan) obj) == 0;

        return false;
    }

    @Override
    public String toString() {
        return _string;
    }

    public String toPreciseString() {
        return _preciseString;
    }

    @Override
    public int compareTo(TimeSpan other) {
        return Long.compare(this.TotalNanoseconds, other.TotalNanoseconds);
    }
}
