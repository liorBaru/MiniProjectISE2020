package main.DB;


import DataAccess.CoachDaoSql;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class coachDaoSqlTest
{
    CoachDaoSql coachDaoSql = CoachDaoSql.getInstance();


    @Test
    public void getAll()
    {

    }

    @Test
    public void save() throws SQLException {
        String [] details = new String[5];
        details[0]="galborabia";
        details[1]="Hapoel Tel aviv";
        details[2]="454";
        details[3]="Coach";
        details[4]="Goal keeper coach";
        try
        {
            coachDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String []> checks=coachDaoSql.get(details);
        assertEquals("galborabia",checks.get(0)[0]);
        coachDaoSql.delete(details);
    }

    @Test
    public void update() throws SQLException {
        String [] details = new String[4];
        details[0]="galborabia";
        details[1]="galb1234";
        details[2]="125632597";
        details[3]="Fan";
        try {
            coachDaoSql.save(details);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        details[1]="galNewPass1";
        coachDaoSql.update(details);
    }
}
