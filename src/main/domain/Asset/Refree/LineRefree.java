package main.domain.Asset.Refree;

import main.DB.RefreesDaoSql;
import main.domain.manageUsers.Account;

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


    @Override
    protected void update() throws SQLException {
        String[]key={account.getUserName(),name,training,"Line"};
        refreesDaoSql.update(key);
    }
}
