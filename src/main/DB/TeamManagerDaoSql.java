package main.DB;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamManagerDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static TeamManagerDaoSql teamManagerDaoSql = new TeamManagerDaoSql();

    public static TeamManagerDaoSql getInstance()
    {
        return teamManagerDaoSql;
    }

    @Override
    /**
     * David
     * get coach
     */
    public List<String[]> get(String[] key)
    {
        Connection conn = dBconnector.getConnection();
        ResultSet resultSet;
        String query="SELECT * FROM teammanager where user_name =?";
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
                results= new String[1];
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
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll()
    {
        String query = "SELECT * FROM teammanager";
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
                results[2]=resultSet.getString(3);
                list.add(results);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     * David
     * insert new Coach to DB
     */
    @Override
    public void save(String[] params) throws SQLException
    {
        String query="INSERT INTO teammanager(user_name,team,name)" +"values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.execute();
                stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String[] params)
    {
        ResultSet resultSet;
        String query="Select FROM teammanager(user_name,team,name)"+
                "values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                String update="Replace INTO teammanager(user_name,team,name)" +"values(?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.execute();
                stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void delete(String[] key)
    {
        String query =" Delete from teammanager where user_name=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.execute();
                stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}
