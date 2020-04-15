package domain;

import java.util.Date;

public class LineRefree extends Refree
{

    public LineRefree(String name, String training, Account account)
    {

        super(name,training,account);
    }
    public LineRefree(String name, Account account, Date birthDay) {

        super(name, account, birthDay);
    }

}//class
