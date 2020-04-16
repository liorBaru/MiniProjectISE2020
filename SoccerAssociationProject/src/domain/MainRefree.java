package domain;

import java.util.Date;

public class MainRefree extends Refree
{

    public MainRefree(String name, String training, Account account) {

        super(name, training,account );
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public MainRefree(String name, Account account) {
        super(name,account );
    }

    public boolean editGameEvent(){return false;}
    public boolean startGame(){return false;}
    public boolean cancelGame(){return false;}
    public boolean finishGame(){return false;}


}
