package DataAccess;


import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class PageFollowersDaoSqlTest {

    PageFollowersDaoSql pageFollowersDaoSql;
    @Before
    public void setUp() throws Exception
    {
        pageFollowersDaoSql=PageFollowersDaoSql.getInstance();
    }

    @Test
    public void save() throws SQLException {

        String [] details = new String[3];
        details[0]="key";
        details[1]="0";
        details[2]="fanTest";
        pageFollowersDaoSql.delete(details);
        details[0]="0";
        details[1]="fanTest";
        pageFollowersDaoSql.save(details);
        details[0]="user_name";
        boolean flag=false;
        for (String[] row:pageFollowersDaoSql.get(details))
        {
            if(row[0].equals("0"))
            {
                flag=true;
            }
        }
        assertEquals(true,flag);
        details[0]="0";
        pageFollowersDaoSql.delete(details);
    }

    @Test
    public void delete() throws SQLException {
        String [] details = new String[3];
        details[0]="key";
        details[1]="0";
        details[2]="fanTest";
        pageFollowersDaoSql.delete(details);
        details[0]="pageID";
        details[1]="0";
        boolean flag=true;
        for (String [] row:pageFollowersDaoSql.get(details))
        {
            if(row[1]=="fanTest")
            {
                flag=false;
            }
        }
        assertEquals(true,flag);
        details[0]="0";
        details[1]="fanTest";
        pageFollowersDaoSql.save(details);
    }
}
