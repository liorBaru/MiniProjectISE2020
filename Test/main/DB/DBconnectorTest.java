package main.DB;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DBconnectorTest
{
    DBconnector dBconnector;
   @Test
    public void getInstance()
   {
       dBconnector =DBconnector.getInstance();
       boolean flag=false;
       if(dBconnector!=null)
       {
           flag=true;
       }
       assertEquals(true,flag);
   }

    @org.junit.Test
    public void getConnection()
    {
        Connection conn =dBconnector.getConnection();
        if(conn!=null)
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }

    }
}
