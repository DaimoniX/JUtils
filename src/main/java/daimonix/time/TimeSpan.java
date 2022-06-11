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

    /**
     * Creates and initializes timespan
     * 
     * @param nanoseconds total nanoseconds
     */
    private TimeSpan(long nanoseconds) {
        if (nanoseconds < 0)
            nanoseconds *= -1;
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
            string = preciseString = "TimeSpan{0.0s}";
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("TimeSpan{");

            if (days > 0)
                builder.append(days).append("d ");
            if (hours > 0)
                builder.append(hours).append("h ");
            if (minutes > 0)
                builder.append(minutes).append("m ");
            builder.append(seconds).append(".").append(String.format("%03d", millis));

            string = builder.toString() + "s}";

            if (this.nanos > 0)
                builder.append(String.format("%03d", micros)).append(String.format("%03d", nanos));
            else if (this.micros > 0)
                builder.append(String.format("%03d", micros));

            preciseString = builder.append("s}").toString();
        }
    }

    /**
     * Creates timespan from seconds
     * 
     * @param seconds total seconds
     * @return new TimeSpan instance with specified seconds
     */
    public static TimeSpan fromSeconds(long seconds) {
        return new TimeSpan(seconds * 1000000000L);
    }

    /**
     * Creates new TimeSpan instance with specified time
     * 
     * @param milliseconds
     * @return TimeSpan with specified param
     */
    public static TimeSpan fromMillis(long milliseconds) {
        return new TimeSpan(milliseconds * 1000000L);
    }

    /**
     * Creates new TimeSpan instance with specified time
     * 
     * @param microseconds
     * @return TimeSpan with specified param
     */
    public static TimeSpan fromMicro(long microseconds) {
        return new TimeSpan(microseconds * 1000L);
    }

    /**
     * Creates new TimeSpan instance with specified time
     * 
     * @param nanoseconds
     * @return TimeSpan with specified param
     */
    public static TimeSpan fromNano(long nanoseconds) {
        return new TimeSpan(nanoseconds);
    }

    /**
     * Converts timespan to {@link Duration}.
     * 
     * @return duration
     */
    public Duration toDuration() {
        return Duration.ofNanos(totalNanos);
    }

    /**
     * Gets number of days in timespan.
     * 
     * @return number of days
     */
    public int days() {
        return days;
    }

    /**
     * Gets number of hours in timespan.
     * 
     * @return number of hours
     */
    public int hours() {
        return hours;
    }

    /**
     * Gets number of minutes in timespan.
     * 
     * @return number of minutes
     */
    public int minutes() {
        return minutes;
    }

    /**
     * Gets number of seconds in timespan.
     * 
     * @return number of seconds
     */
    public int seconds() {
        return seconds;
    }

    /**
     * Gets number of milliseconds in timespan.
     * 
     * @return number of milliseconds
     */
    public int millis() {
        return millis;
    }

    /**
     * Gets number of microseconds in timespan.
     * 
     * @return number of microseconds
     */
    public int micros() {
        return micros;
    }

    /**
     * Gets number of nanoseconds in timespan.
     * 
     * @return number of nanoseconds
     */
    public int nanos() {
        return nanos;
    }

    /**
     * Gets total number of nanoseconds in timespan.
     * 
     * @return total number of nanoseconds
     */
    public long totalNanos() {
        return totalNanos;
    }

    /**
     * Returns difference of this and specified timespan.
     * 
     * @param time timespan to find difference
     * @return new TimeSpan with time difference
     */
    public TimeSpan getDifference(TimeSpan time) {
        return new TimeSpan(this.totalNanos - time.totalNanos);
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

    /**
     * Returns more accurate string representation of TimeSpan
     * 
     * @return String
     */
    public String toPreciseString() {
        return preciseString;
    }

    @Override
    public int compareTo(TimeSpan other) {
        return Long.compare(this.totalNanos, other.totalNanos);
    }
}
