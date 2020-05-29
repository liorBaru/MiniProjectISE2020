package DataAccess;


import DataAccess.UsersDaoSql;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UsersDaoSqlTest
{
    UsersDaoSql usersDaoSql=UsersDaoSql.getInstance();


    @Test
    public void getAll()
    {

    }

    @Test
    public void save() throws SQLException {
        String [] details = new String[4];
        details[0]="galborabia";
        details[1]="galb1234";
        details[2]="125632597";
        details[3]="Fan";
        try
        {
            usersDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String []> checks=usersDaoSql.get(details);
        assertEquals("galborabia",checks.get(0));
        usersDaoSql.delete(details);
    }

    @Test
    public void update() throws SQLException {
        String [] details = new String[4];
        details[0]="galborabia";
        details[1]="galb1234";
        details[2]="125632597";
        details[3]="Fan";
        try {
            usersDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        details[1]="galNewPass1";
        usersDaoSql.update(details);
    }
}
