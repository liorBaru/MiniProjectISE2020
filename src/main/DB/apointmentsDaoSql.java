package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class apointmentsDaoSql implements DaoSql
{
    DBconnector dBconnector= DBconnector.getInstance();

    @Override
    public Optional<String[]> get(String[] key)
    {
        return Optional.empty();
    }

    @Override
    public List<String[]> getAll()
    {
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query = "USE " + dBconnector.getDatabaseName() + " insert into apointments(manager, employee) " +
                "VALUES(?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
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

    }

    @Override
    public void delete(String[] params)
    {
        String query = "USE " + dBconnector.getDatabaseName() + " Delete from apointments(manager, employee) " +
                "VALUES(?,?);";
        Connection conn = dBconnector.getConnection();
        if (conn != null)
        {
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(query);
                stmt.setString(1,params[0]);
                stmt.setString(2,params[1]);
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
