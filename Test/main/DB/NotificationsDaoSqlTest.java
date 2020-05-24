package main.DB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class NotificationsDaoSqlTest {

    NotificationsDaoSql notificationsDaoSql;
    @Before
    public void setUp() throws Exception
    {
        notificationsDaoSql=NotificationsDaoSql.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get()
    {
        String [] details = new String[3];
        details[0]="fanTest";
        details[1]="new notification";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date =new Date();;
        details[2] = format.format(date);
        try
        {
            notificationsDaoSql.save(details);
            boolean flag=false;
            for (String[] rows:notificationsDaoSql.get(details))
            {
                if(rows[0].equals("new notification"))
                {
                    flag=true;
                }
            }
            assertEquals(true,flag);
            notificationsDaoSql.delete(details);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @Test
    public void save()
    {
        String [] details = new String[3];
        details[0]="fanTest";
        details[1]="new notification";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date =new Date();;
        details[2] = format.format(date);
        try
        {
            notificationsDaoSql.save(details);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Test
    public void delete()
    {
        String [] details = new String[3];
        details[0]="fanTest";
        details[1]="new notification";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date =new Date();;
        details[2] = format.format(date);
        try
        {
            notificationsDaoSql.save(details);
            notificationsDaoSql.delete(details);
            boolean flag =notificationsDaoSql.get(details).isEmpty();
            assertEquals(true,flag);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
