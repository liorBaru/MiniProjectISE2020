package domain.Asset.Refree;

import domain.manageUsers.Account;

public class MainRefree extends Refree
{

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public MainRefree(String name, Account account) {
        super(name,account );
    }


    public MainRefree(String name, String training, Account account)
    {
        super(name, training,account );
    }

    public boolean editGameEvent(){return false;}
    public boolean startGame(){return false;}
    public boolean cancelGame(){return false;}
    public boolean finishGame(){return false;}


}
