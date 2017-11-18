package labExam1;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a time slot: a day of the week, and a starting time and 
 * ending time.
 */
public class TimeSlot {
	private DayOfWeek day;
	private Time startTime;
    private Time endTime;

	/**
	 * Creates a new TimeSlot based on the specification.
	 * 
	 * @param spec The TimeSlot specification: a day, a starting time, and an ending time,
	 * separated by space characters
	 */
	public TimeSlot(String spec) {
	    int firstSpace = spec.indexOf(' ');
	    int lastSpace = spec.lastIndexOf(' ');
	    switch (spec.substring(0, firstSpace)) {
            case "Mon": day = DayOfWeek.MONDAY; break;
            case "Tue": day = DayOfWeek.TUESDAY; break;
            case "Wed": day = DayOfWeek.WEDNESDAY; break;
            case "Thu": day = DayOfWeek.THURSDAY; break;
            case "Fri": day = DayOfWeek.FRIDAY; break;
            default: throw new IllegalArgumentException("day of the week is invalid");
        }
        startTime = new Time(spec.substring(firstSpace + 1, lastSpace));
	    endTime = new Time(spec.substring(lastSpace + 1));
	}

    public DayOfWeek getDay() {
        return day;
    }

    public TimeSlot setDay(DayOfWeek day) {
        this.day = day;
        return this;
    }

    public Time getStartTime() {
        return startTime;
    }

    public TimeSlot setStartTime(Time startTime) {
        this.startTime = startTime;
        return this;
    }

    public Time getEndTime() {
        return endTime;
    }

    public TimeSlot setEndTime(Time endTime) {
        this.endTime = endTime;
        return this;
    }

    public boolean overlap(TimeSlot other) {
        return this.day == other.day &&
                this.startTime.precedes(other.endTime) &&
                other.startTime.precedes(this.endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return day == timeSlot.day &&
                Objects.equals(startTime, timeSlot.startTime) &&
                Objects.equals(endTime, timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startTime, endTime);
    }

    @Override
    public String toString() {
        return day.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                + " " + startTime
                + " - " + endTime;
    }
}
