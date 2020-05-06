package main.domain.Asset.Refree;

import main.domain.manageLeagues.Game;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;

import java.util.LinkedList;
import java.util.List;

public abstract class Refree extends User
{
    private List<Game> games;
    private String trainig;


    public Refree(String name, Account account,String trainig)
    {
        super(name,account);
        this.trainig=trainig;
        games = new LinkedList<>();
    }


    @Override
    public void removeUser()
    {
        String details = "Refree, "+this.name+", has been remove from the system by the system manager";
        Notification notification = new Notification(details);
        for (IFA ifa:system.getIfaList())
        {
            ifa.addNotification(notification);
        }
    }

    public List<Game> showGames()
    {
        return this.games;
    }







}
