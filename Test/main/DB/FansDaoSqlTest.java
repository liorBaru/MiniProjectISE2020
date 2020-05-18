package main.DB;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class FansDaoSqlTest {

    FansDaoSql fansDaoSql = FansDaoSql.getInstance();
    @Test
    public void get()
    {
        String [] key = new String[2];
        key[0]="fanTest";
        key[1]="fanName";
        assertEquals(1,fansDaoSql.get(key).size());
    }


    @Test
    public void save()
    {
        String [] key = new String[2];
        key[0]="fanTest";
        key[1]="fanName";
        fansDaoSql.delete(key);
        assertEquals(true,fansDaoSql.get(key).isEmpty());
        try
        {
            fansDaoSql.save(key);
            boolean flag=false;
            for (String[] raw:fansDaoSql.getAll())
            {
                if(raw[0].equals("fanTest"))
                {
                    flag=true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void update()
    {

        String [] key = new String[2];
        key[0]="fanTest";
        key[1]="fanNewName";
        fansDaoSql.update(key);
        for (String[] raw:fansDaoSql.get(key))
        {
            assertEquals("fanNewName",raw[1]);
        }
    }
}
