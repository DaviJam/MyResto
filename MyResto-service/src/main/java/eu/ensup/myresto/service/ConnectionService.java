package eu.ensup.myresto.service;

import static eu.ensup.myresto.service.IService.serviceLogger;

import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.ILoginDao;
import eu.ensup.myresto.dao.LoginDao;

/**
 * The type Service connection.
 */
public class ConnectionService implements IConnectionService {
    private ILoginDao dao;
    // nom de la classe
    String className = getClass().getName();
    
    public ConnectionService()
    {
    	this.dao = new LoginDao();
    }
    
    public ConnectionService(ILoginDao idao)
    {
    	this.dao = idao;
    }
    
    @Override
    public int checkConnection(String mail, String mdp) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int index;
        try {
            index = this.dao.checkPassword(mail, mdp); // Récupération du MDP et comparaison avec le mdp saisi
            serviceLogger.logServiceInfo(className, methodName,"Le mot de passe utilisateur "+mail+" est correct.");
        } catch (ExceptionDao e){
            serviceLogger.logServiceError(className, methodName,"Le mot de passe utilisateur est incorrect.");
            throw new ExceptionService(e.getMessage());
        } finally {

        }
        return index;
    }
}
