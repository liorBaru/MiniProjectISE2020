package DataAccess;


import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class OwnersDaoSqlTest {

    private OwnersDaoSql ownersDaoSql=OwnersDaoSql.getInstance();

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void get()
    {
        try
        {
            String[] key={"ownerTest"};
            ownersDaoSql.delete(key);
            String[] key2={"Key","ownerTest"};
            assertTrue(ownersDaoSql.get(key2).isEmpty());
            String[] params={"ownerTest","owner","teamTest","Coach"};
            ownersDaoSql.save(params);
            assertEquals("Coach",ownersDaoSql.get(key2).get(0)[3]);
            String[] params1={"ownerTest","owner","teamTest",""};
            ownersDaoSql.update(params1);
            assertTrue(ownersDaoSql.get(key2).get(0)[3].isEmpty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    @Category(UnitTests.class)
    public void saveDuplicateKey()
    {
        String message="";
        try
        {
            String[] params={"ownerTest","owner","teamTest","coach"};
            ownersDaoSql.save(params);
            ownersDaoSql.save(params);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("wrong parameters",message);
    }

    @Test
    @Category(UnitTests.class)
    public void saveForigenKey()
    {

        String message="";
        try
        {
            String[] params={"owner","owner","team","coach"};
            ownersDaoSql.save(params);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("object not found",message);
    }
}
