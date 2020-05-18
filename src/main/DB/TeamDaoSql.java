package main.DB;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class TeamDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static TeamDaoSql teamDaoSql = new TeamDaoSql();

    public static  TeamDaoSql getInstance()
    {
        return teamDaoSql;
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
        String query="SELECT * FROM team where name=?";
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
                results= new String[4];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[2]=String.valueOf(resultSet.getInt(2));
                    results[3]=String.valueOf(resultSet.getBoolean(3));
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
        String query = "SELECT * FROM team";
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
                results[2]=String.valueOf(resultSet.getInt(2));
                results[3]=String.valueOf(resultSet.getBoolean(3));
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
        String query="INSERT INTO team(name,pageID,status)" +"values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.setBoolean(3, Boolean.parseBoolean(params[2]));
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
        String query="Select FROM team(name,pageID,status)"+
                "values(?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                //resultSet=stmt.executeQuery();
                //  String type=resultSet.getString(4);
                //  BigDecimal big =resultSet.getBigDecimal(3);
                String update="Replace INTO team(name,pageID,status)" +"values(?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.setBoolean(3, Boolean.parseBoolean(params[2]));

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
        String query =" Delete from team where name=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managteams");
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
