package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LeaguesDaoSql implements DaoSql
{
    private DBconnector dBconnector = DBconnector.getInstance();
    private static LeaguesDaoSql leagueDaoSql = new LeaguesDaoSql();

    public static LeaguesDaoSql getInstance()
    {
        return  leagueDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        String query ="SELECT * from leagues where name=?;";
        ResultSet resultSet;
        Connection conn = dBconnector.getConnection();
        String [] results;
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[2];
                List<String[]> queryResults=new LinkedList<>();
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=String.valueOf(resultSet.getInt(2));
                    queryResults.add(results);
                    stmt.close();
                    return  queryResults;
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
        String query="Select * From leagues ;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt;
            try
            {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                ResultSet resultSet =stmt.executeQuery();
                List<String[]> results = new LinkedList<>();
                while (resultSet.next())
                {
                    String [] row = new String[2];
                    row[0]=resultSet.getString(1);
                    row[1]= String.valueOf(resultSet.getInt(2));
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
        String query="Insert into leagues(name,level) values(?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The league " + params[0] + "succsefully saved");
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
        String query ="Update leagues(name,level) values(?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The league " + params[0] + "succsefully updated");
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
        String query="DELETE from leagues where name=? ;";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            try
            {
                PreparedStatement stmt;
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key[0]));
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The league " + key[0] + "succsefully deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }
}