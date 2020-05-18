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
        boolean flag=false;
        for (String [] checks:usersDaoSql.get(details))
        {
            if(checks[0]=="galborabia")
            {
                flag=true;
            }
        }
        assertEquals(true,flag);
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
        details[1]="galNewPass1";
        usersDaoSql.update(details);
    }
}
