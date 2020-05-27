package domain.service;

import domain.Asset.Refree.MainRefree;
import domain.Asset.Refree.Refree;

import java.util.Date;
import java.util.TreeMap;

public class RefreeController
{

    private Refree refree;

    public RefreeController(Refree refree)
    {
        this.refree=refree;
    }

    public String[] createReport(int gameId)
    {
        String[] message=new String[2];
        try
        {
            refree.creareReport(gameId);
            message[0]="Respond";
            message[1]="Success operation";
        }
        catch (Exception e)
        {
            message[0]="Fail";
            message[1]=e.getMessage();
        }
        return message;
    }


    public String[] addEvent(String teamMemberUserName, int gameID, String event, int minute,Date date)
    {
        String [] message=new String[2];
        try
        {
            if(teamMemberUserName.isEmpty()|| event.isEmpty() || minute<0)
            {
                refree.addEvent(teamMemberUserName,gameID,event,minute,date);
                message[0]="Respond";
                message[1]="Success operation";
            }
            else
            {
                throw new Exception("Invalid arguments");
            }
        }
        catch (Exception e)
        {
            message[0]="Fail";
            message[1]=e.getMessage();
        }
       return message;
    }




}
