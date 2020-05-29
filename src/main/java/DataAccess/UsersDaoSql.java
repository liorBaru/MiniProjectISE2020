package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoSql implements DaoSql
{
    private DBconnector dBconnector= DBconnector.getInstance();
    private static UsersDaoSql usersDaoSql = new UsersDaoSql();

    public static UsersDaoSql getInstance()
    {
        return usersDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM users where user_name =?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[4];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    stmt.close();
                    list.add(results);
                    return list;
                }
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll()
    {

        ResultSet resultSet;
        String query="SELECT user_name, UserType FROM users ;";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    results= new String[2];
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    list.add(results);
                }
                resultSet.close();
                stmt.close();
                conn.close();
                return list;
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query="INSERT INTO users(user_name,password,UserType)" +"values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.execute();
                stmt.close();
                logger.info("The user " + params[0] + "successfuly saved");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException {
        String query = "Update users set password=?,salt=?,UserType=? where user_name=?;";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(4,params[0]);
                stmt.setString(1,params[1]);
                stmt.setInt(2,Integer.valueOf(params[2]));
                stmt.setString(3,params[3]);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The user " + params[0] + "successfuly update");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void delete(String[] key) throws SQLException {
        String query =" Delete from users where user_name=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.execute();
                stmt.close();
                logger.info("The user " + key[0] + "successfuly deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}
