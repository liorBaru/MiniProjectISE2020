package main.DB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ApointmentsDaoSql implements DaoSql
{

    DBconnector dBconnector= DBconnector.getInstance();
    private static ApointmentsDaoSql apointmentsDaoSql = new ApointmentsDaoSql();
    public static ApointmentsDaoSql getInstance()
    {
        return apointmentsDaoSql;
    }
    @Override
    public List<String[]> get(String[] key)
    {
        if(key[0].equals("Employee"))
        {
            return getByUserEmployee(key[1]);
        }
        else if(key[0].equals("Manager"))
        {
            return  getByManager(key[1]);
        }
        return null;
    }

    private List<String[]> getByUserEmployee(String key)
    {
        String query = "select manager from apointments where employee=? ;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;

            try {
                connection.setCatalog("manageteams");
                statement = connection.prepareStatement(query);
                statement.setString(1, key);
                ResultSet rs = statement.executeQuery();
                while (rs.next())
                {
                    String[] raw = new String[1];
                    raw[0] = rs.getString(1);
                    ans.add(raw);
                }
                rs.close();
                statement.close();
                connection.close();
                return ans;
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    private List<String[]> getByManager(String username)
    {
        String query = "select employee from apointments where manager=? ;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;

            try {
                connection.setCatalog("manageteams");
                statement = connection.prepareStatement(query);
                statement.setString(1,username);
                ResultSet rs = statement.executeQuery();
                while (rs.next())
                {
                    String[] raw = new String[1];
                    raw[0] = rs.getString(1);
                    ans.add(raw);
                }
                rs.close();
                statement.close();
                connection.close();
                return ans;
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll() {
        ResultSet resultaDb = null;
        List<String[]> results = new LinkedList<>();
        Connection conn = dBconnector.getConnection();
        String query = " SELECT * FROM apointments";
        if (conn != null)
        {
            Statement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.createStatement();
                resultaDb = stmt.executeQuery(query);
                while (resultaDb.next()) {
                    String[] row = new String[2];
                    row[0] = resultaDb.getString(1);
                    row[1] = resultaDb.getString(2);
                    results.add(row);
                }
                resultaDb.close();
                stmt.close();
                return results;
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query = " insert into apointments(manager, employee) " +
                "VALUES(?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
                stmt.execute();
                stmt.close();
                logger.info("The manager " + params[0]  + " successfuly appointed " +params[0]);
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException
    {

    }

    @Override
    public void delete(String[] params) throws SQLException {
        if(params[0].equals("Employee"))
        {
            deleteByEmployee(params[1]);
            logger.info("The Employee " + params[1]  + " successfuly delete");
        }
        else if(params[0].equals("Manager"))
        {
            deleteByManager(params[1]);
            logger.info("The manager " + params[1]  + " successfuly delete all apointments" );
        }

    }
    private void deleteByEmployee(String employee) throws SQLException
    {
        String query =" Delete from apointments where employee=? ;";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,employee);
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

    private void deleteByManager(String manager)throws SQLException
    {
        String query =" Delete from apointments where manager=? ;";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                conn.setCatalog("manageteams");
                stmt = conn.prepareStatement(query);
                stmt.setString(1,manager);
                stmt.execute();
                stmt.close();
                logger.info("The appointment succesfuly deleted");
            }
            catch (SQLException e)
            {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }
}
