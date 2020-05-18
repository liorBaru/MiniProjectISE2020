package main.DB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;


public class PagesDaoSqlTest {

    PagesDaoSql pagesDaoSql;
    @Before
    public void setUp() throws Exception
    {
        pagesDaoSql=PagesDaoSql.getInstance();
    }

    @Test
    public void get()
    {
        String[] details=new String[1];
        details[0]="0";
        List<String[]> ans =pagesDaoSql.get(details);
        String [] row =ans.get(0);
        assertEquals("coachPage",row[1]);
    }

    @Test
    public void getAll()
    {
        List<String[]> ans = pagesDaoSql.getAll();
        assertEquals(false,ans.isEmpty());
    }

    @Test
    public void save()
    {
        String [] details= new  String[3];
        details[0]="1";
        details[1]="playerTest";
        details[2]="pageName";
        String message="";
        try {
            pagesDaoSql.save(details);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            message=e.getMessage();
        }
        assertEquals("Duplicate entry '1' for key 'PRIMARY'",message);
    }

    @Test
    public void update()
    {
        String [] details= new  String[3];
        details[0]="1";
        details[1]="ownerUserName";
        details[2]="pageNewName";
        pagesDaoSql.update(details);
        List<String[]> ans=pagesDaoSql.get(details);
        String [] row= ans.get(0);
        assertEquals("pageNewName",row[1]);
    }
}
