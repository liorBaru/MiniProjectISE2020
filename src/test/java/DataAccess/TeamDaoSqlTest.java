package DataAccess;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;


public class TeamDaoSqlTest {


    TeamDaoSql teamDaoSql =new TeamDaoSql();

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void save()
    {
        try
        {
            String [] key={"teamTest"};
            String id=teamDaoSql.get(key).get(0)[1];
            teamDaoSql.delete(key);
            assertTrue(teamDaoSql.get(key).isEmpty());
            String [] key2={"teamTest",id,"true"};
            teamDaoSql.save(key2);
            assertFalse(teamDaoSql.get(key).isEmpty());
            //PagesDaoSql pagesDaoSql=PagesDaoSql.getInstance();
            //String[] page={id,"teamTest","name"};
           // pagesDaoSql.save(page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    @Category(UnitTests.class)
    public void update()
    {
        try
        {
            String [] key={"teamTest"};
            String [] parms=teamDaoSql.get(key).get(0);
            String [] key2={parms[0],parms[1],"false"};
            teamDaoSql.update(key2);
            parms=teamDaoSql.get(key).get(0);
            assertFalse(Boolean.valueOf(parms[2]));
            String [] key3={parms[0],parms[1],"true"};
            teamDaoSql.update(key3);
        }
        catch (Exception e)
        {

        }

    }

}
