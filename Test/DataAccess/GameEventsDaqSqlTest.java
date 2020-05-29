package DataAccess;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class GameEventsDaqSqlTest{

    GameEventsDaoSql gameEventsDaoSql = GameEventsDaoSql.getInstance();
    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void saveGetDelete()
    {
        try {
            Date date=new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            String[] key={"1","Maor Buzaglo","Yellow card","56",String.valueOf(ft.format(date))};
            gameEventsDaoSql.save(key);
            assertTrue(gameEventsDaoSql.get(key).get(0)[2].equals("Yellow card"));
            gameEventsDaoSql.delete(key);
            assertTrue(gameEventsDaoSql.get(key).isEmpty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
