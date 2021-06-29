package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class ProductDaoTest
{
    ProductDao daoProduct = new ProductDao();
    Product p =  null;

    @Before
    public void SetUp() throws ExceptionDao {
        Connect.openConnection();
        p = new Product();
        p.setName("Tomate");
        p.setAllergen("Aucun");
        p.setDescription("Une tomate fraiche");
        p.setImage("no-image");
        p.setCategory(Category.ENTREE);
        p.setPrice(0.5);
        p.setStock(25);
        daoProduct.create(p);
    }

    @After
    public void TearDown() throws ExceptionDao {
        if(p != null) {
            daoProduct.delete(p);

        }
    }

    @Test
    @DisplayName("Check if product is in database")
    @Tag("ProductDaoTest")
    public void checkProduct() throws ExceptionDao {
        Product p1 = daoProduct.get("Tomate");
        assertNotNull(p1);
    }


    @Test
    @DisplayName("get product and check it is not nul")
    @Tag("ProductDaoTest")
    public void productNotNull() throws ExceptionDao {
        Product p2 = null;
        p2 = daoProduct.get("Tomate");
        assertNotNull(p2);
        assertThat(p2, notNullValue(Product.class));
    }

    @Test
    @DisplayName("Product should remain null")
    @Tag("ProductDaoTest")
    public void productIsNull() {
        Exception exception = assertThrows(ExceptionDao.class, () -> {
            daoProduct.get("shiba"); //Fail
        });

        String expectedMessage = "Impossible de récupérer les informations de ce produit. Veuillez contacter votre administrateur.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("get product and check its name is Tomate")
    @Tag("ProductDaoTest")
    public void productNameEqual() throws ExceptionDao {
        Product p2 = null;
        p2 = daoProduct.get("Tomate");
        assertEquals(p2.getName(), "Tomate");
    }

    @Test
    @DisplayName("Should throw because not in database")
    @Tag("ProductDaoTest")
    public void ProductNotExistInDatabase()
    {
        ExceptionDao exception = assertThrows(ExceptionDao.class , () -> daoProduct.get("99999"));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Impossible de récupérer les informations de ce produit."));
    }

    @Test
    @DisplayName("get all products and count")
    @Tag("ProductDaoTest")
    public void getAllProducts() throws ExceptionDao {
        Product p1 = new Product();
        p1.setName("Steak");
        p1.setAllergen("Aucun");
        p1.setDescription("Un steak frais");
        p1.setImage("no-image");
        p1.setCategory(Category.PLAT);
        p1.setPrice(1.5);
        p1.setStock(50);
        daoProduct.create(p1);

        Collection<Product> p2;
        p2 = daoProduct.getAll();

        assertThat(p2.size(), Matchers.greaterThanOrEqualTo(2));
    }
//
//    @Test
//    @DisplayName("Courses should be equal")
//    @Tag("CourseDaoTest")
//    public void CompareCourseSame() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(4.0f);
//
//        assertSame(c, c);
//    }
//
//    @Test
//    @DisplayName("Courses should be equal")
//    @Tag("CourseDaoTest")
//    public void CompareCourseNotSame() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(2);
//
//        Course fetchedC= null;
//        fetchedC = daoCourse.get(81); //Success
//        assertNotNull(fetchedC);
//        assertNotSame(fetchedC, c);
//    }
//
////    @Test
////    @DisplayName("Get Course index")
////    @Tag("CourseDaoTest")
////    public void CompareCourseSameIndex() throws ExceptionDao {
////        int cIndex = daoCourse.getIndex("EPS", 4); //Success
////        assertThat(cIndex ,equalTo(81));
////    }
//
//    @Test
//    @DisplayName("Get Course index")
//    @Tag("CourseDaoTest")
//    public void UpdateAndCompareCourseSameNbHours() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(2);
//
//        daoCourse.update(c); //Success
//        Course d = daoCourse.get(81);
//        assertThat(c.getNbHours() ,equalTo(d.getNbHours()));
//    }


/*
@org.junit.jupiter.api.Test
void exampleTest() {
    Assertions.assertTrue(trueBool);
    Assertions.assertFalse(falseBool);

    Assertions.assertNotNull(notNullString);
    Assertions.assertNull(notNullString);

    Assertions.assertNotSame(originalObject, otherObject);
    Assertions.assertEquals(4, 4);
    Assertions.assertNotEquals(3, 2);

    Assertions.assertArrayEquals(new int[]{1,2,3}, new int[]{1,2,3}, "Array Equal Test");

    Iterable<Integer> listOne = new ArrayList<>(Arrays.asList(1,2,3,4));
    Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1,2,3,4));
    Assertions.assertIterableEquals(listOne, listTwo);

    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
    Thread.sleep(50);
    return "result";
    });

    Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
    throw new IllegalArgumentException("error message");
    });

    Assertions.fail("not found good reason to pass");
}
 */

    @DisplayName("Create a product and check if it exist")
    @Tag("ProductDaoTest")
    public void createProduct() throws ExceptionDao {
        Product p =  new Product();
        p.setName("cheeseburger");
        p.setDescription("pain à burger, cheddar");
        p.setPrice(15);
        p.setAllergen("sésame");
        p.setCategory(Category.PLAT);
        daoProduct.create(p);
        Product p1 = daoProduct.get("cheeseburger");
        assertNotNull(p1);
        daoProduct.delete(p1.getId());
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
