package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class IfaDaoSql implements DaoSql
{
    private DBconnector dBconnector=DBconnector.getInstance();
    private static IfaDaoSql ifaDaoSql= new IfaDaoSql();

    public static IfaDaoSql getInstance()
    {
        return ifaDaoSql;
    }
    @Override
    public List<String[]> get(String[] key)
    {
        String query="select * from ifa where user_name=? ;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;
            try
            {
                connection.setCatalog("users");
                statement=connection.prepareStatement(query);
                statement.setString(1,key[0]);
                ResultSet rs=statement.executeQuery();
                while (rs.next())
                {
                    String [] raw=new String[2];
                    raw[0]=rs.getString(1);
                    if(rs.getString(2)!=null)
                    {
                        raw[1]=rs.getString(2);
                    }
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
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<String[]> getAll()
    {
        String query="select * from ifa ;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            List<String[]> ans= new LinkedList<>();
            PreparedStatement preparedStatement;
            try
            {
                connection.setCatalog("users");
                preparedStatement=connection.prepareStatement(query);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next())
                {
                    String [] raw=new String[2];
                    raw[0]=rs.getString(1);
                    if(rs.getString(2)!=null)
                    {
                        raw[1]=rs.getString(2);
                    }
                    ans.add(raw);
                }
                rs.close();
                preparedStatement.close();
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
    public void save(String[] params) throws SQLException
    {
        String query="insert into ifa (user_name,name) values(?,?);";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement preparedStatement;
            try
            {
                connection.setCatalog("users");
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,params[0]);
                preparedStatement.setString(2,params[1]);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The user + " +params[0] + " is succsefullly saved as IFA");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String[] params)
    {
        String query="update ifa set name=? where user_name=?;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement preparedStatement;
            try
            {
                connection.setCatalog("users");
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(2,params[0]);
                preparedStatement.setString(1,params[0]);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The user + " +params[0] + " is succsefullly updated");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String[] key)
    {
        String query="delete from ifa where user_name=? ;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement preparedStatement;
            try
            {
                connection.setCatalog("users");
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,key[0]);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The user + " +key[0] + " is succsefullly deleted");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
