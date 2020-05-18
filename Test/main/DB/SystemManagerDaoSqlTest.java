package main.DB;

import org.junit.Test;

import static org.junit.Assert.*;


public class SystemManagerDaoSqlTest
{

    SystemManagerDaoSql systemManagerDaoSql = SystemManagerDaoSql.getInstance();
    @Test
    public void get()
    {
        String [] key = new String[1];
        key[0]="SMTest";
        boolean flag =false;
        for (String[] raw :systemManagerDaoSql.get(key))
        {
            if(raw[0].equals("SMTest"))
            {
                flag=true;
            }
        }
        assertEquals(true,flag);
    }


    @Test
    public void save()
    {
        String [] key = new String[2];
        key[0]="SMTest";
        key[1]="smName";
        systemManagerDaoSql.delete(key);
        assertEquals(true,systemManagerDaoSql.get(key).isEmpty());
        try
        {
            systemManagerDaoSql.save(key);
            boolean flag =false;
            for (String[] raw:systemManagerDaoSql.getAll())
            {
                if(raw[0].equals("SMTest"))
                {
                    flag=true;
                }
            }
            assertEquals(true,flag);
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
        key[0]="SMTest";
        key[1]="SMNewName";
        systemManagerDaoSql.update(key);
        for (String [] raw:systemManagerDaoSql.get(key))
        {
            assertEquals("SMNewName",raw[1]);
        }
    }
}
