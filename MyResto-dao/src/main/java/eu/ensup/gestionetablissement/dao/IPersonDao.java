package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.business.Person;

/**
 * The interface School dao.
 */
public interface IPersonDao extends IDao<Person>
{
	/**
	 * Get the index of the school by this name
	 *
	 * @param idEtudiant the id etudiant
	 * @param idCourse   the id course
	 * @return type of return
	 * @throws ExceptionDao the exception dao
	 */
	public int linkToCourse( int idEtudiant, int idCourse ) throws ExceptionDao;


	/**
	 * delete a person by its email
	 * @param email
	 * @return
	 * @throws ExceptionDao
	 */
	int delete(String email) throws ExceptionDao;
}
