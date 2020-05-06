package main.domain.manageEvents;


import java.util.Date;

public class Notification implements Comparable
{
    private String details;
    private Date date;

    public Notification(String details)
    {
        this.details = details;
        this.date = new Date();
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

    @Override
    public boolean equals(Object object)
    {
        Notification notification = (Notification)object;
        if((notification.getDetails().equals((this.details)))&&
                (notification.date.equals(this.date)))
        {
            return true;
        }
        return false;
    }

}


