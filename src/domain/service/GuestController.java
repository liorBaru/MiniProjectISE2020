package domain.service;

import domain.manageUsers.Guest;
import domain.manageUsers.User;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GuestController extends Observable
{
    private Guest guest;

    public GuestController(Guest guest) {
        this.guest=guest;
    }

    public String[] register (String name, String userName, String password)
    {
        String [] message=null;
        try
        {
            if(name!=null && userName!=null && password!=null)
            {
                if(userName.length()>6 && password.length()>8)
                {
                    if(guest.register(name,userName,password))
                    {
                        message = new String[2];
                        message[0]="Respond";
                        message[1]="Registration was successful.";
                        return message;
                    }
                }
            }
        }
        catch (Exception e)
        {
            message = new String[2];
            message[1]="Fail";
            message[2]=e.getMessage();
        }
        return message;
    }

    public String[] login( String userName, String password)
    {
        String [] message=null;
        try
        {
            if( userName!=null && password!=null)
            {
                User user= guest.login(userName,password);
               List<String> temp =user.showPersonalDetails();
                message = new String[temp.size()+1];
                message[0]="Respond";
                for(int i=0;i<temp.size();i++)
                {
                    message[i+1]=temp.get(i);
                }
            }
            return message;
        }
        catch (Exception e)
        {
             message = new String[3];

        }
        return message;
    }

}
