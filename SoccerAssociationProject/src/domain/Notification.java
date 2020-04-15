package domain;

import java.util.Date;

public class Notification
{
    String details;
    Date date;

    public Notification(String details, Date date) {
        this.details = details;
        this.date = date;
    }
}
