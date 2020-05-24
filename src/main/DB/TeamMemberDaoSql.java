package main.DB;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class TeamMemberDaoSql implements DaoSql
{
    private DBconnector dBconnector=DBconnector.getInstance();
    private static TeamMemberDaoSql teamMemberDaoSql = new TeamMemberDaoSql();

    public static TeamMemberDaoSql getInstance()
    {
        return teamMemberDaoSql;
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
        String query="SELECT * FROM teammember where user_name =?";
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
                results= new String[1];
                if(resultSet.next())
                {
                    results[0]=resultSet.getString(1);
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
        String query = "SELECT * FROM teammember";
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
                list.add(results);

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
        String query="INSERT INTO teammember(user_name)" +"values(?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.execute();
                stmt.close();
                logger.info("team member " + params[0] + "successfuly save");
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
                stmt.setString(1,params[0]);
                String update="Replace INTO teammember(user_name)" +"values(?);";
                stmt=conn.prepareStatement(update);
                stmt.setString(1,params[0]);
                stmt.execute();
                stmt.close();
                logger.info("team member " + params[0] + "successfuly updated");
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
        String query =" Delete from teammember where user_name=?;" ;
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
                logger.info("team member " + key[0] + "successfuly deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }

    }
}
