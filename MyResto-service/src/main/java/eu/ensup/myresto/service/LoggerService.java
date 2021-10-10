package eu.ensup.myresto.service;


import eu.ensup.myresto.logger.LoggerHandler;
import org.apache.log4j.Logger;

public class LoggerService extends LoggerHandler {
    public LoggerService(){
        super();
    }

    public void logServiceError(String className, String methodName, String message) {
        Logger.getLogger(IService.class.getSimpleName()).error("SERVICE Error: "+ className + " " + methodName + " - " + message);
    }

    public void logServiceInfo(String className, String methodName, String message) {
        Logger.getLogger(IService.class.getSimpleName()).info("SERVICE Info: "+ className + " " + methodName + " - " + message);
    }
}
