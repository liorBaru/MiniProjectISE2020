package main.DB;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


public class PageMessagesDaoSqlTest {

    PageMessagesDaoSql pageMessagesDaoSql;

    @Before
    public void setUp() throws Exception
    {
        pageMessagesDaoSql=PageMessagesDaoSql.getInstance();
    }

    @After
    public void tearDown() throws Exception
    {


    }

    @Test
    public void get()
    {
        String [] key =new String[1];
        key[0]="0";
        boolean flag=false;
        for (String [] row :pageMessagesDaoSql.get(key))
        {
            if(row[1]=="test message")
            {
                flag=true;
            }
        }
        assertEquals(true,flag);
    }


    @Test
    public void save() throws SQLException
    {
        String [] details = new String[3];
        details[0]="0";
        details[1]="newMessage";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date =new Date();;
        details[2] = format.format(date);
        pageMessagesDaoSql.save(details);
        boolean flag=false;
        for (String [] row :pageMessagesDaoSql.get(details))
        {
            if(row[0].equals("newMessage"))
            {
                flag=true;
            }
        }
        details[1]=details[2];
        assertEquals(true,flag);
        pageMessagesDaoSql.delete(details);
    }
}
