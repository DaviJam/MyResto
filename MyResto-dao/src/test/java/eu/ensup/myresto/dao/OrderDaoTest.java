package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.*;
import eu.ensup.myresto.business.Order;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
    UserDao userDao = new UserDao();
    ProductDao productDao = new ProductDao();
    User user;

    @BeforeEach
    public void setUp() throws ExceptionDao {
        Connect.openConnection();
        user = new User("James", "Jamel", Role.CLIENT, "jamel.J@email.com", "test", "somewhere");
        userDao.create(user);
    }

    @AfterEach
    public void tearDown() throws ExceptionDao {
        userDao.delete(user.getEmail());
    }

    @Test
    @DisplayName("get order and check if it's not null")
    @Tag("OrderDaoTest")
    public void orderNotNull() throws ExceptionDao {
        Order o = createOrder();
        assertNotNull(orderDao.get(o.getId()));
        assertThat(orderDao.get(o.getId()), notNullValue(Order.class));
        deleteOrder(o.getId());
    }

    @Test
    @DisplayName("update order status")
    @Tag("OrderDaoTest")
    public void orderUpdated() throws ExceptionDao {
        Order o = createOrder();
        Boolean res = orderDao.update(o.getId(), Status.ENCOURS);
        assertEquals(res, true);
        o = orderDao.get(o.getId());
        assertEquals(o.getStatus(), Status.ENCOURS);
        deleteOrder(o.getId());
    }

    @Test
    @DisplayName("get all orders")
    @Tag("OrderDaoTest")
    public void orderGetAll() throws ExceptionDao {
        createOrder();
        createOrder();

        List<Order> orderlist = orderDao.getAll();
        assertNotNull(orderlist);

        orderlist.forEach(o -> {
            try {
                deleteOrder(o.getId());
            } catch (ExceptionDao exceptionDao) {
                exceptionDao.printStackTrace();
            }
        });

    }

    Order createOrder() throws ExceptionDao {
        List<Product> products = new ArrayList<>();

        products.add(productDao.get(1));
        products.add(productDao.get(4));
        products.add(productDao.get(3));

        int orderId = orderDao.create(new Order(userDao.get(user.getEmail()),products, new Date(), Status.ENATTENTE));
        return orderDao.get(orderId);
    }

    void deleteOrder(int id) throws ExceptionDao {
        orderDao.delete(id);
    }

}
