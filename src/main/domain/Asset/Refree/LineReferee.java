package main.domain.Asset.Refree;

import main.domain.manageUsers.Account;

public class LineReferee extends Referee
{

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public LineReferee(String name, Account account,  String training)
    {
        super(name,account,training);
        kind="LineReferee";
    }



}
