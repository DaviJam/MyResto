package eu.ensup.gestionetablissement.dao;

public class ExceptionDao extends Exception{
    private LoggerDao daoLogger = new LoggerDao();

    public ExceptionDao(){
        super();
    }
    public ExceptionDao(String s){
        super(s);
        daoLogger.logDaoError(s);
    }
}
