package domain;

import java.util.Date;

public class Notification
{
    String details;
    Date date;



    public Notification (String details)
    {
        this.details=details;
        date = new Date();
    }


}
