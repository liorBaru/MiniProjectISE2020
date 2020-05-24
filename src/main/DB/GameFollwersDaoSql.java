package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GameFollwersDaoSql implements DaoSql {

    private DBconnector dBconnector = DBconnector.getInstance();
    private static GameFollwersDaoSql instance= new GameFollwersDaoSql();

    public static GameFollwersDaoSql getInstance()
    {
        return instance;
    }
    @Override
    public List<String[]> get(String[] key)
    {
        if(key[0].equals("user_name"))
        {
            return getByUserName(key[1]);
        }
        else if(key[0].equals("GameID"))
        {
            return  getByGameID(key[1]);
        }
        else if(key[0].equals("Key"))
        {
            return getByKey(key);
        }
        return null;
    }

    private List<String[]> getByUserName(String userName)
    {
        String query = "select * from gamefollwers where user_name=? ;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;

            try {
                connection.setCatalog("users");
                statement = connection.prepareStatement(query);
                statement.setString(1, userName);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String[] raw = new String[2];
                    raw[0] = rs.getString(1);
                    if (rs.getString(2) != null) {
                        raw[1] = rs.getString(2);
                    }
                    ans.add(raw);
                }
                rs.close();
                statement.close();
                connection.close();
                return ans;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    private List<String[]> getByGameID(String gameID)
    {
        String query = "select * from gamefollwers where gameID=? ;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;

            try {
                connection.setCatalog("users");
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.valueOf(gameID));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String[] raw = new String[2];
                    raw[0] = rs.getString(1);
                    if (rs.getString(2) != null) {
                        raw[1] = rs.getString(2);
                    }
                    ans.add(raw);
                }
                rs.close();
                statement.close();
                connection.close();
                return ans;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    private List<String[]> getByKey(String [] key)
    {
        String query = "select * from gamefollwers where gameID=? and user_name=? ;";
        Connection connection = dBconnector.getConnection();
         if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement statement;

            try {
                connection.setCatalog("users");
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.valueOf(key[1]));
                statement.setString(2, key[2]);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String[] raw = new String[2];
                    raw[0] = rs.getString(1);
                    if (rs.getString(2) != null) {
                        raw[1] = rs.getString(2);
                    }
                    ans.add(raw);
                }
                rs.close();
                statement.close();
                connection.close();
                return ans;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }


    @Override
    public List<String[]> getAll() {
        String query = "select * from gamefollwers;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            List<String[]> ans = new LinkedList<>();
            PreparedStatement preparedStatement;
            try {
                connection.setCatalog("users");
                preparedStatement = connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String[] raw = new String[2];
                    raw[0] = rs.getString(1);
                    if (rs.getString(2) != null) {
                        raw[1] = rs.getString(2);
                    }
                    ans.add(raw);
                }
                rs.close();
                preparedStatement.close();
                connection.close();
                return ans;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }



    @Override
    public void save(String[] params) throws SQLException {
        String query = "insert into gamefollwers(gameID,user_name) values(?,?);";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            PreparedStatement preparedStatement;
            try {
                connection.setCatalog("users");
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,Integer.valueOf(params[0]));
                preparedStatement.setString(2, params[1]);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The username " + params[1] + " successfuly following the gameID " + params[0]);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    @Override
    public void update(String[] params)
    {

    }


    @Override
    public void delete(String[] key) throws SQLException {
        if(key[0].equals("user_name"))
        {
            deleteByUser_name(key[1]);
        }
        else if(key[0].equals("gameID"))
        {
            deleteByGameID(key[1]);
        }
        else if(key[0].equals("key"))
        {
            deleteBykey(key);
        }
    }

    private void deleteByGameID(String gameID) throws SQLException {
        String query = "delete from gamefollwers where gameID=?;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            PreparedStatement preparedStatement;
            try {
                connection.setCatalog("users");
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,Integer.valueOf(gameID));
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The gameID " + gameID + " succesfully delted");
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    private void deleteByUser_name(String user_name) throws SQLException {
        String query = "delete from gamefollwers where user_name=?;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            PreparedStatement preparedStatement;
            try {
                connection.setCatalog("users");
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user_name);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The username " + user_name + " succesfully unfollowing the all his games");
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }

    private void deleteBykey(String[] key) throws SQLException {
        String query = "delete from gamefollwers where gameID=? and user_name=?;";
        Connection connection = dBconnector.getConnection();
        if (connection != null) {
            PreparedStatement preparedStatement;
            try {
                connection.setCatalog("users");
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(key[1]));
                preparedStatement.setString(2, key[2]);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                logger.info("The username " + key[0] + " succesfully unfollowing the game" + key[1]);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new SQLException(DaoSql.getException(e.getMessage()));
            }
        }
    }
}