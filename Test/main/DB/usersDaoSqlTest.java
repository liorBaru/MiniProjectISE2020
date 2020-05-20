package main.DB;


import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class usersDaoSqlTest
{
    UsersDaoSql usersDaoSql=UsersDaoSql.getInstance();


    @Test
    public void getAll()
    {
        assertTrue(usersDaoSql.getAll().size()>5);
    }

    @Test
    public void save()
    {
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
        assertEquals("galborabia",checks.get(0)[0]);
        usersDaoSql.delete(details);
    }

    @Test
    public void update()
    {
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
        details[3]="Player";
        usersDaoSql.update(details);
        assertEquals("Player",usersDaoSql.get(details).get(0)[3]);
        usersDaoSql.delete(details);
    }
}
