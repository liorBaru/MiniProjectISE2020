package domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Refree extends User
{
    private List<Game> games;
    private String trainig;
    private Game activeGame;
    Date birthDay;

    public Refree(String name, String training, Account account)
    {
        super(name,account);
        games = new LinkedList<>();
        this.trainig=training;
    }

    public Refree(String rName, Account rAccount, Date birthDay)
    {
        super(rName,rAccount);
        games = new LinkedList<>();
        this.birthDay = birthDay;
    }

    @Override
    public void removeUser()
    {

    }

    public List<Game> showGames(){return null;}
    public boolean addEventToGame(){return false;}





}
