package main.domain.Asset.Refree;

import main.DB.RefreesDaoSql;
import main.domain.manageUsers.Account;

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
    protected void update()
    {
        String[]key={account.getUserName(),name,training,"Main"};
        refreesDaoSql.update(key);
    }
}
