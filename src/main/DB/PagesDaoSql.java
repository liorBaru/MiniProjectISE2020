package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PagesDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
    private static PagesDaoSql pagesDaoSql = new PagesDaoSql();

    public static PagesDaoSql getInstance()
    {
        return pagesDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        String query ="SELECT * from pages where id=?;";
        ResultSet resultSet;
        Connection conn = dBconnector.getConnection();
        String [] results;
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[3];
                List<String[]> queryResults=new LinkedList<>();
                if(resultSet.next())
                {
                    results[0]=String.valueOf(resultSet.getInt(1));
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    queryResults.add(results);
                    stmt.close();
                    return  queryResults;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll()
    {
        String query="Select * From pages ;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                ResultSet resultSet =stmt.executeQuery();
                List<String[]> results = new LinkedList<>();
                while (resultSet.next())
                {
                    String [] row = new String[3];
                    row[0]= String.valueOf(resultSet.getInt(1));
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
        String query="Insert into pages(id,owner,name) values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(params[0]));
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
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
        String query ="Update pages Set owner=?,name=? where id=?  ;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(3,Integer.valueOf(params[0]));
                stmt.setString(1,params[1]);
                stmt.setString(2,params[2]);
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
    public void delete(String[] key)
    {
        String query="DELETE from pages where id=? ;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[0]));
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
