package eu.ensup.myresto.dto;

import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.Status;
import eu.ensup.myresto.business.User;

import java.util.Date;
import java.util.List;

/**
 * The type Student dto.
 */
public class OrderDTO{
    private int id;
    private UserDTO user;
    private List<ProductDTO> product;
    private Date order_date;
    private Status status;

    /**
     * Instantiates a new Student dto.
     */
    public OrderDTO(){ }

    public OrderDTO(int id, UserDTO userDTO, List<ProductDTO> product, Date order_date, Status status) {
        this.id = id;
        this.user = userDTO;
        this.product = product;
        this.order_date = order_date;
        this.status = status;
    }

    public OrderDTO(UserDTO userDTO, List<ProductDTO> product, Date order_date, Status status) {
        this.user = userDTO;
        this.product = product;
        this.order_date = order_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ProductDTO> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDTO> product) {
        this.product = product;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", order_date=" + order_date +
                ", status=" + status +
                '}';
    }
}
