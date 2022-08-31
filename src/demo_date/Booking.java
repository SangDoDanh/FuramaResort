package demo_date;

import java.time.LocalDate;

public class Booking {
    private LocalDate start;
    private LocalDate end;
    private String id;

    public Booking(LocalDate start, LocalDate end, String id) {
        this.start = start;
        this.end = end;
        this.id = id;
    }

    public Booking() {
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "start=" + start +
                ", end=" + end +
                ", id='" + id + '\'' +
                '}';
    }
}
