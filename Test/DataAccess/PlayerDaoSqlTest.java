package DataAccess;

import org.junit.Test;
import org.junit.experimental.categories.Category;


import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class PlayerDaoSqlTest {

    private PlayerDaoSql playerDaoSql =PlayerDaoSql.getInstance();
    @Test
    @Category(UnitTests.class)
    public void get()
    {
        String[] key={"Key","playerTest"};
        String player=playerDaoSql.get(key).get(0)[0];
        if (player.equals("playerTest"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }

    @Test
    @Category(UnitTests.class)
    public void saveForeginKey()
    {
        String message="";
        try
        {
            Date date=new Date();
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String sDate=simpleDateFormat.format(date);
            String[] key={"player","teamTest","name","1",sDate,"","0","0","0","0","0"};
            playerDaoSql.save(key);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("object not found",message);
    }

    @Test
    @Category(UnitTests.class)
    public void update()
    {
        try {
            Date date=new Date();
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String sDate=simpleDateFormat.format(date);
            String[] key2={"playerTest","teamTest","name","1",sDate,"","0","0","0",""};
            playerDaoSql.update(key2);
            String[] key3={"Key","playerTest","teamTest","name","1",sDate,"","0","0","0",""};
            String check=playerDaoSql.get(key3).get(0)[4];
            assertEquals(check,sDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    @Category(UnitTests.class)
    public void delete()
    {
        try
        {
            String[] key={"playerTest"};
            playerDaoSql.delete(key);
            Date date=new Date();
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String sDate=simpleDateFormat.format(date);
            String[] key2={"playerTest","teamTest","name","1",sDate,"","0","0","0","0"};
            playerDaoSql.save(key2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
