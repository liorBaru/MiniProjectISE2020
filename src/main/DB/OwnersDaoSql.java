package main.DB;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnersDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static OwnersDaoSql ownerDaoSql = new OwnersDaoSql();

    public static  OwnersDaoSql getInstance()
    {
        return ownerDaoSql;
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
        String query="SELECT * FROM owners where user_name =?";
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
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    stmt.close();
                    list.add(results);
                    return list;
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
        String query = "SELECT * FROM owners";
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
                results[3]=resultSet.getString(4);
                list.add(results);

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
        String query="INSERT INTO owners(user_name,name,team,anotherJob)" +"values(?,?,?,?);";
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
                stmt.setString(4,params[3]);
                stmt.execute();
                stmt.close();
                logger.info("The owner " +params[0] +" is succsufully saved");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String[] params)
    {
        ResultSet resultSet;
        String query="Select FROM owners(user_name)"+
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
                String update="Replace INTO owner (user_name,name,team,anotherJob)" +"values(?,?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.execute();
                stmt.close();
                logger.info("The owner " +params[0] + " is succsufully update");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }


    }

    @Override
    public void delete(String[] key)
    {
        String query =" Delete from owners where user_name=?;" ;
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
                logger.info("The owner " +key[0] + " is succsufully deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
