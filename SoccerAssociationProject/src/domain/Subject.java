package domain;

import java.util.List;

public abstract class Subject
{

    private List<User> followers;



    public boolean addFollwer(User user){return false;}
    public boolean removeFollwer(User user){return false;}
    public void notifyObservers(){}
}
