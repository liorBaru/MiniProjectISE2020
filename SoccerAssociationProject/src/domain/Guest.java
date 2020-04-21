package domain;

public class Guest
{
    protected System system;

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
    public void search (String key){}


}
