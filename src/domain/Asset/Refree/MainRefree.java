package domain.Asset.Refree;

import domain.manageUsers.Account;

import java.sql.SQLException;

public class MainRefree extends Refree
{

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public MainRefree(String name, String training, Account account) throws SQLException {
        super(name,account ,training,"Main");
    }

    @Override
    protected void update() throws SQLException {
        String[]key={account.getUserName(),name,training,"Main"};
        refreesDaoSql.update(key);
    }
    @Override
    public String getKind()
    {
        return "Main";
    }
}
