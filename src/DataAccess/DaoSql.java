package DataAccess;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import Logger.*;
public interface DaoSql
{
    Logger logger= Lo4jDemo.getInstance();
    static String getException(String message)
    {
        if(message.contains("foreign key"))
            return "object not found";
        else
            return "wrong parameters";
    }

    List<String[]> get(String[] key );
    List<String[]> getAll();
    void save(String[] params) throws SQLException, ParseException;
    void update(String[] params) throws SQLException;
    void delete(String []key)throws SQLException;


}
