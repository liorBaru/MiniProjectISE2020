package main.DB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ApointmentsDaoSql implements DaoSql
{
    DBconnector dBconnector= DBconnector.getInstance();

    @Override
    public List<String[]> get(String[] key)
    {
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
            } catch (SQLException e) {
                e.printStackTrace();
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
        String query =" Delete from apointments(manager, employee) " +
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
