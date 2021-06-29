package eu.ensup.myresto.service;

import java.util.ArrayList;
import java.util.List;

import eu.ensup.myresto.business.User;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.UserDao;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.mapper.UserMapper;

/**
 * The type Service person.
 */
public class UserService implements IService<UserDTO> {

    private UserDao dao = null;

    /**
     * The Class name.
     */
// nom de la classe
    String className = getClass().getName();

    /**
     * Instantiates a new Service person.
     */
    public UserService() {
        this.dao = new UserDao();
    }

    /**
     * Instantiates a new Person service.
     *
     * @param idao the idao
     */
    public UserService(UserDao idao) {
        this.dao = idao;
    }

    @Override
    public int create(UserDTO userDto) throws ExceptionService {

        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        // Checker le role et faire une instance et l'envoyer dans le DAO
        int check = 0;

        User user = UserMapper.dtoToBusiness(userDto);
        try {
            check = this.dao.create(user);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return check;
    }

    // Update Person
    @Override
    public int update(UserDTO userDto) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        User user = UserMapper.dtoToBusiness(userDto);
        try{
        res = this.dao.update(user);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
           throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    @Override
    public int delete(UserDTO userDto) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        User user = UserMapper.dtoToBusiness(userDto);
        try{
            res = this.dao.delete(user);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

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

    @Override
    public UserDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
        User user = this.dao.get(index);
        UserDTO userDto = null;

        if(user != null)
            userDto = UserMapper.businessToDto(user);

        return userDto;
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
    public UserDTO get(String email) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            User user = this.dao.get(email);
            UserDTO personDto = null;
            personDto = UserMapper.businessToDto(user);

            return personDto;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    @Override
    public List<UserDTO> getAll() throws ExceptionService{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
        	List<User> listAllPerson = this.dao.getAll();
        	for(User user : listAllPerson)
            {
                UserDTO userDto = null;
                userDto = UserMapper.businessToDto(user);
                userDTOList.add(userDto);
            }
            return userDTOList;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }
}
