package domain.Asset.Refree;

import domain.manageLeagues.Game;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import domain.manageEvents.Notification;
import domain.manageLeagues.IFA;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Refree extends User
{
    private List<Game> games;
    private String trainig;


    public Refree(String name, String training, Account account)
    {
        super(name,account);
        games = new LinkedList<>();
        this.trainig=training;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Refree(String name, Account account)
    {
        super(name,account);
        games = new LinkedList<>();
    }

    @Override
    public void removeUser()
    {
        String details = "Refree, "+this.name+", has been remove from the system by the system manager";
        Date date = new Date();
        Notification notification = new Notification(details,date);
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
