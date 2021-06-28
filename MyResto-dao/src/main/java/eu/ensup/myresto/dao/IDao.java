package eu.ensup.myresto.dao;

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
     * Delete int.
     *
     * @param entity the entity
     * @return the int
     * @throws ExceptionDao the exception dao
     */
    int delete(T entity) throws ExceptionDao;
}
