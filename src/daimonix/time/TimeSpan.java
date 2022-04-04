package daimonix.time;

public class TimeSpan implements Comparable<TimeSpan> {
    private final String _string;
    public final int Days;
    public final int Hours;
    public final int Minutes;
    public final int Seconds;
    public final int Milliseconds;

    public TimeSpan(long nanoseconds) {
        this(0, 0, 0, (int) (nanoseconds / 1000000 / 1000), (int) (nanoseconds / 1000000 % 1000));
    }

    public TimeSpan(int days, int hours, int minutes, int seconds, int milliseconds) {
        seconds += milliseconds / 1000;
        this.Milliseconds = milliseconds % 1000;
        minutes += seconds / 60;
        this.Seconds = seconds % 60;
        hours += minutes / 60;
        this.Minutes = minutes % 60;
        days += hours / 24;
        this.Hours = hours % 24;
        this.Days = days;

        StringBuilder sb = new StringBuilder();
        sb.append("TimeSpan {");
        if (this.Days > 0)
            sb.append(this.Days).append("d, ");
        if (this.Hours > 0)
            sb.append(this.Hours).append("h, ");
        if (this.Minutes > 0)
            sb.append(this.Minutes).append("m, ");
        sb.append(this.Seconds);
        if (this.Milliseconds > 0)
            sb.append(".").append(String.format("%03d", this.Milliseconds));
        sb.append("s}");
        _string = sb.toString();
    }

    public TimeSpan(int days, int hours, int minutes, int seconds) {
        this(days, hours, minutes, seconds, 0);
    }

    public TimeSpan(int hours, int minutes, int seconds) {
        this(0, hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj instanceof TimeSpan)
            return this.compareTo((TimeSpan) obj) == 0;

        return false;
    }

    @Override
    public String toString() {
        return _string;
    }

    @Override
    public int compareTo(TimeSpan other) {
        if (other == this)
            return 0;

        if (this.Days != other.Days)
            return Integer.compare(this.Days, other.Days);
        if (this.Hours != other.Hours)
            return Integer.compare(this.Hours, other.Hours);
        if (this.Minutes != other.Minutes)
            return Integer.compare(this.Minutes, other.Minutes);
        if (this.Seconds != other.Seconds)
            return Integer.compare(this.Seconds, other.Seconds);
        if (this.Milliseconds != other.Milliseconds)
            return Integer.compare(this.Milliseconds, other.Milliseconds);

        return 0;
    }
}
