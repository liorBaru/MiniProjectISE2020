package main.DB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NotificationsDaoSql implements DaoSql
{

    private DBconnector dBconnector =DBconnector.getInstance();
    private static NotificationsDaoSql notificationsDaoSql = new NotificationsDaoSql();

    public static NotificationsDaoSql getInstance()
    {
        return notificationsDaoSql;
    }

    @Override
    public List <String[]> get(String[] key)
    {
        String query="Select * from notifications where user_name=?; ";
        Connection connection =dBconnector.getConnection();
        if(connection!=null)
        {
            try
            {
                PreparedStatement stmt;
                connection.setCatalog("events");
                stmt = connection.prepareStatement(query);
                stmt.setString(1,key[0]);
                ResultSet resultSet = stmt.executeQuery();
                List<String[]> results = new LinkedList<>();
                while (resultSet.next())
                {
                    String [] row = new String[2];
                    row[0]=resultSet.getString(2);
                    java.util.Date date =resultSet.getTimestamp(3);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    row[1]=dateFormat.format(date);
                    results.add(row);
                }
                stmt.close();
                connection.close();
                return results;
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
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
        String query = "Insert INTO notifications (user_name,details,date_time) values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("events");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.util.Date date = simpleDateFormat.parse(params[2]);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                stmt.setDate(3,sqlStartDate);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("New notification inserted by username: " +params[0]);
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
        String query ="Delete From notifications where user_name = ? ;";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("events");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("Notification deleted by username: " + key[0]);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
