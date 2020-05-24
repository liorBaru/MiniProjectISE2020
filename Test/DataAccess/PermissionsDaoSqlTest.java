package DataAccess;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
public class PermissionsDaoSqlTest {

    PermissionsDaoSql permissionsDaoSql =PermissionsDaoSql.getInstance();
    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void get()
    {
        try {
            String[] key={"managerTest","addPlayer"};
            String[] key2={"Key","managerTest","addPlayer"};
            permissionsDaoSql.save(key);
            assertTrue(permissionsDaoSql.get(key2).get(0)[0].equals("managerTest"));
            permissionsDaoSql.delete(key2);
            assertTrue(permissionsDaoSql.get(key2).isEmpty());
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
        try {
            String[] key={"managerTest","addPlayer"};
            permissionsDaoSql.save(key);
            permissionsDaoSql.save(key);
            assertEquals("wrong parameters",message);
            String[] key2={"Key","managerTest","addPlayer"};
            permissionsDaoSql.delete(key2);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }

    }

    @Test
    @Category(UnitTests.class)
    public void saveEmptyTeamManager()
    {
        String message="";
        try {
            String[] key={"manager","Player"};

            permissionsDaoSql.save(key);
            permissionsDaoSql.save(key);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("object not found",message);
    }
}
