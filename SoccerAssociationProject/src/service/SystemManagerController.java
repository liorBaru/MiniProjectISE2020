package service;
import domain.System;
import domain.SystemManager;

public class SystemManagerController
{
    SystemManager systemManager;
    public boolean initSystem(String userName, String password, String name) throws Exception {
        if(userName!=null && password!=null && name!=null) {
            if (userName.length() >= 6 && password.length() >= 8) {
                System.initSystem(userName, password, name);
            }
        }
        return false;
    }

    public boolean removeUser(String userName) throws Exception
    {
        return systemManager.removeUserFromSystem(userName);

    }




}
