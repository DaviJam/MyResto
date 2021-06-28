package eu.ensup.gestionetablissement.service;

import java.util.ArrayList;
import java.util.List;

import eu.ensup.gestionetablissement.business.Mark;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.IMarkDao;
import eu.ensup.gestionetablissement.dao.MarkDao;
import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.mapper.MarkMapper;

/**
 * The type Mark service.
 */
public class MarkService implements IMarkService {
    
    private IMarkDao dao;    // nom de la classe
    /**
     * The Class name.
     */
    String className = getClass().getName();

    /**
     * Instantiates a new Mark service.
     */
    public MarkService() {
        this.dao = new MarkDao();
    }

    public MarkService(IMarkDao idao) {
        this.dao = idao;
    }

    public List<MarkDTO> getAll() throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<MarkDTO> listMarkDto = new ArrayList<MarkDTO>();

        try {
            for (Mark c : this.dao.getAll())
                listMarkDto.add(MarkMapper.businessToDto(c));
        } catch (ExceptionDao exceptionDao) {
            throw new ExceptionService(exceptionDao.getMessage());
        }

        return listMarkDto;
    }

    public List<MarkDTO> getAllById(int id) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<MarkDTO> listMarkDto = new ArrayList<>();

        try {
            for (Mark c : this.dao.getAllMarkByStudentId(id))
                listMarkDto.add(MarkMapper.businessToDto(c));
        } catch (ExceptionDao exceptionDao) {
            throw new ExceptionService(exceptionDao.getMessage());
        }

        return listMarkDto;
    }

    public MarkDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        MarkDTO markDTO;
        try {
            markDTO = MarkMapper.businessToDto(this.dao.get(index));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return markDTO;
    }

    public int create(MarkDTO courseDto) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res;
        try {
            res = this.dao.create(MarkMapper.dtoToBusiness(courseDto));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    public int update(MarkDTO courseDto) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Mark course = MarkMapper.dtoToBusiness(courseDto);
        course.setId(courseDto.getId());
        int ret;
        try {
            ret = this.dao.update(course);
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return ret;
    }

    public int delete(MarkDTO courseDto) throws ExceptionService {
        return delete(courseDto.getId());
    }

    public int delete(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int ret;
        try {
            ret = this.dao.delete(index);
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return ret;
    }
}
