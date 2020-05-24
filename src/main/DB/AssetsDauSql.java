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
    private static AssetsDauSql assetsDauSql=new AssetsDauSql();
    public static AssetsDauSql getInstance()
    {
        return assetsDauSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {

        if(key[0].equals("Team"))
        {
            return getByTeam(key[1]);
        }
        else if(key[0].equals("Key"))
        {
            return getByKey(key);
        }
        return null;
    }

    private List<String[]> getByTeam(String key)
    {
        String query ="SELECT  name, AssetType from assets where team=?;";
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
                    String [] row = new String[2];
                    row[0]=resultSet.getString(1);
                    row[1]=resultSet.getString(2);
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

    private List<String[]> getByKey(String[] key)
    {
        String query ="SELECT  AssetType from assets where team=? AND name=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                stmt.setString(2,key[2]);
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
        String query ="SELECT * from assets ;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[3];
                    row[0]=resultSet.getString(1);
                    row[1]=resultSet.getString(2);
                    row[2]=resultSet.getString(3);
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
    public void save(String[] params) throws SQLException
    {
        String query ="INSERT INTO assets(team,name,AssetType) values(?,?,?);";
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
                stmt.setString(3,params[2]);
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
                if(e.getMessage().contains("foreign key"))
                    throw new SQLException("Invalid team");
                else
                    throw new SQLException("team already as asset by this name");
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
        String query ="Delete from assets where team=? and name=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("manageteams");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.setString(2,key[1]);
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
