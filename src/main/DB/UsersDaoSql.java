package main.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static UsersDaoSql usersDaoSql = new UsersDaoSql();

    public static UsersDaoSql getInstance()
    {
        return usersDaoSql;
    }

    @Override
    public String[] get(String[] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM users where user_name =?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[4];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    results[2]=String.valueOf(resultSet.getBigDecimal(3));
                    results[3]=resultSet.getString(4);
                    stmt.close();
                    return results;
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
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query="INSERT INTO users(user_name,password,salt,type)" +"values(?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                BigDecimal big=new BigDecimal( params[2]);
                stmt.setBigDecimal(3,big);
                stmt.setString(4,params[3]);
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
                conn.setCatalog("accounts");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                //resultSet=stmt.executeQuery();
              //  String type=resultSet.getString(4);
              //  BigDecimal big =resultSet.getBigDecimal(3);
                String update="Replace INTO users(user_name,password)"+"values(?,?)";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                //stmt.setBigDecimal(3,big);
                //stmt.setString(4,type);
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
        String query =" Delete from users where user_name=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("accounts");
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
