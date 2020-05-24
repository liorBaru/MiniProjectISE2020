package main.DB;


import org.junit.Test;

import static org.junit.Assert.*;

public class BoardMemberDaoSqlTest {

    private BoardMemberDaoSql boardMemberDaoSql=BoardMemberDaoSql.getInstance();

    @Test
    public void getAll()
    {
        assert (boardMemberDaoSql.getAll().size()>=2);
    }



}
