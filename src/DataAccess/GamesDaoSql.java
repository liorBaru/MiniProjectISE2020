package DataAccess;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        if(key[0].equals("Key"))
        {
            return getByKey(key[1]);
        }
        else if(key[0].equals("Team"))
        {
            return getByTeam(key[1]);
        }
        else if(key[0].equals("Refree"))
        {
            return getByRefree(key[1]);
        }
        return null;
    }

    private List<String[]> getByKey (String key)
    {
        ResultSet resultSet;
        String query="SELECT * FROM games where id=?";
        Connection conn = dBconnector.getConnection();
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(key));
                resultSet=stmt.executeQuery();
                if(resultSet.next())
                {
                    String[] results= new String[13];
                    results[0]=String.valueOf(resultSet.getInt(1));
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    results[4]="";
                    if(resultSet.getDate(5)!=null)
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date dateC = resultSet.getDate(5);
                        results[4]=dateFormat.format(dateC);
                    }
                    results[5]="";
                    if(resultSet.getString(6)!=null)
                    {
                        results[5]=resultSet.getString(6);
                    }
                    results[6]=resultSet.getString(7);
                    results[7]=resultSet.getString(8);
                    results[8]=resultSet.getString(9);
                    results[9]=resultSet.getString(10);
                    results[10]=resultSet.getString(11);
                    results[11]=String.valueOf(resultSet.getBoolean(12));
                    results[12]=String.valueOf(resultSet.getBoolean(13));
                    list.add(results);
                }
                return list;
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
    private List<String[]> getByTeam(String team)
    {
        ResultSet resultSet;
        String query="SELECT * FROM games where guest=? || host=?";
        Connection conn = dBconnector.getConnection();
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,team);
                stmt.setString(2,team);
                resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String[] results= new String[13];
                    results[0]=String.valueOf(resultSet.getInt(1));
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date dateC = resultSet.getDate(5);
                    results[4]=dateFormat.format(dateC);
                    results[5]=resultSet.getString(6);
                    results[6]=resultSet.getString(7);
                    results[7]=resultSet.getString(8);
                    results[8]=resultSet.getString(9);
                    results[9]=resultSet.getString(10);
                    results[10]=resultSet.getString(11);
                    results[11]=String.valueOf(resultSet.getBoolean(12));
                    results[12]=String.valueOf(resultSet.getBoolean(13));
                    results[13]=String.valueOf(resultSet.getTime(14));
                    results[14]=String.valueOf(resultSet.getTime(15));
                    list.add(results);
                }
                return list;
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
    private List<String[]> getByRefree(String refree)
    {
        ResultSet resultSet;
        String query="SELECT * FROM games where mainRefree=? || lineRefree1=? || lineRefree2=? || extraRefree=?;";
        Connection conn = dBconnector.getConnection();
        List<String[]> list = new ArrayList<>();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,refree);
                stmt.setString(2,refree);
                stmt.setString(3,refree);
                stmt.setString(4,refree);
                resultSet=stmt.executeQuery();
                while (resultSet.next())
                {
                    String[] results= new String[13];
                    results[0]=String.valueOf(resultSet.getInt(1));
                    results[1]=resultSet.getString(2);
                    results[2]=resultSet.getString(3);
                    results[3]=resultSet.getString(4);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date dateC = resultSet.getDate(5);
                    results[4]=dateFormat.format(dateC);
                    results[5]=resultSet.getString(6);
                    results[6]=resultSet.getString(7);
                    results[7]=resultSet.getString(8);
                    results[8]=resultSet.getString(9);
                    results[9]=resultSet.getString(10);
                    results[10]=resultSet.getString(11);
                    results[11]=String.valueOf(resultSet.getBoolean(12));
                    results[12]=String.valueOf(resultSet.getBoolean(13));
                    results[13]=String.valueOf(resultSet.getTime(14));
                    results[14]=String.valueOf(resultSet.getTime(15));
                    list.add(results);
                }
                return list;
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
        ResultSet resultSet=null;
        try{
            Connection conn = dBconnector.getConnection();
            conn.setCatalog("managmentleaggues");
            Statement statement=conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String[] results= new String[13];
                results[0]=String.valueOf(resultSet.getInt(1));
                results[1]=resultSet.getString(2);
                results[2]=resultSet.getString(3);
                results[3]=resultSet.getString(4);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date dateC = resultSet.getDate(5);
                results[4]=dateFormat.format(dateC);
                results[5]=resultSet.getString(6);
                results[6]=resultSet.getString(7);
                results[7]=resultSet.getString(8);
                results[8]=resultSet.getString(9);
                results[9]=resultSet.getString(10);
                results[10]=resultSet.getString(11);
                results[11]=String.valueOf(resultSet.getBoolean(12));
                results[12]=String.valueOf(resultSet.getBoolean(13));
                results[13]=String.valueOf(resultSet.getTime(14));
                results[14]=String.valueOf(resultSet.getTime(15));
                list.add(results);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query="INSERT INTO games(id,guest,host,field,date,score,league,mainRefree,lineRefree1,lineRefree2,extraRefree,var,reported)" +"values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(params[0]));
                stmt.setString(2,params[1]);
                stmt.setString(3,params[2]);
                stmt.setString(4,params[3]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = dateFormat.parse(params[4]);
                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                stmt.setDate(5,sqlDate);
                stmt.setString(6,params[5]);
                stmt.setString(7,params[6]);
                stmt.setString(8,params[7]);
                stmt.setString(9,params[8]);
                stmt.setString(10,params[9]);
                stmt.setString(11,params[10]);
                stmt.setBoolean(12,Boolean.parseBoolean(params[11]));
                stmt.setBoolean(13,Boolean.parseBoolean(params[12]));
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("New game is scheduled in system");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException
    {
        String update="update games guest=?,host=?,field=?,date=?,score=?,league=?,mainRefree=?,lineRefree1=?,lineRefree2=?,extraRefree=?,var=?,reported=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt=conn.prepareStatement(update);
                stmt.setInt(13, Integer.parseInt(params[0]));
                stmt.setString(1,params[1]);
                stmt.setString(2,params[2]);
                stmt.setString(3,params[3]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = dateFormat.parse(params[4]);
                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                stmt.setDate(4,sqlDate);
                stmt.setString(5,params[5]);
                stmt.setString(6,params[6]);
                stmt.setString(7,params[7]);
                stmt.setString(8,params[8]);
                stmt.setString(8,params[9]);
                stmt.setString(10,params[10]);
                stmt.setBoolean(11,Boolean.parseBoolean(params[11]));
                stmt.setBoolean(12,Boolean.parseBoolean(params[12]));
                stmt.execute();
                stmt.close();
                logger.info("The game id " + params[0]  + "is updated");
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
        String query =" Delete from games where id=?;" ;
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("managmentleagues");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,key[0]);
                stmt.execute();
                stmt.close();
                logger.info("The game id " + key[0]  + "is deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}