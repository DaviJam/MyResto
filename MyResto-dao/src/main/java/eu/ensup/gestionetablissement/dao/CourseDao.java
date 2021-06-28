package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.business.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Course dao.
 */
public class CourseDao implements ICourseDao
{
	String className = getClass().getName();
	@Override
	public List<Course> getAll() throws ExceptionDao
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Connection cn = Connect.openConnection();
		List<Course> allCourse = new ArrayList<Course>();
		
		Statement st = null;
		ResultSet res = null;
		try
		{
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Course");
			if(!res.next()){
				throw new ExceptionDao("Aucun cours disponible dans la base de donnée.");
			}
			while( res.next() )
			{
				Course cours = new Course(res.getString("coursesubject"),res.getFloat("nbhours"),res.getInt("id"));
				
				allCourse.add(cours);
			}

			// TODO:  Add logger failed and successfull
			if(allCourse.isEmpty())
			{
				DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les Curs.");
			}

			DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les cours a réussie.");
			st.close();
			cn.close();
		}
		catch (SQLException e) {

			// TODO:  Add logger failed and successfull
			DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
			throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
		}
		finally{
			try {
				st.close();
				cn.close();
			}
			catch(SQLException sqle) {

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",sqle);
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
		}
		
		return allCourse;
	}

	@Override
	public Course get( int index )  throws ExceptionDao
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Connection cn = Connect.openConnection();
		Course cours = null;

		Statement st = null;
		ResultSet res = null;
		try
		{
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Course WHERE id="+index);
			if(!res.next()){
				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant le cours. Ce dernier n'existe pas en base de donnée.");
				throw  new ExceptionDao("Le cours n'existe pas dans la base de donnée.");
			}
			else
			{
				cours = new Course(res.getString("coursesubject"), res.getFloat("nbhours"), res.getInt("id"));

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoInfo(className, methodName, "Les information du cours " + res.getString("coursesubject") + " " + res.getFloat("nbhours") + "  ont été récupérer de la base de donnée.");
			}
		}
		catch (SQLException e) {

			// TODO:  Add logger failed and successfull
			DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
			throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
		}
		finally{
			try {
				st.close();
				cn.close();
			}
			catch(SQLException sqle) {

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",sqle);
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
		}

		return cours;
	}

	@Override
	public int getIndex( String coursesubject, float nbhours ) throws ExceptionDao
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		Connection cn = Connect.openConnection();
		int index = -1;
		
		Statement st = null;
		ResultSet res = null;
		try
		{
			st = cn.createStatement();
			res = st.executeQuery("SELECT id FROM Course WHERE coursesubject='"+coursesubject+"' AND nbhours="+nbhours);
			if(!res.next()){

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoInfo(className, methodName,"Le cours " + coursesubject+" "+ nbhours + " Le cours n'existe pas dans la base de donnée.");
				throw  new ExceptionDao("Le cours n'existe pas dans la base de donnée.");
			}
			else {
				index = res.getInt("id");
			}
		}
		catch (SQLException e) {

			// TODO:  Add logger failed and successfull
			DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
			throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
		}
		finally{
			try {
				st.close();
				cn.close();
			}
			catch(SQLException sqle) {

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",sqle);
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
		}
		
		return index;
	}

	@Override
	public int create( Course course ) throws ExceptionDao
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int res = 1;
		Connection cn = Connect.openConnection();
		PreparedStatement pstmt = null;
		try
		{
			//Vérifie qu'il n'y a pas de double
			if( course.getId() != -1 || getIndex(course.getCourseSubject(), course.getNbHours()) == -1 )
			{
				if(course.getId() != -1)
					pstmt = cn.prepareStatement("INSERT INTO Course (id, coursesubject, nbhours) VALUES ( ?, ?, ? )");
				else
					pstmt = cn.prepareStatement("INSERT INTO Course (coursesubject, nbhours) VALUES ( ?, ? )");
			
				int index = 1;
				if(pstmt.getParameterMetaData().getParameterCount() == 3)
					pstmt.setInt(index++, course.getId());
				
				pstmt.setString(index++, course.getCourseSubject());
				pstmt.setFloat(index++, course.getNbHours());
				
				pstmt.execute();
				DaoLogger.logDaoInfo(className, methodName,"Le cours " + course.getCourseSubject() +" "+course.getNbHours() + " a été créé.");
			}else{

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoInfo(className, methodName,"Le cours " + course.getCourseSubject() +" "+course.getNbHours() +" existe déja dans la base.");
				throw  new ExceptionDao("Ce cours existe déja!");
			}
		}
		catch (SQLException e) {

			// TODO:  Add logger failed and successfull
			DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une personne à la base de donnée.",e);
			throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
		}
		finally{
			try {
				if( pstmt !=  null )
					pstmt.close();
				cn.close();
			}
			catch(SQLException sqle) {

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une personne à la base de donnée.",sqle);
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
			res = 0;
		}
		
		return res;
	}

	@Override
	public int update(Course course) throws ExceptionDao
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int res = 1;
		Course preCourse = get(course.getId());
		String update = "";
		
		if( course.getCourseSubject() != null && ! course.getCourseSubject().equals(preCourse.getCourseSubject()) )
			update += "coursesubject='"+course.getCourseSubject()+"'";
		
		if( course.getNbHours() != -1 && course.getNbHours() != preCourse.getNbHours() )
			update += (update != "" ? "," : "")+"nbhours='"+course.getNbHours()+"'";

		if( update != "" )
		{
			Connection cn = Connect.openConnection();
			Statement st = null;
			try {
				st = cn.createStatement();
				st.execute("UPDATE Course SET "+update+" WHERE id="+course.getId());
				DaoLogger.logDaoInfo(className, methodName,"Les information du cours " + course.getCourseSubject() +" "+course.getNbHours() +" ont bien été modifié.");
			}
			catch( SQLException sqle) {

				// TODO:  Add logger failed and successfull
				DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une personne à la base de donnée.",sqle);
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
			finally {
				try {
					st.close();
					cn.close();
				}
				catch (SQLException throwables) {

					// TODO:  Add logger failed and successfull
					DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une personne à la base de donnée.",throwables);
					throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
				}
				res = 0;
			}
		}
		return res;
	}

	@Override
	public int delete( int index ) throws ExceptionDao {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int res = 1;
		if( index != -1 && indexExist(index) )
		{
			Connection cn = Connect.openConnection();
			
			Statement st = null;
			try
			{
				st = cn.createStatement();
				st.execute("DELETE FROM Course WHERE id="+index);
				DaoLogger.logDaoInfo(className, methodName,"La suppression du cours a réussie.");
			}
			catch (SQLException e) {
				// TODO:  Add logger failed and successfull
				throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
			}
			finally{
				try {
					st.close();
					cn.close();
				}
				catch(SQLException sqle) {
					// TODO:  Add logger failed and successfull
					throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");}
				res = 0;
			}
		}
		if (res != 0) {
			DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression du cours. Ce dernier n'existe pas dans la base de donnée.");
		}
		
		return res;
	}

	@Override
	public int delete( Course course ) throws ExceptionDao
	{
		return delete(course.getId());
	}
	@Override
	public boolean indexExist(int index) throws ExceptionDao {
		boolean existe = false;
		
		List<Course> alCourse = getAll();
		for( Course cours : alCourse )
			if( index == cours.getId() )
				existe = true;
		
		return existe;
	}
}
