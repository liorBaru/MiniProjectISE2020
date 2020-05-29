package DataAccess;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class GamesDaoSqlTest{

    GamesDaoSql gamesDaoSql = GamesDaoSql.getInstance();
    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void saveGetDelete()
    {
        try {
            Date date=new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            String[] key={"1","Hapel Ber Sheva","Maccabi Tel Aviv","Teddy",String.valueOf(ft.format(date)),"3-0","Ligat Ha Al","Hacmon","Yefet","Keinan","Zaltsman","true","false"};
            gamesDaoSql.save(key);
            String[] key2={"Key","1"};
            assertTrue(gamesDaoSql.get(key2).get(0)[0].equals("1"));
            gamesDaoSql.delete(key);
            assertTrue(gamesDaoSql.get(key2).isEmpty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
