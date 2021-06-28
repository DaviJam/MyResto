package eu.ensup.gestionetablissement.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static eu.ensup.gestionetablissement.dao.Connect.openConnection;
import static eu.ensup.gestionetablissement.dao.IDao.DaoLogger;


/**
 * The type Dao login.
 */
public class LoginDao implements ILoginDao {
    /**
     * The Connection.
     */
    Connection cn = null;

    /**
     * The Prepared Statement.
     */
    PreparedStatement st = null;

    /**
     * The Result Set.
     */
    ResultSet rs = null;


    String className = getClass().getName();
    /**
     * The update, create and remove result.
     */
    int res = 0;

    /**
     * Gets password.
     *
     * @param mail     the mail
     * @param password the password
     * @return the password
     */
    public int checkPassword(String mail, String password) throws ExceptionDao {
        int id = 0;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * Crer la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT id FROM Person WHERE email = ? AND password = ? AND (role = 1 OR role = 2)";
            st = cn.prepareStatement(sql_request);
            st.setString(1, mail);
            st.setString(2, password);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();


            if (rs.next()) {
                id = rs.getInt("id");
                DaoLogger.logDaoInfo(className, methodName,"L'utilisateur " + mail +  " authentifié");
            } else {
                DaoLogger.logDaoError(className, methodName,mail + " : Identifiant ou mot de passe incorrect.");
                throw new ExceptionDao("Identifiant ou mot de passe incorrect.");
            }

        } catch (SQLException throwables) {
            DaoLogger.logDaoError(className, methodName,"Une erreur est survenue lors de la vérification du mot de passe de l'utilisateur.",throwables);
            throw new ExceptionDao("Une erreur est survenue lors de la vérification du mot de passe de l'utilisateur.");
        }
        return id;
    }
}
