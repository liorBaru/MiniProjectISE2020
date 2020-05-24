package main.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDaoSql implements DaoSql
{
    private DBconnector dBconnector =DBconnector.getInstance();
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
                results= new String[3];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
                    results[1]=String.valueOf(resultSet.getInt(2));
                    results[2]=String.valueOf(resultSet.getBoolean(3));
                    stmt.close();
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
            return list;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
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
                conn.close();
                logger.info("The team " + params[0] + "succesfully saved");
            }
            catch (SQLException e)
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
                String update="Replace INTO team(name,pageID,status)" +"values(?,?,?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.setInt(2,Integer.parseInt(params[1]));
                stmt.setBoolean(3, Boolean.parseBoolean(params[2]));

                stmt.execute();
                stmt.close();
                logger.info("The team " + params[0] + "succesfully updated");
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
        String query =" Delete from team where name=?;" ;
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
                logger.info("The team " + key[0] + "succesfully deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}