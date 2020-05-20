package main.Demo;

import main.DB.FansDaoSql;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lo4jDemo {

  private static Logger logger = LogManager.getLogger(Lo4jDemo.class);

    public static Logger getInstance()
    {
        return logger;
    }

}
