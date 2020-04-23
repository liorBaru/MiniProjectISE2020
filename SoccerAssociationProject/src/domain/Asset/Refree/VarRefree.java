package domain.Asset.Refree;

import domain.manageUsers.Account;

public class VarRefree extends Refree
{
    public VarRefree (String name, String training, Account account)
    {
        super(name,training,account);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public VarRefree (String name, Account account)
    {
        super(name,account);
    }
}
