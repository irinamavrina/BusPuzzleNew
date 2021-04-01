package project;

import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Entry {
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String busCompany;
    private static final int MINUTES_IN_DAY = 1440;

    public Entry(LocalTime departureTime, LocalTime arrivalTime, String busCompany) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.busCompany = busCompany;
    }

    public long routeTime() {
        if (!this.pastMidnight())
            return MINUTES.between(this.departureTime, this.arrivalTime);
        return MINUTES.between(this.departureTime, this.arrivalTime) + MINUTES_IN_DAY;

    }

    public boolean pastMidnight() {
        return MINUTES.between(this.departureTime, this.arrivalTime) < 0;
    }

    public boolean isMoreEfficient(Entry e) {
        if (this.departureTime.equals(e.departureTime) && this.arrivalTime.equals(e.arrivalTime)) {
            if (this.busCompany.equals("Posh")) return true;
        }
        if (this.departureTime.equals(e.departureTime) && this.routeTime() < e.routeTime()) return true;
        if (this.arrivalTime.equals(e.arrivalTime) && this.routeTime() < e.routeTime()) return true;

        if (this.pastMidnight() && !e.pastMidnight()) {
            return false;
        } else if (!this.pastMidnight() && e.pastMidnight()) {
            if (this.departureTime.getHour() == e.departureTime.getHour()) {
                return this.departureTime.isAfter(e.departureTime) || this.departureTime.equals(e.departureTime);
            }
            if (this.arrivalTime.getHour() == e.arrivalTime.getHour()) {
                return this.arrivalTime.isBefore(e.arrivalTime) || this.arrivalTime.equals(e.arrivalTime);
            }
            return false;
        }
        return this.departureTime.isAfter(e.departureTime) && this.arrivalTime.isBefore(e.arrivalTime);
    }


    @Override
    public String toString() {
        return busCompany +
                " " + departureTime +
                " " + arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(departureTime, entry.departureTime) &&
                Objects.equals(arrivalTime, entry.arrivalTime) &&
                Objects.equals(busCompany, entry.busCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureTime, arrivalTime, busCompany);
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getBusCompany() {
        return busCompany;
    }
}
