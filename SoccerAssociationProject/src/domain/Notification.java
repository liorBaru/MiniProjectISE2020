package domain;

import java.util.Date;

public class Notification
{
    private String details;
    private Date date;



    public Notification (String details)
    {
        this.details=details;
        date = new Date();
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
