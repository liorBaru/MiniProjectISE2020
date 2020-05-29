package DataAccess;


import DataAccess.TeamMemberDaoSql;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class teammemberDaoSqlTest
{
    TeamMemberDaoSql teamMemberDaoSql = TeamMemberDaoSql.getInstance();


    @Test
    public void getAll()
    {

    }

    @Test
    public void save()
    {
        String [] details = new String[1];
        details[0]="galborabia";
        try
        {
            teamMemberDaoSql.save(details);
            List<String []> checks= teamMemberDaoSql.get(details);
            assertEquals("galborabia",checks.get(0)[0]);
            teamMemberDaoSql.delete(details);
        }
        catch (SQLException e) {

        }

    }

    @Test
    public void update()
    {
        String [] details = new String[1];
        details[0]="galborabia";
        try {
            teamMemberDaoSql.save(details);
            details[1]="galNewPass1";
            teamMemberDaoSql.update(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}