package domain.manageUsers;

import DB.System;
import domain.manageUsers.User;

public class Guest
{
    protected System system;

    public Guest ()
    {
        system=System.getInstance();
    }

    public User login(String userName, String password) throws Exception
    {
       User user = system.login(userName,password);
       return user;
       /// open user Mode
    }
    public void register(String name, String userName, String password) throws Exception
    {
        system.createNewFanUser(name,userName,password);
    }
}
