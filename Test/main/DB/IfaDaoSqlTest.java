package main.DB;


import org.junit.Test;
import static org.junit.Assert.*;


public class IfaDaoSqlTest {

    IfaDaoSql ifaDaoSql =IfaDaoSql.getInstance();
    @Test
    public void get()
    {
        String[] key = new String[2];
        key[0]="IFATest";
        key[1]="ifaName";
        assertEquals(false,ifaDaoSql.get(key).isEmpty());
    }

    @Test
    public void save()
    {
        try {
            String[] key = new String[2];
            key[0]="IFATest";
            key[1]="ifaName";
            ifaDaoSql.delete(key);
            boolean flag=true;
            for (String [] raw:ifaDaoSql.getAll())
            {
                if(raw[0].equals("IFATest"))
                {
                    flag=false;
                }
            }
            assertEquals(true,flag);
            ifaDaoSql.save(key);
        }
        catch (Exception e)
        {

        }

    }

}
