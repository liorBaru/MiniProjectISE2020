package main.DB;

import java.sql.Connection;

public class DBconnector
{
    private static DBconnector dBconnector;
    private String connectionString;
    private String databaseName;
    private String username;
    private String password;


    public static DBconnector getInstance()
    {
        return dBconnector;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public  Connection getConnection()
    {
        return null;
    }
}
