package main.DB;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GamesDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
    private static  GamesDaoSql gameDqoSql = new GamesDaoSql();

    public static GamesDaoSql getInstance()
    {
        return gameDqoSql;
    }
    @Override
    public List<String[]> get(String[] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM games where id=?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                resultSet=stmt.executeQuery();
                results= new String[13];
                if(resultSet.next())
                {
                    results[0]=String.valueOf(resultSet.getBigDecimal(1));
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    results[4]=String.valueOf(resultSet.getDate(5));
                    results[5]=resultSet.getString(6);
                    results[6]=resultSet.getString(7);
                    results[7]=String.valueOf(resultSet.getBigDecimal(8));
                    results[8]=resultSet.getString(9);
                    results[9]=resultSet.getString(10);
                    results[10]=resultSet.getString(11);
                    results[11]=resultSet.getString(12);
                    results[12]=String.valueOf(resultSet.getBoolean(13));
                    stmt.close();
                    list.add(results);
                    return list;
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
        String query = "SELECT * FROM games";
        List<String[]> list= new ArrayList();
        String[] results= null;
        ResultSet resultSet=null;
        try{
            Connection conn = dBconnector.getConnection();
            conn.setCatalog("managmentleaggues");
            Statement statement=conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                results[0]=String.valueOf(resultSet.getBigDecimal(1));
                results[1]=resultSet.getString(2);
                results[2]=resultSet.getString(3);
                results[3]=resultSet.getString(4);
                results[4]=String.valueOf(resultSet.getDate(5));
                results[5]=resultSet.getString(6);
                results[6]=resultSet.getString(7);
                results[7]=String.valueOf(resultSet.getBigDecimal(8));
                results[8]=resultSet.getString(9);
                results[9]=resultSet.getString(10);
                results[10]=resultSet.getString(11);
                results[11]=resultSet.getString(12);
                results[12]=String.valueOf(resultSet.getBoolean(13));
                statement.close();
                list.add(results);

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        Date date=null;
        try {
            date= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(params[4]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String query="INSERT INTO games(id,guest,host,field,date,score,league,column_8,mainRefree,lineRefree1,lineRefree2,extraRefree,var)" +"values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleaggues");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(params[0]));
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.setDate(5,date);
                stmt.setString(6,params[5]);
                stmt.setString(7,params[6]);
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setString(9,params[8]);
                stmt.setString(10,params[9]);
                stmt.setString(11,params[10]);
                stmt.setString(12,params[11]);
                stmt.setBoolean(13,Boolean.parseBoolean(params[12]));
                stmt.execute();
                stmt.close();
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException {
        Date date=null;
        try {
            date= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(params[4]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResultSet resultSet;
        String query="Select FROM games(id)"+
                "values(?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleaggues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                String update="Replace INTO  games(id,guest,host,field,date,score,league,column_8,mainRefree,lineRefree1,lineRefree2,extraRefree,var)" +"values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setInt(1, Integer.parseInt(params[0]));
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.setDate(5,date);
                stmt.setString(6,params[5]);
                stmt.setString(7,params[6]);
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setString(9,params[8]);
                stmt.setString(10,params[9]);
                stmt.setString(11,params[10]);
                stmt.setString(12,params[11]);
                stmt.setBoolean(13,Boolean.parseBoolean(params[12]));
                stmt.execute();
                stmt.close();
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }


    }

    @Override
    public void delete(String[] key) throws SQLException {
        String query =" Delete from games where id=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleaggues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.execute();
                stmt.close();
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}