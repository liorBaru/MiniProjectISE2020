package domain.Asset.Refree;

import domain.manageUsers.Account;

import java.sql.SQLException;

public class LineRefree extends Refree
{


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public LineRefree(String name, String training, Account account) throws SQLException {
        super(name,account,training,"Line");
    }
    public LineRefree(Account account,String [] params)
    {
        super(account,params);
        kind="Line";
    }



    @Override
    protected void update() throws SQLException {
        String[]key={account.getUserName(),name,training,"Line"};
        refreesDaoSql.update(key);
    }

    @Override
    public String getKind()
    {
        return "Line";
    }
}
