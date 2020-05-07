package main.service;

import main.domain.manageUsers.Guest;
import main.domain.manageUsers.User;

import java.util.Observable;
import java.util.Observer;

public class GuestController extends Observable implements Observer
{
    Guest guest;

    public GuestController(Guest guest) {
        this.guest=guest;
    }

    public boolean register(String name, String userName, String password) throws Exception {
        if(name!=null && userName!=null && password!=null)
        {
            if(userName.length()>6 && password.length()>8)
            {
                if(guest.register(name,userName,password)!=null)
                    return true;
            }
            return false;
        }
        return false;
    }

    public String login( String userName, String password) throws Exception {
        if( userName!=null && password!=null)
        {
            User userType= guest.login(userName,password);
            if(userType!=null){
                return userType.getKind();
            }

        }
        return "invalid";
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
