package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.business.Mark;

import java.util.List;

/**
 * The interface Course dao.
 */
public interface IMarkDao extends IDao<Mark>
{
	/**
	 * Know if the index exist or not in the table Course
	 *
	 * @param index index of the course
	 * @return if the index exist
	 * @throws ExceptionDao the exception dao
	 */
	public boolean indexExist(int index) throws ExceptionDao;

	/**
	 * Gets all mark by student id.
	 *
	 * @param index the index
	 * @return the all mark by student id
	 */
	public List<Mark> getAllMarkByStudentId(int index ) throws ExceptionDao;
}
