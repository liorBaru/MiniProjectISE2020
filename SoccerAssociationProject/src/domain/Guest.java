package domain;

public class Guest
{
    protected System system;

    public void login(String userName, String password) throws Exception
    {
       User user = system.login(userName,password);
       /// open user Mode
    }
    public void register(String name, String userName, String password) throws Exception
    {
        system.createNewFanUser(name,userName,password);
    }
    public void search (String key){}


}
