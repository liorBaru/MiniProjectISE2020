package domain.Asset.Refree;

import domain.manageUsers.Account;

import java.sql.SQLException;

public class VarRefree extends Refree
{
    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */

    public VarRefree (String name, String training, Account account) throws SQLException {
        super(name,account,training,"Var");
    }

    public VarRefree(Account account,String [] params)
    {
        super(account,params);
        kind="Var";
    }

    @Override
    protected void update() throws SQLException {
        String[]key={account.getUserName(),name,training,"Var"};
        refreesDaoSql.update(key);
    }
    @Override
    public String getKind()
    {
        return "Var";
    }
}
