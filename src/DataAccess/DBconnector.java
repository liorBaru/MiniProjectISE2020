package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.Logger;
import Logger.*;
public class DBconnector {

    private static final DBconnector instance = new DBconnector();

    private static Logger logger= Lo4jDemo.getInstance();
    public static DBconnector getInstance()
    {
        return instance;
    }

    private DBconnector()
    {

    }

    /**
     *  Get a connection to database
     *
     * @return Connection object
     */

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://132.72.65.129:3306", "root", "123456");
            return conn;
        } catch(Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}