package MyUtils.time;

public class TimeSpan implements Comparable<TimeSpan> {
    private String _string;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public TimeSpan(long nanoseconds) {
        this.milliseconds = (int) (nanoseconds / 1000000 % 1000);
        this.seconds = (int) (nanoseconds / 1000000 / 1000 % 60);
        this.minutes = (int) (nanoseconds / 1000000 / 1000 / 60 % 60);
        this.hours = (int) (nanoseconds / 1000000 / 1000 / 60 / 60 % 24);
        this.days = (int) (nanoseconds / 1000000 / 1000 / 60 / 60 / 24);
        CreateString();
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
        CreateString();
    }

    public TimeSpan(int days, int hours, int minutes, int seconds) {
        this(days, hours, minutes, seconds, 0);
    }

    public TimeSpan(int hours, int minutes, int seconds) {
        this(0, hours, minutes, seconds);
    }

    private void CreateString() {
        _string = "TimeSpan {";
        if (days > 0)
            _string += days + "d, ";
        if (hours > 0)
            _string += hours + "h, ";
        if (minutes > 0)
            _string += minutes + "m, ";
        _string += seconds;
        if (milliseconds > 0)
            _string += "." + String.format("%03d", milliseconds);
        _string += "s}";
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
