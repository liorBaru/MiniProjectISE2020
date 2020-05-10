package main.domain.manageUsers;

import main.DB.System;

import java.util.Observable;
import java.util.Random;

public class Guest extends Observable
{
    protected System system;

    public Guest ()
    {
        system=System.getInstance();
    }

    public User login(String userName, String password) throws Exception
    {
        if(userName!=null && password!=null)
        {
            User user = system.login(userName, password);
            return user;
        }
        throw new Exception("Bad details");

    }



    public User register(String name, String userName, String password) throws Exception
    {
        if(name!=null && userName!=null && password!=null) {
            return system.createNewFanUser(name, userName, password);
        }
        throw new Exception("Bad details");
    }



}
