package DataAccess;


import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class coachDaoSqlTest
{
    CoachDaoSql coachDaoSql = CoachDaoSql.getInstance();

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void save()
    {
        try {
            String [] key1={"coachTest"};
            coachDaoSql.delete(key1);
            String [] key2={"Key","coachTest"};
            assertTrue(coachDaoSql.get(key2).isEmpty());
            String [] key3={"coachTest","teamTest","0","training","job","name"};
            coachDaoSql.save(key3);
            assertFalse(coachDaoSql.get(key2).isEmpty());
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
            String [] key2={"Key","coachTest"};
            String [] key3={"coachTest","","0","training","job","name"};
            coachDaoSql.update(key3);
            assertTrue(coachDaoSql.get(key2).get(0)[1].isEmpty());
            String [] key4={"coachTest","teamTest","0","training","job","name"};
            coachDaoSql.update(key4);
        }
        catch (Exception e)
        {

        }

    }
}