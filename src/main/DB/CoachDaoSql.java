package main.DB;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static CoachDaoSql coachDaoSql = new CoachDaoSql();

    public static CoachDaoSql getInstance()
    {
        return coachDaoSql;
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
        String query="SELECT * FROM coach where user_name =?";
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
                results= new String[5];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    results[2]=String.valueOf(resultSet.getBigDecimal(3));
                    results[3]=resultSet.getString(4);
                    results[4]=resultSet.getString(5);
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
        String query = "SELECT * FROM coach";
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
                results[2]=String.valueOf(resultSet.getBigDecimal(3));
                results[3]=resultSet.getString(4);
                results[4]=resultSet.getString(5);
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
        String query="INSERT INTO coach(user_name,team,pageID,training,job)" +"values(?,?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                BigDecimal big=new BigDecimal( params[2]);
                stmt.setBigDecimal(3,big);
                stmt.setString(4,params[3]);
                stmt.setString(5,params[4]);
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
        String query="Select FROM users(user_name)"+
                "values(?);";
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
                String update="Replace INTO coach(user_name,team,pageID,training,job)" +"values(?,?,?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                BigDecimal big=new BigDecimal( params[2]);
                stmt.setBigDecimal(3,big);
                stmt.setString(4,params[3]);
                stmt.setString(5,params[4]);

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
        String query =" Delete from coach where user_name=?;" ;
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
