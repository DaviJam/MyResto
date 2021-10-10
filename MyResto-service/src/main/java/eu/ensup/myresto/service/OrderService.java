package eu.ensup.myresto.service;

import eu.ensup.myresto.business.Order;
import eu.ensup.myresto.business.Status;
import eu.ensup.myresto.business.User;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.IDao;
import eu.ensup.myresto.dao.OrderDao;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.dto.StatusDTO;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.mapper.OrderMapper;
import eu.ensup.myresto.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Service order.
 */
public class OrderService implements IService<OrderDTO>{

    private OrderDao dao = null;

    /**
     * The Class name.
     */
// nom de la classe
    String className = getClass().getName();

    public OrderService(OrderDao orderdao){ this.dao = orderdao; }
    public OrderService() { this.dao = new OrderDao(); }


    @Override
    public List<OrderDTO> getAll() throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<OrderDTO> listOrderDTO = new ArrayList<OrderDTO>();

        try {
            for (Order o : this.dao.getAll())
                listOrderDTO.add(OrderMapper.businessToDto(o));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionDao);
            throw new ExceptionService(exceptionDao.getMessage());
        }

        return listOrderDTO;
    }

    @Override
    public OrderDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        OrderDTO orderDTO;
        try {
            orderDTO = OrderMapper.businessToDto(this.dao.get(index));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return orderDTO;
    }

    @Override
    public int create(OrderDTO orderDTO) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res;
        try {
            res = this.dao.create(OrderMapper.dtoToBusiness(orderDTO));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    @Override
    public int update(OrderDTO entity) throws ExceptionService {
        return 0;
    }

    @Override
    public int delete(OrderDTO orderDTO) throws ExceptionService {
        return delete(orderDTO.getId());
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

    public Boolean update(int index, StatusDTO status) throws ExceptionService{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            return this.dao.update(index, Status.getStatusByNum(status.getNum()));
        } catch (ExceptionDao exceptionDao) {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
    }
}
