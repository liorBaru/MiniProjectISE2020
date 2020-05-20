package main.DB;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.*;
public interface DaoSql
{
    Logger logger= main.Demo.Lo4jDemo.getInstance();
    List<String[]> get(String[] key );
    List<String[]> getAll();
    void save(String[] params) throws SQLException;
    void update(String[] params);
    void delete(String []key);

}
