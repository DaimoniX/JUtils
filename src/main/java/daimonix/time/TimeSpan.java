package daimonix.time;

import java.time.Duration;

public class TimeSpan implements Comparable<TimeSpan> {
    private final String string;
    private final String preciseString;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int seconds;
    private final int millis;
    private final int micros;
    private final int nanos;
    private final long totalNanos;

    private TimeSpan(long nanoseconds) {
        this.totalNanos = nanoseconds;
        this.nanos = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.micros = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.millis = (int) (nanoseconds % 1000);
        nanoseconds /= 1000;
        this.seconds = (int) (nanoseconds % 60);
        nanoseconds /= 60;
        this.minutes = (int) (nanoseconds % 60);
        nanoseconds /= 60;
        this.hours = (int) (nanoseconds % 24);
        nanoseconds /= 24;
        this.days = (int) nanoseconds;
        if (totalNanos == 0)
            string = preciseString = "0.0s";
        else {
            StringBuilder builder = new StringBuilder();

            if (days > 0)
                builder.append(days).append("d ");
            if (hours > 0)
                builder.append(hours).append("h ");
            if (minutes > 0)
                builder.append(minutes).append("m ");
            builder.append(seconds).append(".").append(String.format("%03d", millis));

            string = builder.toString() + "s";

            if (this.nanos > 0)
                builder.append(String.format("%03d", micros)).append(String.format("%03d", nanos));
            else if (this.micros > 0)
                builder.append(String.format("%03d", micros));

            preciseString = builder.append("s").toString();
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
        return Duration.ofNanos(totalNanos);
    }
    
    public int days() {
        return days;
    }

    public int hours() {
        return hours;
    }

    public int minutes() {
        return minutes;
    }

    public int seconds() {
        return seconds;
    }

    public int millis() {
        return millis;
    }
    
    public int micros() {
        return micros;
    }

    public int nanos() {
        return nanos;
    }

    public long totalNanos() {
        return totalNanos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeSpan)
            return this.compareTo((TimeSpan) obj) == 0;

        return false;
    }

    @Override
    public String toString() {
        return string;
    }

    public String toPreciseString() {
        return preciseString;
    }

    @Override
    public int compareTo(TimeSpan other) {
        return Long.compare(this.totalNanos, other.totalNanos);
    }
}
