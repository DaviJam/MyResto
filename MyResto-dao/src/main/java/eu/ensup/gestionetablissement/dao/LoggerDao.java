package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.logger.LoggerHandler;
import org.apache.log4j.Logger;

import java.util.Optional;

public class LoggerDao extends LoggerHandler {
    public LoggerDao(){
        super();
    }

    public void logDaoError(String className, String methodName, String message) {
        Logger.getLogger(IDao.class.getPackageName()).error("DAO Error: "+ className + " " + methodName + " - " + message);
    }

    public void logDaoError(String className, String methodName, String message, Exception exec) {
        Logger.getLogger(IDao.class.getPackageName()).error("DAO Error: "+ className + " " + methodName + " - " + message + ". See exception : " +exec.getMessage());
    }

    public void logDaoError(String message) {
        Logger.getLogger(IDao.class.getPackageName()).error("DAO Error: "+message);
    }

    public void logDaoInfo(String className, String methodName, String message) {
        Logger.getLogger(IDao.class.getPackageName()).info("DAO Info: "+ className + " " + methodName + " - " + message);
    }
}
