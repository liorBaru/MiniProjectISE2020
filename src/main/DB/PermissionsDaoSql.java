package main.DB;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionsDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
    private static PermissionsDaoSql permissionsDaoSql = new PermissionsDaoSql();

    public static PermissionsDaoSql getInstance()
    {
        return permissionsDaoSql;
    }

    @Override
    /**
     * David
     * get coach
     */
    public List<String[]> get(String[] key)
    {
        if(key[0].equals("Key"))
        {
            return getByKey(key);
        }
        else if(key[0].equals("user_name"))
        {
            return getByUserName(key);
        }
      return null;
    }
    private List<String[]> getByKey(String[] key)
    {
        Connection conn = dBconnector.getConnection();
        ResultSet resultSet;
        String query="SELECT * FROM permissions where user_name =?";
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[2];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
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
    private List<String[]> getByUserName(String [] key)
    {
        Connection conn = dBconnector.getConnection();
        ResultSet resultSet;
        String query="SELECT * FROM permissions where user_name =?";
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[2];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
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
        String query = "SELECT * FROM permissions";
        List<String[]> list= new ArrayList();
        String[] results= null;
        ResultSet resultSet=null;
        try{
            Connection conn = dBconnector.getConnection();
            conn.setCatalog("manageteams");
            Statement statement=conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                results[0]=resultSet.getString(1);
                results[1]=resultSet.getString(2);
                list.add(results);

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return list;
    }
    /**
     * David
     * insert new permission to DB
     */
    @Override
    public void save(String[] params) throws SQLException
    {
        String query="INSERT INTO permissions(user_name,permission)" +"values(?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.execute();
                stmt.close();
                logger.info("The permission " +params[1] + " is succesufully saved");
            }
            catch (SQLException e)
            {
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void update(String[] params)
    {
    }

    private void deleteByKey(String[] key) throws SQLException {
        String query =" Delete from permissions where user_name=? AND permissions=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                stmt.setString(2,key[2]);
                stmt.execute();
                stmt.close();
                logger.info("The permission " +key[2] + " of "+key[1]+" succesufully updated");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }


    }
    private void deleteByUserName(String[] key) throws SQLException {
        String query =" Delete from permissions where user_name=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                stmt.execute();
                stmt.close();
                logger.info("The permission is succesufully deleted");
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
        if(key[0].equals("Key"))
        {
            deleteByKey(key);
        }
        else if(key[0].equals("user_name"))
        {
            deleteByUserName(key);
        }

    }
}
