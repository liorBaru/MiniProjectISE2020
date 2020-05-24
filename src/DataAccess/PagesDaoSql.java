package DataAccess;

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
        String query ="SELECT owner,page_name from pages where id=?;";
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
                results= new String[2];
                List<String[]> queryResults=new LinkedList<>();
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
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
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query="Insert into pages(id,owner,page_name) values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managepages");
                stmt = conn.prepareStatement(query);
                int x=Integer.valueOf(params[0]);
                stmt.setInt(1,x);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The pageID " + params[0] + "successfuly saved in pages ");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));

            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException {
        String query ="Update pages Set owner=?,page_name=? where id=?  ;";
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

                logger.info("The page succesfully updated");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void delete(String[] key) throws SQLException {
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
                logger.info("The page " +key[0] +"succesfully deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }
}
