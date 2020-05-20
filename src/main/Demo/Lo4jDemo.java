package main.Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lo4jDemo {

  private static Logger logger = LogManager.getLogger(Lo4jDemo.class);

    public static void main(String[] args)
    {
        System.out.printf("\n Hello word ...! \n");

        logger.info("This is information message test");
        logger.error("This is an error message test");
        logger.warn("This is an fatal message");

        System.out.println("\n Completed");
    }
}
