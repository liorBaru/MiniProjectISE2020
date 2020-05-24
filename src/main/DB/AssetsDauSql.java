package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AssetsDauSql implements DaoSql
{

    private DBconnector dBconnector;
    @Override
    public List<String[]> get(String[] key)
    {

        if(key[0].equals("Emploee"))
        {
            return getByEmploee(key[1]);
        }
        else if(key[0].equals("Manager"))
        {
            return getByManager(key[1]);
        }
        return null;
    }

    private List<String[]> getByManager(String key)
    {
        String query ="SELECT  employee from apointments where manager=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key);
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[1];
                    row[0]=resultSet.getString(1);
                    results.add(row);
                }
                stmt.close();
                conn.close();
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

    private List<String[]> getByEmploee(String key)
    {
        String query ="SELECT  manager from apointments where emploee=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key);
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[1];
                    row[0]=resultSet.getString(1);
                    results.add(row);
                }
                stmt.close();
                conn.close();
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
        String query ="INSERT INTO apointments(manager,employee) values(?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.execute();
                stmt.close();
                conn.close();
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
        if(key[0].equals("Key"))
        {
            deleteByKey(key);
        }
    }

    private void deleteByKey(String[] key)
    {
        String query ="Delete from apointments where manager=? and employee=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                stmt.setString(2,key[2]);
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
