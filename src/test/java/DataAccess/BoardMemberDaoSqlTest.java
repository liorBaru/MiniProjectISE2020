package DataAccess;


import DataAccess.BoardMemberDaoSql;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoardMemberDaoSqlTest {

    private BoardMemberDaoSql boardMemberDaoSql=BoardMemberDaoSql.getInstance();

    @Test
    @Category(UnitTests.class)
    public void getAll()
    {
        assert (boardMemberDaoSql.getAll().size()>=2);
    }



}
