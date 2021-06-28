package eu.ensup.gestionetablissement.service;


import eu.ensup.gestionetablissement.logger.LoggerHandler;
import eu.ensup.gestionetablissement.service.IService;
import org.apache.log4j.Logger;

public class LoggerService extends LoggerHandler {
    public LoggerService(){
        super();
    }

    public void logServiceError(String className, String methodName, String message) {
        Logger.getLogger(IService.class.getPackageName()).error("SERVICE Error: "+ className + " " + methodName + " - " + message);
    }

    public void logServiceInfo(String className, String methodName, String message) {
        Logger.getLogger(IService.class.getPackageName()).info("SERVICE Info: "+ className + " " + methodName + " - " + message);
    }
}
