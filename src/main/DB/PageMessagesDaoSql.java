package main.DB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PageMessagesDaoSql implements DaoSql
{

    private DBconnector dBconnector=DBconnector.getInstance();
    private static PageMessagesDaoSql instance =new PageMessagesDaoSql();

    public static PageMessagesDaoSql getInstance()
    {
        return instance;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        String query = "Select message,date from pagemessages where pageID=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[0]));
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[2];
                    row[0]=resultSet.getString(1);
                    Date date =resultSet.getTimestamp(2);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    row[1] = dateFormat.format(date);
                    results.add(row);
                }
                stmt.close();
                conn.close();
                return results;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll()
    {
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query ="INSERT INTO pagemessages(pageID,message,date) values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                int x=Integer.valueOf(params[0]);
                stmt.setInt(1,x);
                stmt.setString(2,params[1]);
                SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = datetimeFormatter.parse(params[2]);
                Timestamp fromTS1 = new Timestamp(date.getTime());
                stmt.setTimestamp(3,fromTS1);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The message " + params[1] + "successfuly saved in pageID " + params[0]);

            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(String[] params)
    {

    }

    @Override
    public void delete(String[] key)
    {
        String query="DELETE from pagemessages where pageID=? AND date=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[0]));
                SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = datetimeFormatter1.parse(key[1]);
                Timestamp fromTS1 = new Timestamp(date.getTime());
                stmt.setTimestamp(2,fromTS1);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The message successfuly deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
