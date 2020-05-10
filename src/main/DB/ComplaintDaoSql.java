package main.DB;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ComplaintDaoSql implements DaoSql
{
    private DBconnector dBconnector=DBconnector.getInstance();
    private static ComplaintDaoSql instance = new ComplaintDaoSql();

    public static ComplaintDaoSql getInstance()
    {
        return instance;
    }

    @Override
    public List<String[]> get(String[] key)
    {
        return null;
    }

    @Override
    public List<String[]> getAll()
    {
        return null;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query = "INSERT INTO complaint (id,user_name,status,details) values(?,?,?,?);";
        Connection conn = dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement stmt = null;
            try
            {
                conn.setCatalog("events");
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,Integer.valueOf(params[0]));
                stmt.setString(2,params[1]);
                stmt.setBoolean(3,Boolean.valueOf(params[2]));
                stmt.setString(3,params[4]);
                stmt.execute();
                stmt.close();
            }
            catch (Exception e)
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
    public void delete(String[] key)
    {
        // console error insert error
    }
}
