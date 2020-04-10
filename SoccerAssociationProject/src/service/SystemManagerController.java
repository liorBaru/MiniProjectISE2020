package service;
import domain.System;
public class SystemManagerController
{
    public void initSystem(String userName, String password, String name)
    {
        System.initSystem(userName,password,name);
    }
}
