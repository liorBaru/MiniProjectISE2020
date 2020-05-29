package DataAccess;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class FinancialDaoSqlTest {

    FinancialDaoSql financialDaoSql = FinancialDaoSql.getInstance();
    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void saveGetDelete()
    {
        try {
            Date date=new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            String[] key={"teamTest",String.valueOf(ft.format(date)),"pass 5000$ for buying new player","managerTest","5000.0"};
            String[] key2={"managerTest",ft.format(date)};
            financialDaoSql.save(key);
            assertTrue(financialDaoSql.get(key2).get(0)[2].equals("pass 5000$ for buying new player"));
            financialDaoSql.delete(key2);
            assertNull(financialDaoSql.get(key2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
