package domain.Asset.Refree;

import domain.Asset.TeamMember;
import domain.manageEvents.GameEventLog;
import domain.manageLeagues.Game;
import domain.manageUsers.Account;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

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

    public MainRefree(Account account,String [] params)
    {
        super(account,params);
        kind="Main";
    }



    @Override
    public String getKind()
    {
        return "Main";
    }
}
