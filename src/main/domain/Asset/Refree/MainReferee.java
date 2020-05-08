package main.domain.Asset.Refree;

import main.domain.manageUsers.Account;

public class MainReferee extends Referee
{

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public MainReferee(String name, String training, Account account)
    {
        super(name,account ,training );
        kind="MainReferee";

    }



}
