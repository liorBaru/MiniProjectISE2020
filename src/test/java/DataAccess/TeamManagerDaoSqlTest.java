package DataAccess;

import org.junit.Test;
import static org.junit.Assert.*;


public class TeamManagerDaoSqlTest {

    TeamManagerDaoSql teamManagerDaoSql=TeamManagerDaoSql.getInstance();

    @Test
    public void get()
    {
        String[] key={"Key","managerTest"};
        String teamManager=teamManagerDaoSql.get(key).get(0)[0];
        if (teamManager.equals("managerTest"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }

    @Test
    public void save()
    {
        try
        {
            String [] key2={"Key","ownerTest"};
            String[] key={"ownerTest","teamTest","owner"};
            teamManagerDaoSql.save(key);
            assertTrue(teamManagerDaoSql.get(key2).get(0)[0].equals("ownerTest"));
            teamManagerDaoSql.delete(key);
            assertTrue(teamManagerDaoSql.get(key2).isEmpty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void update()
    {
        try {
            String[] key={"managerTest","teamTest","gedalia"};
            teamManagerDaoSql.update(key);
            String[] key2={"Key","managerTest"};
            assertEquals("gedalia",teamManagerDaoSql.get(key2).get(0)[2]);
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void saveFailure()
    {
        String message="";
        String[] key={"ownerTest","teamTest","owner"};
        try
        {
            teamManagerDaoSql.save(key);
            teamManagerDaoSql.save(key);
        }
        catch (Exception e)
        {
           message=e.getMessage();
        }
        try
        {
            teamManagerDaoSql.delete(key);
            assertEquals("wrong parameters",message);
        }
        catch (Exception e)
        {
        }

    }
}
