package eu.ensup.gestionetablissement.dao;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface IDao<T>
{
    /**
     * The constant DaoLogger.
     */
    final LoggerDao DaoLogger = new LoggerDao();

    /**
     * list all T of the database.
     *
     * @return list of all T
     * @throws ExceptionDao the exception dao
     */
    List<T> getAll() throws ExceptionDao;

    /**
     * Get an T in the database.
     *
     * @param index index of the T to be get
     * @return the class of type T
     * @throws ExceptionDao the exception dao
     */
    T get(int index) throws ExceptionDao;

    /**
     * Get t.
     *
     * @param email the email
     * @return the t
     * @throws ExceptionDao the exception dao
     */
    default T get(String email) throws ExceptionDao
    {
        return  null;
    }

    /**
     * Create an T in the database.
     *
     * @param entity T to be created
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    int create(T entity) throws ExceptionDao;

    /**
     * Update an T of the database.
     *
     * @param entity T to be updated
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    int update(T entity) throws ExceptionDao;

    /**
     * delete an T in the database.
     *
     * @param entity T to be deleted
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    default int delete(T entity) throws ExceptionDao {
        //TODO : to delete
        return 0;
    }

    /**
     * Delete int.
     *
     * @param index the index
     * @return the int
     * @throws ExceptionDao the exception dao
     */
    int delete(int index) throws ExceptionDao;
}
