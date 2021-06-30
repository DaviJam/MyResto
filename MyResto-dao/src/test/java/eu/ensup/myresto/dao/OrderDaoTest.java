package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static eu.ensup.myresto.dao.IDao.DaoLogger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for Order Dao
 */
public class OrderDaoTest {

    OrderDao orderDao = new OrderDao();

    @BeforeEach
    public void setUp() {
        try {
            Connect.openConnection();
        } catch (ExceptionDao exceptionDao) {
        }
    }

    @Test
    @DisplayName("get order and check if it's not null")
    @Tag("OrderDaoTest")
    public void orderNotNull() throws ExceptionDao {
        Order o = null;
        o = orderDao.get(1);
        assertNotNull(o);
        assertThat(o, notNullValue(Order.class));
    }

    @Test
    @DisplayName("update order status")
    @Tag("OrderDaoTest")
    public void orderUpdated() throws ExceptionDao {
        Boolean res = orderDao.update(1, 2);
        assertEquals(res, true);
    }

    @Test
    @DisplayName("delete order")
    @Tag("OrderDaoTest")
    public void orderDelete() throws ExceptionDao {
        //int result = orderDao.delete(13);
        //assertEquals(result, 1);
    }

    @Test
    @DisplayName("getAll orders")
    @Tag("OrderDaoTest")
    public void orderGetAll() throws ExceptionDao {
        List<Order> orderlist = orderDao.getAll();
        assertNotNull(orderlist);
    }

    @Test
    @DisplayName("create orders")
    @Tag("OrderDaoTest")
    public void orderCreate() throws ExceptionDao {
        /*User user = new User(341, "dupont","jean", Role.CLIENT, "jdupont@gmail.com", "1234", "2 rue des jeans");
        Product prod1 = new ProductDao().getProductById(1);
        List<Product> listproduct= new ArrayList<Product>();
        listproduct.add(prod1);
        Order order = new Order(user, listproduct, new Date(2021, 06, 06), Status.ENCOURS);
        new OrderDao().create(order);*/
    }
}
