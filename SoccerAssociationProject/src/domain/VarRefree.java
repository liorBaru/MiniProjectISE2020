package domain;

import java.util.Date;

public class VarRefree extends Refree
{
    public VarRefree (String name, String training, Account account)
    {

        super(name,training,account);
    }
    public VarRefree(String name, Account account, Date birthDay) {

        super(name, account, birthDay);
    }
}
