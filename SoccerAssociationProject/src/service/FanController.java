package service;

import domain.Fan;

public class FanController
{
    private Fan userFan;
    public void FanController(Fan fan)
    {
        userFan=fan;
    }

    public void readNotification()
    {
       userFan.readNotification();
    }

    public void sendComplaint(String details)
    {
        if(details.isEmpty()==false)
        {
            userFan.fillingComplaint(details);
        }
    }


}
