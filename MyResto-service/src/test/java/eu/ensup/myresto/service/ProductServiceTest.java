package eu.ensup.myresto.service;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.ProductDao;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    LoggerService productServiceLogger = new LoggerService();
    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductService productService = new ProductService();

    @Test
    public void getProductOk() {
        try {
            Product product = new Product(4,"triple cheese burger", "trois steak trois tranche de chedar", 16, "sésame", "https://via.placeholder.com/150", 1, Category.BURGER);
            ProductDTO productDTO1 = ProductMapper.businessToDto(product);
            when(productDao.get(4)).thenReturn(product);

            ProductDTO productDTO = productService.get(4);

            assertEquals(productDTO.getId(), productDTO1.getId());
            Mockito.verify(productDao, Mockito.atLeast(1)).get(4);
        } catch (ExceptionService es) {
            productServiceLogger.logServiceError(es.getClass().getName(), "getProductOk", "Une erreur survenue lors de récupération du produit.");
        } catch (ExceptionDao ed) {
            productServiceLogger.logServiceError(ed.getClass().getName(), "getProductOk", "Une erreur survenue lors de récupération du produit.");
        }

    }


    @Test
    public void createProductOk() {
        try {
            ProductDTO productDTO = new ProductDTO(4,"triple cheese burger", "trois steak trois tranche de chedar", 16, "sésame", "https://via.placeholder.com/150", 1, Category.MENU);
            when(productDao.create(any(Product.class))).thenReturn(1);
            int res = productService.create(productDTO);
            assertEquals(res, 1);
            Mockito.verify(productDao, Mockito.atLeast(1)).create(any(Product.class));
        } catch (ExceptionDao ed) {
            productServiceLogger.logServiceError(ed.getClass().getName(), "createProductOk", "Une erreur survenue lors de la création du produit.");
        } catch (ExceptionService es) {
            productServiceLogger.logServiceError(es.getClass().getName(), "createProductOk", "Une erreur survenue lors de la création du produit.");
        }
    }

    @Test
    public void UpdateProductOk() {
        try {
            ProductDTO productDTO = new ProductDTO(4,"triple cheese burger", "trois steak trois tranche de chedar", 16, "sésame", "https://via.placeholder.com/150", 1, Category.MENU);

            when(productDao.update(any(Product.class))).thenReturn(1);

            int ret = productService.update(productDTO);
            assertEquals(ret, 1);
            Mockito.verify(productDao, Mockito.atLeast(1)).update(any(Product.class));
        } catch (ExceptionDao ed) {
            productServiceLogger.logServiceError(ed.getClass().getName(), "updateProductOk", "Une erreur survenue lors de la modification du produit.");
        } catch (ExceptionService es) {
            productServiceLogger.logServiceError(es.getClass().getName(), "updateProductOk", "Une erreur survenue lors de la modification du produit.");

        }
    }

    @Test
    public void DeleteProductOk()  {
        try {
            when(productDao.delete(1)).thenReturn(1);
            int ret = productService.delete(1);
            assertEquals(ret, 1);
            Mockito.verify(productDao, Mockito.atLeast(1)).delete(1);
        } catch (ExceptionDao ed) {
            productServiceLogger.logServiceError(ed.getClass().getName(), "DeleteProductOk", "Une erreur survenue lors de la suppression du produit.");
        } catch (ExceptionService es) {
            productServiceLogger.logServiceError(es.getClass().getName(), "DeleteProductOk", "Une erreur survenue lors de la suppression du produit.");

        }
    }

}
