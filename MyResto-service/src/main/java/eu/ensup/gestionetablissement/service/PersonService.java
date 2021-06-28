package eu.ensup.gestionetablissement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.ensup.gestionetablissement.business.Director;
import eu.ensup.gestionetablissement.business.Manager;
import eu.ensup.gestionetablissement.business.Mark;
import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.business.Student;
import eu.ensup.gestionetablissement.business.Teacher;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.IPersonDao;
import eu.ensup.gestionetablissement.dao.MarkDao;
import eu.ensup.gestionetablissement.dao.PersonDao;
import eu.ensup.gestionetablissement.dto.DirectorDTO;
import eu.ensup.gestionetablissement.dto.ManagerDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.dto.TeacherDTO;
import eu.ensup.gestionetablissement.mapper.DirectorMapper;
import eu.ensup.gestionetablissement.mapper.ManagerMapper;
import eu.ensup.gestionetablissement.mapper.StudentMapper;
import eu.ensup.gestionetablissement.mapper.TeacherMapper;

/**
 * The type Service person.
 */
public class PersonService implements IPersonService {

    private IPersonDao dao = null;

    /**
     * The Class name.
     */
// nom de la classe
    String className = getClass().getName();

    /**
     * Instantiates a new Service person.
     */
    public PersonService() {
        this.dao = new PersonDao();
    }

    /**
     * Instantiates a new Person service.
     *
     * @param idao the idao
     */
    public PersonService(IPersonDao idao) {
        this.dao = idao;
    }

    @Override
    public int create(PersonDTO person) throws ExceptionService {

        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        // Checker le role et faire une instace et l'envoyer dans le DAO
        int check = 0;
        switch(person.getRole().getNum()){
            case 1: // Director
                Person director = DirectorMapper.dtoToBusiness((DirectorDTO)person);
                try {
                    check = this.dao.create(director);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 2: // Manager
                Person manager = ManagerMapper.dtoToBusiness((ManagerDTO)person);
                try{
                    check = this.dao.create(manager);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 3: // Teacher
                // On instancie Personne pour que dans le DAO il puisse récupérer le matière enseignée
                Person teacher = TeacherMapper.dtoToBusiness((TeacherDTO)person);
                try {
                    check = this.dao.create(teacher);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 4: // Student
                // On instancie Personne pour que dans le DAO il puisse récupérer la date de naissance
                Person student = StudentMapper.dtoToBusiness((StudentDTO)person);
                try{
                    check = this.dao.create(student);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
        }
        return check;
    }

    // Update Person
    @Override
    public int update(PersonDTO person) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        switch(person.getRole().getNum()){
            case 1: // Director
                Person director = DirectorMapper.dtoToBusiness((DirectorDTO)person);
                try{
                res = this.dao.update(director);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                   throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 2: // Manager
                Person manager = ManagerMapper.dtoToBusiness((ManagerDTO)person);
                try{
                res = this.dao.update(manager);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 3: // Teacher
                Person teacher = TeacherMapper.dtoToBusiness((TeacherDTO)person);
                try{
                res = this.dao.update(teacher);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
            case 4: // Student
                Person student = StudentMapper.dtoToBusiness((StudentDTO)person);
                try{
                res = this.dao.update(student);
                }catch (ExceptionDao exceptionDao){
                    serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
                    throw new ExceptionService(exceptionDao.getMessage());
                }
                break;
        }
        return res;
    }

    @Override
    public int delete(PersonDTO entity) throws ExceptionService {
        return 0;
    }

    @Override
    public int delete(String email) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        try{
            res = this.dao.delete(email);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        try{
         res = this.dao.delete(index);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    /**
     * Link to course int.
     *
     * @param idEtudiant the id etudiant
     * @param idCourse   the id course
     * @return the int
     * @throws ExceptionService the exception service
     */
    public int linkToCourse(int idEtudiant, int idCourse) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            int res = this.dao.linkToCourse(idEtudiant, idCourse);
            return res;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    @Override
    public PersonDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
        Person person = this.dao.get(index);
        PersonDTO personDTO = new PersonDTO();
        if(person instanceof Student)
        {
            personDTO = StudentMapper.businessToDto((Student)person);
            ((StudentDTO)personDTO).setAverage(this.getAverage(index));
        }else if(person instanceof Manager)
        {
            personDTO = ManagerMapper.businessToDto((Manager)person);
        }else if(person instanceof Teacher)
        {
            personDTO = TeacherMapper.businessToDto((Teacher)person);
        }else if(person instanceof Director)
        {
            personDTO = DirectorMapper.businessToDto((Director)person);
        }

        return personDTO;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
           throw new ExceptionService(exceptionDao.getMessage());
        }
    }


    /**
     * Get person dto.
     *
     * @param email the email
     * @return the person dto
     * @throws ExceptionService the exception service
     */
    public PersonDTO get(String email) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            Person person = this.dao.get(email);
            PersonDTO personDTO = new PersonDTO();
            if(person instanceof Student)
            {
                personDTO = StudentMapper.businessToDto((Student)person);
                ((StudentDTO)personDTO).setAverage(this.getAverage(person.getId()));
            }else if(person instanceof Manager)
            {
                personDTO = ManagerMapper.businessToDto((Manager)person);
            }else if(person instanceof Teacher)
            {
                personDTO = TeacherMapper.businessToDto((Teacher)person);
            }else if(person instanceof Director)
            {
                personDTO = DirectorMapper.businessToDto((Director)person);
            }

            return personDTO;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    @Override
    public List<PersonDTO> getAll() throws ExceptionService{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();
        try {
        	List<Person> listAllPerson = this.dao.getAll();
        	for(Person person : listAllPerson)
            {
                if (person instanceof Student) {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO = StudentMapper.businessToDto((Student) person);
                    studentDTO.setAverage(getAverage(studentDTO.getId()));
                    personDTOList.add(studentDTO);
                } else if (person instanceof Manager) {
                    ManagerDTO managerDTO = new ManagerDTO();
                    managerDTO = ManagerMapper.businessToDto((Manager) person);
                    personDTOList.add(managerDTO);
                } else if (person instanceof Teacher) {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO = TeacherMapper.businessToDto((Teacher) person);
                    personDTOList.add(teacherDTO);
                } else if (person instanceof Director) {
                    DirectorDTO directorDTO = new DirectorDTO();
                    directorDTO = DirectorMapper.businessToDto((Director) person);
                    personDTOList.add(directorDTO);
                }
            }
            return personDTOList;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    /**
     * Gets average.
     *
     * @param index the index
     * @return the average
     * @throws ExceptionService the exception service
     */
    public float getAverage(int index) throws ExceptionService
    {
    	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
    	Float average = 0f;
    	
    	List<Mark> listMark;
		try {
			listMark = (new MarkDao()).getAllMarkByStudentId(index);
	    	
	    	for( Mark mark : listMark )
	    	{
	    		average += mark.getMark();
	    	}
	    	
	    	average = average / listMark.size();
		}
		catch (ExceptionDao exceptionDao) {
			serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
		}
    	
    	return average;
    }
}
