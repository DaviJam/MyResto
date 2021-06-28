package eu.ensup.gestionetablissement.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHandler {

    public LoggerHandler()
    {
        PropertyConfigurator.configure(this.getClass().getResource("/log4j.properties"));
    }

}