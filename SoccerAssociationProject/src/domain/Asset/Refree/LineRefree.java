package domain.Asset.Refree;

import domain.manageUsers.Account;

public class LineRefree extends Refree
{

    public LineRefree(String name, String training, Account account)
    {
        super(name,training,account);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public LineRefree(String name, Account account)
    {
        super(name,account);
    }
}
