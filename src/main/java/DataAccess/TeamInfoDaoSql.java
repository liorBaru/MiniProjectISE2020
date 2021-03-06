package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamInfoDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
    private static TeamInfoDaoSql teamInfoDaoSql = new TeamInfoDaoSql();

    public static TeamInfoDaoSql getInstance()
    {
        return teamInfoDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM teaminfo where team =?";
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
                resultSet=stmt.executeQuery();
                results= new String[10];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=String.valueOf(resultSet.getInt(2));
                    results[2]=resultSet.getString(3);
                    results[3]=String.valueOf(resultSet.getInt(4));
                    results[4]=String.valueOf(resultSet.getInt(5));
                    results[5]=String.valueOf(resultSet.getInt(6));
                    results[6]=String.valueOf(resultSet.getInt(7));
                    results[7]=String.valueOf(resultSet.getInt(8));
                    results[8]=String.valueOf(resultSet.getInt(9));
                    results[9]=String.valueOf(resultSet.getInt(10));
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
        String query = "SELECT * FROM teaminfo";
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
                results[1]=String.valueOf(resultSet.getInt(2));
                results[2]=resultSet.getString(3);
                results[3]=String.valueOf(resultSet.getInt(4));
                results[4]=String.valueOf(resultSet.getInt(5));
                results[5]=String.valueOf(resultSet.getInt(6));
                results[6]=String.valueOf(resultSet.getInt(7));
                results[7]=String.valueOf(resultSet.getInt(8));
                results[8]=String.valueOf(resultSet.getInt(9));
                results[9]=String.valueOf(resultSet.getInt(10));
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

        String query="INSERT INTO teaminfo(team,season,league,points,goals,onGoals,losses,victories,draw,position)" +"values(?,?,?,?,?,?,?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {

            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.setString(3,params[2]);
                stmt.setInt(4,Integer.parseInt(params[3]));
                stmt.setInt(5,Integer.parseInt(params[4]));
                stmt.setInt(6,Integer.parseInt(params[5]));
                stmt.setInt(7,Integer.parseInt(params[6]));
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setInt(9,Integer.parseInt(params[8]));
                stmt.setInt(10,Integer.parseInt(params[9]));
                stmt.execute();
                stmt.close();
                logger.info("team information of " + params[0] + " successfuly saved");
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
        ResultSet resultSet;
        String query="Select FROM teaminfo(team,season,league,points,goals,onGoals,losses,victories,draw,position)" +"values(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                String update="Replace INTO teaminfo(team,season,league,points,goals,onGoals,losses,victories,draw,position)" +"values(?,?,?,?,?,?,?,?,?,?)";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.setString(3,params[2]);
                stmt.setInt(4,Integer.parseInt(params[3]));
                stmt.setInt(5,Integer.parseInt(params[4]));
                stmt.setInt(6,Integer.parseInt(params[5]));
                stmt.setInt(7,Integer.parseInt(params[6]));
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setInt(9,Integer.parseInt(params[8]));
                stmt.setInt(10,Integer.parseInt(params[9]));
                stmt.execute();
                stmt.close();
                logger.info("team information of " + params[0] + " successfuly updated");
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
        String query =" Delete from teaminfo where team=?;" ;
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
                logger.info("team information of " + key[0] + " successfuly deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}