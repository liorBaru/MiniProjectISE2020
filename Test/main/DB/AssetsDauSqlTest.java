package main.DB;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;


public class AssetsDauSqlTest {

    private AssetsDauSql assetsDauSql=AssetsDauSql.getInstance();
    @Test
    @Category({UnitTests.class,RegressionTests.class})
    public void getAll()
    {
        try
        {
            String [] key1={"teamTest","field1","Field"};
            String [] key2={"teamTest","field2","Field"};
            assetsDauSql.save(key1);
            assetsDauSql.save(key2);
            assertTrue(assetsDauSql.getAll().size()>=2);
            assetsDauSql.delete(key1);
            assetsDauSql.delete(key2);
            assertFalse(assetsDauSql.getAll().contains(key1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Test
    @Category({UnitTests.class})
    public void saveFailureInvalidTeam()
    {
        String [] key1={"team","field1","Field"};
        String message="";
        try
        {
            assetsDauSql.save(key1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
            e.printStackTrace();
        }
        assertEquals("Invalid team",message);
    }

    @Test
    @Category({UnitTests.class})
    public void saveFailureDuplikateKey()
    {
        String message="";
        String [] key1={"teamTest","field1","Field"};
        String [] key2={"teamTest","field1","Field"};
        try
        {
            assetsDauSql.save(key1);
            assetsDauSql.save(key2);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("team already as asset by this name",message);
        assetsDauSql.delete(key1);
    }
}
