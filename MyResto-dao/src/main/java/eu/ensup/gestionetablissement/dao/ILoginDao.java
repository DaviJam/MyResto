package eu.ensup.gestionetablissement.dao;

/**
 * The type Dao login.
 */
public interface ILoginDao
{
    /**
     * Gets password.
     *
     * @param mail     the mail
     * @param password the password
     * @return the password
     */
    public int checkPassword(String mail, String password) throws ExceptionDao;
}
