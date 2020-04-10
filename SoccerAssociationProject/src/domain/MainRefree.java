package domain;

public class MainRefree extends Refree
{
    public MainRefree(String name, String trainig, Account account) {
        super(name, trainig, account);
    }

    public boolean editGameEvent(){return false;}
    public boolean startGame(){return false;}
    public boolean cancelGame(){return false;}
    public boolean finishGame(){return false;}


}
