package MyUtils.time;

public class TimeSpan implements Comparable<TimeSpan> {
    private final String _string;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int seconds;
    private final int milliseconds;

    public TimeSpan(long nanoseconds) {
        this(0, 0, 0, (int) (nanoseconds / 1000000 / 1000), (int) (nanoseconds / 1000000 % 1000));
    }

    public TimeSpan(int days, int hours, int minutes, int seconds, int milliseconds) {
        seconds += milliseconds / 1000;
        this.milliseconds = milliseconds % 1000;
        minutes += seconds / 60;
        this.seconds = seconds % 60;
        hours += minutes / 60;
        this.minutes = minutes % 60;
        days += hours / 24;
        this.hours = hours % 24;
        this.days = days;

        StringBuilder sb = new StringBuilder();
        sb.append("TimeSpan {");
        if (this.days > 0)
            sb.append(this.days).append("d, ");
        if (this.hours > 0)
            sb.append(this.hours).append("h, ");
        if (this.minutes > 0)
            sb.append(this.minutes).append("m, ");
        sb.append(this.seconds);
        if (this.milliseconds > 0)
            sb.append(".").append(String.format("%03d", this.milliseconds));
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

        if (this.days != other.days)
            return Integer.compare(this.days, other.days);
        if (this.hours != other.hours)
            return Integer.compare(this.hours, other.hours);
        if (this.minutes != other.minutes)
            return Integer.compare(this.minutes, other.minutes);
        if (this.seconds != other.seconds)
            return Integer.compare(this.seconds, other.seconds);
        if (this.milliseconds != other.milliseconds)
            return Integer.compare(this.milliseconds, other.milliseconds);

        return 0;
    }

}
