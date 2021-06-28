package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductDaoTest {

    ProductDao productDao = new ProductDao();

    @BeforeEach
    public void setUp() {
        try {
            Connect.openConnection();
        } catch (ExceptionDao exceptionDao) {
        }
    }

    @Test
    @DisplayName("Create a product and check if it exist")
    @Tag("ProductDaoTest")
    public void createProduct() throws ExceptionDao {
        Product p =  new Product();
        p.setName("cheeseburger");
        p.setDescription("pain à burger, cheddar");
        p.setPrice(15);
        p.setAllergen("sésame");
        p.setCategory(Category.PLAT);
        productDao.create(p);
        Product p1 = productDao.get(1);
        assertNotNull(p1);
        productDao.delete(p1.getId());
    }

    @Test
    @DisplayName("update a product and check if it exist")
    @Tag("ProductDaoTest")
    public void updateProduct() throws ExceptionDao {
        Product p =  new Product();
        p.setName("cheeseburger");
        p.setDescription("pain à burger, cheddar");
        p.setPrice(15);
        p.setAllergen("sésame");
        p.setCategory(Category.PLAT);
        // TODO : execute update and check if it was corretly executed
    }



}
