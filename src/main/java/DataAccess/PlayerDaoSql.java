package DataAccess;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoSql implements DaoSql
{
    private DBconnector dBconnector=DBconnector.getInstance();
    private static PlayerDaoSql playerDaoSql = new PlayerDaoSql();

    public static PlayerDaoSql getInstance()
    {
        return playerDaoSql;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        if(key[0].equals("Key"))
        {
            return getByKey(key);
        }
        else if(key[0].equals("Team"))
        {
            return getByTeam(key);
        }
        return null;
    }

    private List<String[]> getByTeam(String [] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM player where team =?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                resultSet=stmt.executeQuery();
                results= new String[10];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=String.valueOf(resultSet.getInt(4));
                    results[4]=String.valueOf(resultSet.getDate(5));
                    results[5]=resultSet.getString(6);
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

    private List<String[]> getByKey(String [] key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM player where user_name =?";
        Connection conn = dBconnector.getConnection();
        String [] results;
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[1]);
                resultSet=stmt.executeQuery();
                results= new String[10];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=String.valueOf(resultSet.getInt(4));
                    results[4]=String.valueOf(resultSet.getDate(5));
                    results[5]=resultSet.getString(6);
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
        String query = "SELECT * FROM player";
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
                results[3]=String.valueOf(resultSet.getInt(4));
                results[4]=String.valueOf(resultSet.getDate(5));
                results[5]=resultSet.getString(6);
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

        String query="INSERT INTO player(user_name,team,name,pageID,birthDay,positions,goals,redCards,yellowCards,games)"
                +"values(?,?,?,?,?,?,?,?,?,?);";
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
                stmt.setInt(4,Integer.parseInt(params[3]));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = simpleDateFormat.parse(params[4]);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                stmt.setDate(5,sqlStartDate);
                stmt.setString(6,params[5]);
                stmt.setInt(7,Integer.parseInt(params[6]));
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setInt(9,Integer.parseInt(params[8]));
                stmt.setInt(10,Integer.parseInt(params[9]));
                stmt.execute();
                stmt.close();
                logger.info("The player " +params[0] + " is succesufully saved");
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
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                String update="Replace INTO player(user_name,team,name,pageID,birthDay,positions,goals,redCards,yellowCards,games)" +"values(?,?,?,?,?,?,?,?,?,?)";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.setInt(4,Integer.parseInt(params[3]));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = simpleDateFormat.parse(params[4]);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                stmt.setDate(5,sqlStartDate);
                stmt.setString(6,params[5]);
                stmt.setInt(7,Integer.parseInt(params[6]));
                stmt.setInt(8,Integer.parseInt(params[7]));
                stmt.setInt(9,Integer.parseInt(params[8]));
                stmt.setInt(10,Integer.parseInt(params[9]));
                stmt.execute();
                stmt.close();
                logger.info("The player " +params[0] + " is succesufully update");
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
        String query =" Delete from player where user_name=?;" ;
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
                logger.info("The player " +key[0] + " is succesufully deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}
