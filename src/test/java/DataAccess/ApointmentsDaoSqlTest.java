package DataAccess;


import Logger.Lo4jDemo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ApointmentsDaoSqlTest {

    ApointmentsDaoSql apointmentsDaoSql=ApointmentsDaoSql.getInstance();
    @Test
    public void getByManager()
    {
        try
        {
            String [] key1 ={"ownerTest","coachTest"};
            String [] key2 ={"ownerTest","managerTest"};
            Lo4jDemo.getInstance().error("Hello world ");
            apointmentsDaoSql.save(key1);
            apointmentsDaoSql.save(key2);
            String [] params={"Manager","ownerTest"};
            List<String[]> ans= apointmentsDaoSql.get(params);
            boolean flag1=false;
            boolean flag2=false;
            for (String[] raw:ans)
            {
                if(raw[0].equals("coachTest"))
                {
                    flag1=true;
                }
                else if(raw[0].equals("managerTest"))
                {
                    flag2=true;
                }
            }
            assertTrue(flag1);
            assertTrue(flag2);
            apointmentsDaoSql.delete(params);
            assertTrue(apointmentsDaoSql.get(params).isEmpty());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Test
    public void getByEmployee()
    {
        try
        {
            String [] key1 ={"ownerTest","coachTest"};
            apointmentsDaoSql.save(key1);
            String [] params={"Employee","coachTest"};
            List<String[]> ans= apointmentsDaoSql.get(params);
            boolean flag1=false;
            for (String[] raw:ans)
            {
                if(raw[0].equals("ownerTest"))
                {
                    flag1=true;
                }
            }
            assertTrue(flag1);
            apointmentsDaoSql.delete(params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Test
    public void saveFailureOwner()
    {
        String message="";
        try
        {
            String [] key1 ={"wrongOwner","coachTest"};
            apointmentsDaoSql.save(key1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("wrong parameters",message);
    }

    @Test
    public void saveFailureEmployee()
    {
        String message="";
        try
        {
            String [] key1 ={"OwnerTest","coach"};
            apointmentsDaoSql.save(key1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("object not found",message);
    }

    @Test
    public void saveFailureDuplicateKey()
    {
        String message="";
        try
        {
            String [] key1 ={"OwnerTest","coachTest"};
            apointmentsDaoSql.save(key1);
            apointmentsDaoSql.save(key1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("wrong parameters",message);
    }

}
