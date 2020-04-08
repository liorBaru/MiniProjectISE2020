package domain;

import java.util.List;

public abstract class Refree extends User
{
    private List<Game> games;
    private String trainig;
    private Game activeGame;


    public Refree(String name, String trainig, Account account)
    {
        super(name,account);
        this.trainig=trainig;
    }
    public List<Game> showGames(){return null;}
    public boolean addEventToGame(){return false;}





}
