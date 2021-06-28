package eu.ensup.myresto.mapper;

import eu.ensup.myresto.business.Order;
import eu.ensup.myresto.dto.OrderDTO;

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
        orderDTO.setId(order.getId());
        orderDTO.setOrder_date(order.getOrder_date());
        orderDTO.setProduct(order.getProduct());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setUser(order.getUser());
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
        order.setId(orderDTO.getId());
        order.setOrder_date(orderDTO.getOrder_date());
        order.setProduct(orderDTO.getProduct());
        order.setStatus(orderDTO.getStatus());
        order.setUser(orderDTO.getUser());

        return order;
    };
}
