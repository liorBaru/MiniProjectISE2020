package DataAccess;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


class FinancialDaoSql implements DaoSql
{
    private DBconnector dBconnector;
    private static FinancialDaoSql financialDaoSql = new FinancialDaoSql();

    public static FinancialDaoSql getInstance()
    {
        return financialDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM financialactions where member =? AND date =?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.setString(2,key[1]);
                resultSet=stmt.executeQuery();
                results= new String[5];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=String.valueOf(resultSet.getDate(2));
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    results[4]=String.valueOf(resultSet.getDouble(5));
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
        String query = "SELECT * FROM financialactions";
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
                results[1]=String.valueOf(resultSet.getDate(2));
                results[2]=resultSet.getString(3);
                results[3]=resultSet.getString(4);
                results[4]=String.valueOf(resultSet.getDouble(5));
                statement.close();
                list.add(results);

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    @Override
    public void save(String[] params) throws SQLException, ParseException {
        String query="INSERT INTO financialactions(team,date,description,member,price)" +"values(?,?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDateFormat.parse(params[1]);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setDate(2,sqlStartDate);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.setDouble(5,Double.parseDouble(params[4]));
                stmt.execute();
                stmt.close();
                logger.info("financial action succesfully saved");
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
        java.sql.Date date=null;
        try {
            date= (Date) new SimpleDateFormat("dd/MM/yyyy").parse(params[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ResultSet resultSet;
        String query="Select FROM financialactions(team)"+
                "values(?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                String update="Replace INTO users(team,date,description,member,price)"+"values(?,?,?,?,?)";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setDate(2,date);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                stmt.setDouble(5,Double.parseDouble(params[4]));
                stmt.execute();
                stmt.close();
                logger.info("financial action succesfully updated");
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
        String query =" Delete from financialactions where member =? AND date =?"; ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.setString(2,key[1]);
                stmt.execute();
                stmt.close();
                logger.info("financial action succesfully deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}
