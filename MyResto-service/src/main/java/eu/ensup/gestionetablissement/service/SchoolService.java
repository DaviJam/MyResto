package eu.ensup.gestionetablissement.service;

import java.util.ArrayList;
import java.util.List;

import eu.ensup.gestionetablissement.business.School;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.ISchoolDao;
import eu.ensup.gestionetablissement.dao.SchoolDao;
import eu.ensup.gestionetablissement.dto.SchoolDTO;
import eu.ensup.gestionetablissement.mapper.SchoolMapper;

/**
 * The type School service.
 */
public class SchoolService implements IService<SchoolDTO> {
	private ISchoolDao dao;
	// nom de la classe
	String className = getClass().getName();
	
	/**
	 * Instantiates a new School service.
	 */
	public SchoolService()
	{
		this.dao = new SchoolDao();
	}
	
	public SchoolService(ISchoolDao idao)
	{
		this.dao = idao;
	}

	public List<SchoolDTO> getAll() throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		List<SchoolDTO> listSchoolDto = new ArrayList<SchoolDTO>();

		try{
			for (School school: this.dao.getAll())
				listSchoolDto.add(SchoolMapper.businessToDto(school));
			if(listSchoolDto.isEmpty()) {
				serviceLogger.logServiceError(className, methodName,"La liste de cours est vide.");
			}
		}catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}
		return listSchoolDto;
	}

	public SchoolDTO get(int index) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		try{
			return SchoolMapper.businessToDto(this.dao.get(index));
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}
	}

	public int create(SchoolDTO schoolDto) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		try{
			return this.dao.create(SchoolMapper.dtoToBusiness(schoolDto));
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}

	}

	public int update(SchoolDTO schoolDto) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		School school = SchoolMapper.dtoToBusiness(schoolDto);
		school.setId(schoolDto.getId());
		try{
			return this.dao.update(school);
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}

	}

	public int delete(SchoolDTO schoolDto) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		try{
			return this.dao.delete(schoolDto.getId());
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}



	}
	
	public int delete(int index) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		try
		{
			return this.dao.delete(index);
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}
	}

	/**
	 * Gets index.
	 *
	 * @param surname the surname
	 * @return the index
	 */
	public int getIndex( String surname ) throws ExceptionService
	{
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		try
		{
			return this.dao.getIndex(surname);
		} catch(ExceptionDao e){
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
			throw new ExceptionService(e.getMessage());
		}

	}
}
