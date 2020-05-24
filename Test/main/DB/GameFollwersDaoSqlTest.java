package main.DB;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameFollwersDaoSqlTest
{

    GameFollwersDaoSql gameFollwersDaoSql =GameFollwersDaoSql.getInstance();
    @Test
    public void get()
    {
        String [] key = new String[3];
        key[0]="Key";
        key[1]="0";
        key[2]="fanTest";
        assertEquals(true,gameFollwersDaoSql.get(key).isEmpty());
    }

    @Test
    public void save()
    {

        String [] key = new String[3];
        key[0]="key";
        key[1]="0";
        key[2]="fanTest";
        try {
            gameFollwersDaoSql.delete(key);
            assertEquals(true,gameFollwersDaoSql.get(key).isEmpty());
            key[0]="0";
            key[1]="fanTest";
            gameFollwersDaoSql.save(key);
            key[0]="key";
            key[1]="0";
            key[2]="fanTest";
            assertEquals(false,gameFollwersDaoSql.get(key).isEmpty());
            gameFollwersDaoSql.delete(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
