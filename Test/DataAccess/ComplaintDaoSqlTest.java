package DataAccess;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class ComplaintDaoSqlTest {

    ComplaintDaoSql complaintDaoSql =ComplaintDaoSql.getInstance();



    @Test
    public void getAll()
    {
        String [] details = new String[5];
        details[0]="1";
        details[1]="fanTest";
        details[2]=String.valueOf(false);
        details[3]="This is the details of the complaint";
        Date date = new Date();
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
        details[4]=datetimeFormatter.format(date);
        try
        {
            complaintDaoSql.save(details);
            details[0]="2";
            complaintDaoSql.save(details);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        boolean flag1=false;
        boolean flag2=false;
        for (String[] row:complaintDaoSql.getAll())
        {
            if(row[0].equals("1"))
            {
                flag1=true;
            }
            else if (row[0].equals("2"))
            {
                flag2=true;
            }
        }
        try {
            assertEquals(true,flag1);
            assertEquals(true,flag2);
            complaintDaoSql.delete(details);
            details[0]="1";
            complaintDaoSql.delete(details);
        }
        catch (Exception e)
        {

        }

    }

    @Test
    public void save()
    {
        String [] details = new String[5];
        details[0]="1";
        details[1]="fanTest";
        details[2]=String.valueOf(false);
        details[3]="This is the details of the complaint";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        details[4]=simpleDateFormat.format(date);
        try
        {
            complaintDaoSql.save(details);
            boolean flag=false;
            for (String [] row:complaintDaoSql.get(details))
            {
                if(row[3].equals("This is the details of the complaint"))
                {
                    flag=true;
                }
            }
            assertEquals(true,flag);
            complaintDaoSql.delete(details);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void update()
    {
        String [] details = new String[5];
        details[0]="2";
        details[1]="fanTest";
        details[2]=String.valueOf(false);
        details[3]="This is the details of the complaint";
        Date date = new Date();
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
        details[4]=datetimeFormatter.format(date);
        try
        {
            complaintDaoSql.save(details);
            String[] update = new String[4];
            update[0]=String.valueOf(true);
            update[1]="answer";
            Date dateu = new Date();
            SimpleDateFormat datetimeFormatteru = new SimpleDateFormat("yyyy-MM-dd");
            update[2]= datetimeFormatteru.format(dateu);
            update[3]="2";
            complaintDaoSql.update(update);
            boolean flag =false;
            for (String [] row:complaintDaoSql.get(details))
            {
                if(row[4
                        ].equals("answer") && row[2].equals(String.valueOf(true)))
                {
                    flag=true;
                }
            }
            assertEquals(true,flag);
            complaintDaoSql.delete(details);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
