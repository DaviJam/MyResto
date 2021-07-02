package eu.ensup.myresto.mapper;

import eu.ensup.myresto.business.Order;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Order mapper.
 */
public class OrderMapper {
    /**
     * Business to order dto.
     *
     * @param order the order
     * @return the order dto
     */
    public static OrderDTO businessToDto(Order order){
        OrderDTO orderDTO = new OrderDTO();
        List<ProductDTO> list_product = new ArrayList<ProductDTO>();
        for(Product p : order.getProduct())
        {
            list_product.add(ProductMapper.businessToDto(p));
        }
        orderDTO.setId(order.getId());
        orderDTO.setOrder_date(order.getOrder_date());
        orderDTO.setProduct(list_product);
        orderDTO.setStatus(order.getStatus());
        orderDTO.setUser(UserMapper.businessToDto(order.getUser()));
        return orderDTO;
    };

    /**
     * Dto to business order.
     *
     * @param orderDTO the order dto
     * @return the order
     */
    public static Order dtoToBusiness(OrderDTO orderDTO){
        Order order = new Order();
        List<Product> list_product = new ArrayList<Product>();
        for(ProductDTO p : orderDTO.getProduct())
        {
            list_product.add(ProductMapper.dtoToBusiness(p));
        }
        System.out.println("Order : " + orderDTO.toString());
        System.out.println("order dto get user : " + orderDTO.getUser());
        order.setId(orderDTO.getId());
        order.setOrder_date(orderDTO.getOrder_date());
        order.setProduct(list_product);
        order.setStatus(orderDTO.getStatus());
        order.setUser(UserMapper.dtoToBusiness(orderDTO.getUser()));

        return order;
    };

}
