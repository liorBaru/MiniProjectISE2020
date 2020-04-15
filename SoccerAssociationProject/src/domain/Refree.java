package domain;

import java.util.LinkedList;
import java.util.List;

public abstract class Refree extends User
{
    private List<Game> games;
    private String trainig;
    private Game activeGame;


    public Refree(String name, String training, Account account)
    {
        super(name,account);
        games = new LinkedList<>();
        this.trainig=training;
    }

    @Override
    public void removeUser()
    {

    }

    public List<Game> showGames(){return null;}
    public boolean addEventToGame(){return false;}





}
