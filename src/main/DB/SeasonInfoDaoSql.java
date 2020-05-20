package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeasonInfoDaoSql implements DaoSql
{
    private DBconnector dBconnector = DBconnector.getInstance();
    private static SeasonInfoDaoSql seasonInfoDaoSql = new SeasonInfoDaoSql();

    public static SeasonInfoDaoSql getInstance()
    {
        return  seasonInfoDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        String query ="SELECT * from seasoninfo where name=?;";
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
                results= new String[4];
                List<String[]> queryResults=new LinkedList<>();
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=String.valueOf(resultSet.getInt(2));
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    queryResults.add(results);
                    stmt.close();
                    return  queryResults;
                }
            }
            catch (SQLException e)
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
        String query="Select * From seasoninfo ;";
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
                    String [] row = new String[4];
                    row[0]=resultSet.getString(1);
                    row[1]=String.valueOf(resultSet.getInt(2));
                    row[2]=resultSet.getString(3);
                    row[3]=resultSet.getString(4);
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
    public void save(String[] params) throws SQLException
    {
        String query="Insert into seasoninfo(league,year,gameSchedule,leagueCalculator) values(?,?,?,?);";
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
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The sesson is sucessfuly add to " + params[0]);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new SQLException(e.getMessage());
            }
        }
    }

    @Override
    public void update(String[] params)
    {
        String query ="Update seasoninfo(league,year,gameSchedule,leagueCalculator) values(?,?,?,?);";
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
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("The sesson is successfully updated");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String[] key)
    {
        String query="DELETE from seasoninfo where name=? ;";
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
                logger.info("The sesson is successfully deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}