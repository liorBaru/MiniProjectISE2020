package service;

import domain.Guest;

public class GuestController
{
    Guest guest;

    public void register(String name, String userName, String password) throws Exception {
        if(name!=null && userName!=null && password!=null)
        {
            if(userName.length()>6 && password.length()>8)
            {
                guest.register(name,userName,password);
            }
        }
    }
}
