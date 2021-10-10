package eu.ensup.myresto.service;

import eu.ensup.myresto.business.*;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.OrderDao;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.dto.StatusDTO;
import eu.ensup.myresto.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
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
        Product product = new Product(0, "cheeseburger", "pain à burger, cheddar", 15.0, "sésame", null, 0, Category.BURGER);
        Product product2 = new Product(0, "Big Mac", "Le big Mac quoi", 10.0, "sésame", null, 0, Category.BURGER);
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
        OrderDTO orderDTO = new OrderDTO(0, new UserDTO(), new ArrayList<ProductDTO>(), new Date(), Status.TERMINE);
        when(dao.create(any(Order.class))).thenReturn(1);
        int ret = service.create(orderDTO);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).create(any(Order.class));
    }

    @Test
    public void updateOrderOk() throws ExceptionDao, ExceptionService {
        OrderDTO orderDTO = new OrderDTO(0, new UserDTO(), new ArrayList<ProductDTO>(), new Date(), Status.TERMINE);
        when(dao.update(0,Status.TERMINE)).thenReturn(true);
        Boolean ret = service.update(0, StatusDTO.TERMINE);
        assertEquals(ret, true);
        Mockito.verify(dao, Mockito.atLeast(1)).update(anyInt(), Status.getStatusByNum(anyInt()));
    }

    @Test
    public void deleteOrderOk() throws ExceptionDao, ExceptionService {
        when(dao.delete(1)).thenReturn(1);
        int ret = service.delete(1);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).delete(1);
    }

    @Test
    public void TestOrder(){
        //RequestDispatcher requestDispatcher;
        //requestDispatcher = req.getRequestDispatcher("testorderlist.jsp");

        //HttpSession session = req.getSession();
        //session.setAttribute("email", "youness@gmail.com");
        //session.setAttribute("role", "2");
        //String emailuser = (String) session.getAttribute("email");
        String role = "2";
        String emailuser = "youness@gmail.com";
        OrderService orderService = new OrderService();

        List<OrderDTO> orderlist = new ArrayList<OrderDTO>();

        try { // Récupération de toutes les commandes
            orderlist = orderService.getAll();
            System.out.println("order list : " + orderlist.toString());
        } catch (ExceptionService exceptionService) {
//             exceptionService.printStackTrace();
        }
        // Si l'utilisateur est un client, on trie les commandes pour ne récupérer que les siennes
        if(role == "1"){ // Si c'est un client, on trie les commandes
            List<OrderDTO> neworderlist = new ArrayList<OrderDTO>();
            for(OrderDTO o : orderlist){
                if(o.getUser().getEmail() == emailuser)
                {
                    neworderlist.add(o);
                }
            }
            System.out.println(neworderlist);
        }
        else
        {
        }
        System.out.println(orderlist);
    }

    @Test
    public void createOrder(){
        /*List<ProductDTO> test_list = new ArrayList<ProductDTO>();
        int id_user = 1;
        try {
            test_list.add(new ProductService().getProductById(1));
            test_list.add(new ProductService().getProductById(2));

            // Creation de l'order
            OrderDTO orderDTO = new OrderDTO(new UserService().get(id_user), test_list, new java.sql.Date(Calendar.getInstance().getTime().getTime()), Status.ENCOURS);

            new OrderService().create(orderDTO);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        } */
    }
}
