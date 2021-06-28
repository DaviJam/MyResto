package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static eu.ensup.myresto.dao.Connect.openConnection;
public class ProductDao implements IDao<Product> {



    /**
     * Create an T in the database.
     *
     * @param entity T to be created
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    @Override
    public int create(Product entity) throws ExceptionDao {
        try {
            Connection cn = openConnection();
            String request = "INSERT INTO `product`(`name`, `description`, `price`, `allergen`, `image`, `id_category`) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = cn.prepareStatement(request);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.setString(4, entity.getAllergen());
            st.setString(5, entity.getImage());
            st.setInt(6, entity.getCategory().getNum());

            st.executeQuery();

            cn.close();
            return 0;
        } catch (SQLException throwables) {
            throw new ExceptionDao();
        }
    }

    /**
     * Update an T of the database.
     *
     * @param entity T to be updated
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    @Override
    public int update(Product entity) throws ExceptionDao {
        try {
            Connection cn = openConnection();
            String request = "UPDATE `product` SET `id_product`=?,`name`=?,`description`=?,`price`=?,`allergen`=?,`image`=?,`id_category`=? WHERE id_product=?";

            PreparedStatement st = cn.prepareStatement(request);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.setString(4, entity.getAllergen());
            st.setString(5, entity.getImage());
            st.setInt(6, entity.getCategory().getNum());
            st.setInt(7, entity.getId());
            st.executeUpdate();

            cn.close();
            return 0;
        } catch (SQLException throwables) {
            throw new ExceptionDao();
        }
    }

    /**
     * list all T of the database.
     *
     * @return list of all T
     * @throws ExceptionDao the exception dao
     */
    @Override
    public List<Product> getAll() throws ExceptionDao {
        return null;
    }

    /**
     * Get an T in the database.
     *
     * @param index index of the T to be get
     * @return the class of type T
     * @throws ExceptionDao the exception dao
     */
    @Override
    public Product get(int index) throws ExceptionDao {
        return null;
    }

    /**
     * Delete from product.
     *
     * @param entity the entity
     * @return the int
     * @throws ExceptionDao the exception dao
     */
    @Override
    public int delete(Product entity) throws ExceptionDao {
        return 0;
    }


    /**
     * Delete int.
     *
     * @param index the entity
     * @return the int
     * @throws ExceptionDao the exception dao
     */

    public int delete(int index) throws ExceptionDao {
        return 0;
    }
}
