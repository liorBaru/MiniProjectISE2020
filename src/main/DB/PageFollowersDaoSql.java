package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PageFollowersDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
    private static PageFollowersDaoSql instance = new PageFollowersDaoSql();

    public static PageFollowersDaoSql getInstance()
    {
        return instance;
    }


    @Override
    public List<String[]> get(String[] key)
    {
        if(key[0]=="pageID")
        {
            return getByPageID(key);
        }
        else if(key[0]=="user_name")
        {
            return getByUserName(key);
        }
        else
        {
            return getByKey(key);
        }
    }

    private List<String[]> getByKey(String [] key)
    {
        String query ="SELECT  user_name from pagefollowers where pageID=? AND user_name=? ;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
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
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<String[]> getByPageID(String[] key)
    {
        String query ="SELECT  user_name from pagefollowers where pageID=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
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
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<String[]> getByUserName(String[]key)
    {
        String query ="SELECT  pageID from pagefollowers where user_name=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[1];
                    row[0]=String.valueOf(resultSet.getInt(1));
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
        String query ="SELECT  * from pagefollowers;";
        Connection conn = dBconnector.getConnection();
        List<String[]> results= new LinkedList<>();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                ResultSet resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String [] row = new String[2];
                    row[0]=String.valueOf(resultSet.getInt(1));
                    row[1]=resultSet.getString(2);
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
        String query ="INSERT INTO pagefollowers(pageID,user_name) values(?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(params[0]));
                stmt.setString(2,params[1]);
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
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
       if(key[0].equals("user_name"))
       {
           deleteByuserName(key);
       }
       else if(key[0].equals("pageID"))
       {
           deleteByPageID(key);
       }
       else if(key[0].equals("key"))
       {
           deleteBykey(key);
       }
    }

    private void deleteByuserName(String[] key)
    {
        String query ="Delete from pagefollowers and user_name=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
                stmt.setString(2,key[2]);
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    private void deleteByPageID(String[] key)
    {
        String query ="Delete from pagefollowers where pageID=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void deleteBykey(String [] key)
    {
        String query ="Delete from pagefollowers where pageID=? and user_name=?;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[1]));
                stmt.setString(2,key[2]);
                stmt.execute();
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
