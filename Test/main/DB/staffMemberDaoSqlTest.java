package main.DB;


import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class staffMemberDaoSqlTest
{
    StaffMembersDaoSql staffDaoSql = StaffMembersDaoSql.getInstance();


    @Test
    public void getAll()
    {

    }

    @Test
    public void save()
    {
        String [] details = new String[2];
        details[0]="galborabia";
        details[1]="coach";
        try
        {
            staffDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String []> checks= staffDaoSql.get(details);
        assertEquals("galborabia",checks.get(0));
        staffDaoSql.delete(details);
    }

    @Test
    public void update()
    {
        String [] details = new String[1];
        details[0]="galborabia";
        try {
            staffDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        details[1]="Owner";
        staffDaoSql.update(details);
    }
}
