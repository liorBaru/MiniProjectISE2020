package main.DB;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
        String query = "Select * from complaint where id=?;";
        Connection connection=dBconnector.getConnection();
        List<String[]> ans = new LinkedList<>();
        if(connection!=null)
        {
            PreparedStatement statement;
            try
            {
                connection.setCatalog("events");
                statement=connection.prepareStatement(query);
                statement.setInt(1,Integer.valueOf(key[0]));
                ResultSet resultSet =statement.executeQuery();
                while (resultSet.next())
                {
                    String row[] = new String [7];
                    row[0]=String.valueOf(resultSet.getInt(1));
                    row[1]=resultSet.getString(2);
                    row[2]=String.valueOf(resultSet.getBoolean(3));
                    row[3]=resultSet.getString(4);
                    if(resultSet.getString(5)!=null)
                    {
                        row[4]=resultSet.getString(5);
                    }
                    else
                    {
                        row[4]="";
                    }
                    SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    if(resultSet.getDate(6)!=null)
                    {
                        Date dateC = resultSet.getDate(6);
                        row[5]=datetimeFormatter.format(dateC);
                    }
                    else
                    {
                        row[6]="";
                    }
                    if(resultSet.getDate(7)!=null)
                    {
                        Date dateA = resultSet.getDate(7);
                        row[6]=datetimeFormatter.format(dateA);
                    }
                    else
                    {
                        row[6]="";
                    }
                    ans.add(row);
                }
                resultSet.close();
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
        String query = "select id,user_name,details from complaint where status=false;";
        Connection connection=dBconnector.getConnection();
        List<String[]> ans = new LinkedList<>();
        if(connection!=null)
        {
            Statement stmt = null;
            try {
                connection.setCatalog("events");
                stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next())
                {
                    String [] row = new String[3];
                    row[0]=String.valueOf(resultSet.getInt(1));
                    row[1]=resultSet.getString(2);
                    row[2]= resultSet.getString(3);
                    ans.add(row);
                }
                resultSet.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return ans;
    }

    @Override
    public void save(String[] params) throws SQLException
    {
        String query = "INSERT INTO complaint (id,user_name,status,details,create_time) values(?,?,?,?,?);";
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
                stmt.setString(4,params[3]);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = simpleDateFormat.parse(params[4]);
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                stmt.setDate(5,sqlStartDate);
                stmt.execute();
                stmt.close();
                conn.close();
                logger.info("Complaint id " +params[0] + " Succsefully saved");
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
        String query = "Update complaint set status=?,answer=?,answer_time=? where id=?;";
        Connection connection=dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement statement;
            try
            {

                connection.setCatalog("events");
                statement=connection.prepareStatement(query);
                boolean bool =Boolean.valueOf(params[0]);
                statement.setBoolean(1,bool);
                statement.setString(2,params[1]);
                SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = datetimeFormatter.parse(params[2]);
                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                statement.setDate(3,sqlDate);
                int x= Integer.valueOf(params[3]);
                statement.setInt(4,x);
                statement.execute();
                statement.close();
                connection.close();
                logger.info("Complaint id " +params[0] + " Succsefully updated");
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
        String query = "Delete from complaint where id=?;";
        Connection connection = dBconnector.getConnection();
        if(connection!=null)
        {
            PreparedStatement statement;
            try
            {
                connection.setCatalog("events");
                statement=connection.prepareStatement(query);
                int id=Integer.valueOf(key[0]);
                statement.setInt(1,id);
                statement.execute();
                statement.close();
                connection.close();
                logger.info("Complaint id Succsefully delted" );

            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
