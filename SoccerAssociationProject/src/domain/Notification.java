package domain;

import java.util.Date;

public class Notification implements Comparable
{
    String details;
    Date date;

    public Notification(String details, Date date) {
        this.details = details;
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        return  ((Notification)o).date.compareTo(date);
    }
}
