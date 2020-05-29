package domain.manageEvents;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification implements Comparable
{
    private String details;
    private Date date;

    public Notification(String details) throws Exception {
        if(details==null || details.isEmpty())
            throw new Exception("Invalid arguments");
        this.details = details;
        this.date = new Date();
    }

    public Notification(String[] details)
    {
        this.details=details[0];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try
        {
            this.date=dateFormat.parse(details[1]);
        }
        catch (Exception e)
        {
            date = new Date();
        }
    }


    @Override
    public int compareTo(Object o)
    {
        return  ((Notification)o).date.compareTo(date);
    }

    public String getDetails() {
        return details;
    }

    public String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate=dateFormat.format(date);
        return sDate;
    }

    public void setDate(Date date )
    {
        this.date=date;
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


