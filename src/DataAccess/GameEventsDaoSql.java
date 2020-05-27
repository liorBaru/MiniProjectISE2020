package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class GameEventsDaoSql implements DaoSql
{

    private DBconnector dBconnector=DBconnector.getInstance();
    private static GameEventsDaoSql gameEventsDaoSql =new GameEventsDaoSql();
    public static GameEventsDaoSql getInstance()
    {
        return gameEventsDaoSql;
    }
    @Override
    public List<String[]> get(String[] key)
    {
        String query ="select * from gameevents where gameID=?;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement preparedStatement;
            List<String []> events= new LinkedList<>();
            try
            {
                connection.setCatalog("managmentleagues");
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setInt(1,Integer.valueOf(key[0]));
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    String [] event=new String[5];
                    event[0]=String.valueOf(resultSet.getInt(1));
                    event[1]=resultSet.getString(2);
                    event[2]=resultSet.getString(3);
                    event[3]=String.valueOf(resultSet.getInt(4));
                    java.util.Date date = resultSet.getTimestamp(5);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    event[4] = dateFormat.format(date);
                    events.add(event);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return events;
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
        String query ="select * from gameevents ;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement preparedStatement;
            List<String []> events= new LinkedList<>();
            try
            {
                connection.setCatalog("managmentleagues");
                preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    String [] event=new String[5];
                    event[0]=String.valueOf(resultSet.getInt(1));
                    event[1]=resultSet.getString(2);
                    event[2]=resultSet.getString(3);
                    event[3]=String.valueOf(resultSet.getInt(4));
                    java.util.Date date = resultSet.getTimestamp(5);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    event[4] = dateFormat.format(date);
                    events.add(event);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return events;
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
        String query="insert into gameevents (gameID, teamMember, EventType, gameminute, date) values(?,?,?,?,?);";
        Connection conn=dBconnector.getConnection();
        if(conn!=null)
        {
            PreparedStatement preparedStatement;
            try {
                conn.setCatalog("managmentleagues");
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,Integer.valueOf(params[0]));
                preparedStatement.setString(2, params[1]);
                preparedStatement.setString(3,params[2]);
                preparedStatement.setInt(4,Integer.valueOf(params[3]));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.util.Date date = simpleDateFormat.parse(params[4]);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                preparedStatement.setDate(5,sqlStartDate);
                preparedStatement.execute();
                preparedStatement.close();
                conn.close();
                logger.info("New GameEvent "+params[0]+", "+params[1]+", "+params[2]+", "+params[3]);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void update(String[] params) throws SQLException
    {
        throw new SQLException("Invalid operation");
    }

    @Override
    public void delete(String[] key) throws SQLException {
        throw new SQLException("Invalid operation");
    }
}
