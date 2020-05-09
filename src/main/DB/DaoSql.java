package main.DB;
import java.sql.SQLException;
import java.util.*;
public interface DaoSql
{

    Optional<String[]> get(String[] key );
    List<String[]> getAll();
    void save(String[] params) throws SQLException;
    void update(String[] params);
    void delete(String []key);

}
