package domain;

import java.util.Date;

public class Notification implements Comparable
{
    private String details;
    private Date date;

    public Notification(String details, Date date)
    {
        this.details = details;
        this.date = date;
    }

    @Override
    public int compareTo(Object o)
    {
        return  ((Notification)o).date.compareTo(date);
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
