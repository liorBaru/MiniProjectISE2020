package domain.service;

import domain.Asset.Refree.MainRefree;
import domain.Asset.Refree.Refree;
import domain.manageLeagues.Game;
import domain.manageUsers.User;

import java.util.Date;
import java.util.TreeMap;

public class RefreeController extends GuestController
{

    private Refree refree;

    public RefreeController(User user)
    {
            this.refree= (Refree) user;

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


    public String[] addEvent(String teamMemberUserName, int gameID, String event, int minute, Date date)
    {
        String [] message=new String[2];
        try
        {
            if(teamMemberUserName.isEmpty()==false&& event.isEmpty()==false && minute>0 && minute<120)
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
