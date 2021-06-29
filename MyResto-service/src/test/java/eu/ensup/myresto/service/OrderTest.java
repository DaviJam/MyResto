package eu.ensup.myresto.service;

import eu.ensup.myresto.business.*;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.OrderDao;
import eu.ensup.myresto.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @Mock
    OrderDao dao;

    @InjectMocks
    OrderService service;

    @Test
    public void getOrderOk() throws ExceptionDao, ExceptionService {
        User user = new User(0, "Lacomblez", "Thomas", Role.CLIENT, "Lacomblez.thomas@gmail.com", "1234", "80 B rue de Chartres");
        Product product = new Product(0, "cheeseburger", "pain à burger, cheddar", 15.0, "sésame", null, 0, Category.PLAT);
        Product product2 = new Product(0, "Big Mac", "Le big Mac quoi", 10.0, "sésame", null, 0, Category.PLAT);
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product2);
        Date dateorder = new Date(2021-06-28);
        when(dao.get(1)).thenReturn(new Order(1, user, productList, dateorder, Status.TERMINE));
        OrderDTO orderDTO = service.get(1);
        assertEquals(orderDTO.getId(), 1);
        Mockito.verify(dao, Mockito.atLeast(1)).get(1);
    }

    @Test
    public void createOrderOk() throws ExceptionDao, ExceptionService {
        OrderDTO orderDTO = new OrderDTO(0, new User(), new ArrayList<Product>(), new Date(), Status.TERMINE);
        when(dao.create(any(Order.class))).thenReturn(1);
        int ret = service.create(orderDTO);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).create(any(Order.class));
    }

    @Test
    public void updateOrderOk() throws ExceptionDao, ExceptionService {
        OrderDTO orderDTO = new OrderDTO(0, new User(), new ArrayList<Product>(), new Date(), Status.TERMINE);
        when(dao.update(any(Order.class))).thenReturn(1);
        Boolean ret = service.update(0, Status.TERMINE.getNum());
        assertEquals(ret, true);
        Mockito.verify(dao, Mockito.atLeast(1)).update(anyInt(), anyInt());
    }

    @Test
    public void deleteOrderOk() throws ExceptionDao, ExceptionService {
        when(dao.delete(1)).thenReturn(1);
        int ret = service.delete(1);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).delete(1);
    }
}
