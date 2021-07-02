package eu.ensup.myresto.service;

import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.ProductDao;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<ProductDTO> {

    private ProductDao productDao;


    String className = getClass().getName();
    public ProductService() {
        this.productDao = new ProductDao();
    }
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public int create(ProductDTO productDTO) throws ExceptionService {

        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        // Checker le role et faire une instance et l'envoyer dans le DAO
        int check = 0;

        Product product = ProductMapper.dtoToBusiness(productDTO);
        try {
            check = this.productDao.create(product);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return check;
    }

    // Update Person
    @Override
    public int update(ProductDTO productDTO) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        Product product = ProductMapper.dtoToBusiness(productDTO);
        try{
            res = this.productDao.update(product);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    @Override
    public int delete(ProductDTO productDTO) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        Product product = ProductMapper.dtoToBusiness(productDTO);
        try{
            res = this.productDao.delete(product);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }


    @Override
    public int delete(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 0;
        try{
            res = this.productDao.delete(index);
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
        return res;
    }

    @Override
    public ProductDTO get(int index) throws ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            Product product = this.productDao.get(index);
            ProductDTO productDTO = ProductMapper.businessToDto(product);
            return productDTO;
        }catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }
    }

    @Override
    public List<ProductDTO> getAll() throws ExceptionService{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            List<Product> listAllPerson = this.productDao.getAll();
            for(Product product : listAllPerson)
            {
                ProductDTO productDTO = ProductMapper.businessToDto(product);
                productDTOList.add(productDTO);
            }
            return productDTOList;
        } catch (ExceptionDao exceptionDao){
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(exceptionDao.getMessage());
        }

    }

    public Boolean updateStock(int id_product, int quantity) throws ExceptionDao, ExceptionService {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try
        {
            return this.productDao.updateStock(id_product, quantity);
        }
        catch(ExceptionDao e)
        {
            serviceLogger.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            throw new ExceptionService(e.getMessage());
        }
    }
}
