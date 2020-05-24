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
                logger.error(e.getMessage());
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
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }

    @Override
    public void update(String[] params)
    {

    }

    @Override
    public void delete(String[] key) throws SQLException
    {
        if(key[0].equals("Key"))
        {
            deleteByKey(key);
        }
        else if(key[0].equals("Page"))
        {
            deleteByPage(key);
        }

    }

    private void deleteByKey(String[] key) throws SQLException {
        String query="DELETE from pagemessages where pageID=? AND date=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
                SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = datetimeFormatter1.parse(key[2]);
                Timestamp fromTS1 = new Timestamp(date.getTime());
                stmt.setTimestamp(2,fromTS1);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("Page : "+key[1] + "has delete message from"+key[2]);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    private void deleteByPage(String [] key) throws SQLException {
        String query="DELETE from pagemessages where pageID=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("All messages at page: "+ key[1] +" has been deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }
}
