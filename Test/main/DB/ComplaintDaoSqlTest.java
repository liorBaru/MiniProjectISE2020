package main.DB;


import org.junit.Test;

import static org.junit.Assert.*;


public class ComplaintDaoSqlTest {

    ComplaintDaoSql complaintDaoSql =ComplaintDaoSql.getInstance();
    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save()
    {
        String [] details = new String[4];
        details[0]="1";
        details[1]="galborabia";
        details[2]="false";
        details[3]="This is the details of the complaint";
    }

    @Test
    public void update() {
    }
}
