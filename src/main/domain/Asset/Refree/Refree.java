package main.domain.Asset.Refree;

import main.DB.GamesDaoSql;
import main.DB.IfaDaoSql;
import main.DB.RefreesDaoSql;
import main.domain.manageLeagues.Game;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class Refree extends User
{
    protected List<Game> games;
    protected String training;

    protected static RefreesDaoSql refreesDaoSql;
    protected static IfaDaoSql ifaDaoSql;
    protected static GamesDaoSql gamesDaoSql;

    public Refree(String name, Account account,String trainig,String type) throws SQLException {
        super(name,account);
        this.training=trainig;
        String [] key ={account.getUserName(),name,trainig,type};
        refreesDaoSql.save(key);
    }

    public static User createRefreeFromDB(String [] key)
    {
        return null;
    }

    public static User getRefreeFromDB(String [] params)
    {
        return createRefreeFromDB(params);
    }

    @Override
    public boolean removeUser() throws Exception {
        String details = "Refree, "+this.name+", has been remove from the system by the system manager";
        Notification notification = new Notification(details);
        for (String[] ifa:ifaDaoSql.getAll())
        {
            try
            {
                system.sendNotification(ifa[0],notification);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

}
