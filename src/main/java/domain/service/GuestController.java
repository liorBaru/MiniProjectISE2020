package domain.service;

import domain.manageUsers.Guest;
import domain.manageUsers.User;

import java.util.List;
import java.util.Observable;

public class GuestController extends Observable
{
    private Guest guest;

    public GuestController()
    {
        this.guest= new Guest();
    }

    public String[] register (String name, String userName, String password)
    {
        String [] message=null;
        try
        {
            if(name!=null && userName!=null && password!=null)
            {
                if(userName.length()>=6 && password.length()>=8)
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
            else
            {
                throw new Exception("Invalid passwords length or userName length");
            }
        }
        catch (Exception e)
        {
            message = new String[2];
            message[0]="Fail";
            message[1]=e.getMessage();
        }
        return message;
    }

    public String[] login(String userName, String password, GuestApplication application)
    {
        String [] message=null;
        try
        {
            if( userName!=null && password!=null)
            {
                User user= guest.login(userName,password);
                GuestController controller=null;
                if(user.getKind().equals("IFA"))
                {
                   controller = new IFAController(user);
                }
                else if(user.getKind().equals("Main")||user.getKind().equals("Line")||user.getKind().equals("Var"))
                {
                    controller=new RefreeController(user);
                }
                else if(user.getKind().equals("Fan"))
                {
                    controller = new FanController(user);
                }
                else if(user.getKind().equals("SystemManager"))
                {
                    controller = new SystemManagerController(user);
                }
                else if(user.getKind().equals("Player")||user.getKind().equals("Coach"))
                {
                    controller= new TeamMemberController(user);
                }
                else if(user.getKind().equals("Owner") || user.getKind().equals("TeamManager") )
                {
                    controller=new BoardManagerController(user);
                }
                if(controller!=null)
                {
                    controller.addObserver(application);
                    Object [] objects ={user.getAccount().getUserName(),controller};
                    controller.setChanged();
                    controller.notifyObservers(objects);
                }
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
             message = new String[2];
             message[0]="Fail";
             message[1]=e.getMessage();
        }
        return message;
    }

}
