package DataAccess;

import org.junit.Before;
import org.junit.Test;

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
            message=e.getMessage();
        }
        assertEquals("wrong parameters",message);
    }

    @Test
    public void update() throws SQLException {
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
