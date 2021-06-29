package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.*;

import static eu.ensup.myresto.dao.Connect.openConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IDao<Order> {

    Connection cn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int res = 0;
    String className = getClass().getName();


    public OrderDao(){

    }

    @Override
    public int create(Order entity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * Créer la requete
             */
            String sql_request = "INSERT INTO Orders(" +
                    "order_date," +
                    "id_status," +
                    "id_user)" +
                    "VALUES(?, ?, ?) ";
            st = cn.prepareStatement(sql_request);
            st.setDate(1, new java.sql.Date(entity.getOrder_date().getTime()));
            st.setString(2, entity.getStatus().getName());
            st.setInt(3, entity.getUser().getId());


            /*
             * Exécuter la requête
             */
            res = st.executeUpdate();

            // Ajout des produits dans la table List avec l'id de la commande
            for(Product product : entity.getProduct())
            {
                String sql_request_list = "INSERT INTO List(" +
                        "id_product," +
                        "id_order)" +
                        "VALUES(?, ?) ";
                st = cn.prepareStatement(sql_request_list);
                st.setInt(1, product.getId());
                st.setInt(2, entity.getId());

                res = st.executeUpdate();
            }
            //*****************//

            /*
             * Fermer la connexion
             */
            cn.close();

            /**
             * Log to file
             */
            DaoLogger.logDaoInfo(className, methodName,"La commande numéro " + entity.getId() + " a été créée");

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une commande à la base de donnée.",e);
            throw new ExceptionDao("Impossible de créer la commande. Veuillez contacter votre administrateur.");
        }
        return res;
    }

    @Override
    public int update(Order entity) throws ExceptionDao {
        return 0;
    }

    @Override
    public List<Order> getAll() throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Order> allOrders = new ArrayList<Order>();

        Statement st = null;
        ResultSet res = null;

        try{
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Orders");
            if(!res.next()){
                throw new ExceptionDao("Aucune commande disponible dans la base de donnée.");
            }
            else
            {
                do {
                    Order order = this.get(res.getInt("id_order"));
                    allOrders.add(order);
                }
                while(res.next());
            }

            // TODO:  Add logger failed and successfull
            if(allOrders.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant toutes les commandes.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant toutes les commandes a réussie.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }
        return allOrders;
    }

    @Override
    public Order get(int index) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        Order order = null;

        Statement st = null;
        ResultSet res = null;

        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT orders.id_order, order_date, id_status, orders.id_user, users.surname, users.firstname, users.email, users.password, users.address , users.id_role, list.id_product, product.name, product.description, product.price, product.allergen, product.image, product.stock, product.id_category FROM orders, users, list, product WHERE orders.id_user = users.id_user AND list.id_product = product.id_product AND orders.id_user = list.id_order AND orders.id_order = " + index + " GROUP BY orders.id_order, orders.id_user, list.id_product;");
            System.out.println(res.toString());
            if(!res.next()){
                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant la commande. Ce dernier n'existe pas en base de donnée.");
                throw  new ExceptionDao("La commande n'existe pas dans la base de donnée.");
            }
            else
            {
                List<Product> listproduct = new ArrayList<Product>();
                User user = new User(res.getString("surname"), res.getString("firstname"), Role.CLIENT, res.getString("email"), res.getString("password"), res.getString("address"));
                java.sql.Date date = res.getDate("order_date");
                Status status = Status.getStatusByNum(res.getInt("id_status"));
                int idorder = res.getInt("id_order");
                do
                {
                    Product product = new Product(res.getString("name"),
                            res.getString("description"),
                            res.getDouble("price"),
                            res.getString("allergen"),
                            res.getString("image"),
                            res.getInt("stock"),
                            Category.getCategoryByNum(res.getInt("id_category")));

                    listproduct.add(product);
                }
                while(res.next());

                order = new Order(idorder,user, listproduct, date, status);

                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoInfo(className, methodName, "Les informations de la commande numéro " + index + " ont été récupérées ");
            }
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }
        finally{
            try {
                st.close();
                cn.close();
            }
            catch(SQLException sqle) {

                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",sqle);
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
        }

        return order;
    }

    @Override
    public int delete(Order entity) throws ExceptionDao {
        return 0;
    }

    public int delete(int index) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 1;

        if(index != -1)
        {
            Connection cn = Connect.openConnection();

            Statement st = null;
            // Delete les produits from list
            try
            {
                st = cn.createStatement();
                st.execute("DELETE FROM List WHERE id_order="+index);

                DaoLogger.logDaoInfo(className, methodName,"La suppression des produits relié à la commande a réussie.");
                st.close();
                cn.close();
            }
            catch (SQLException e) {
                // TODO:  Add logger failed and successfull
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
            // Delete la commande
            try
            {
                cn = Connect.openConnection();
                st = cn.createStatement();
                st.execute("DELETE FROM Orders WHERE id_order="+index);

                DaoLogger.logDaoInfo(className, methodName,"La suppression de la commande a réussie.");
                st.close();
                cn.close();
            }
            catch (SQLException e) {
                // TODO:  Add logger failed and successfull
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
        }

        if (res != 0) {
            DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression de la commande. Ce dernier n'existe pas dans la base de donnée.");
        }

        return res;
    }

    public Boolean update(int index, int status) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Boolean res = false;

            Connection cn = Connect.openConnection();
            Statement st = null;
            try {
                st = cn.createStatement();
                st.execute("UPDATE Orders SET id_status = "+status+" WHERE id_order="+index);

                DaoLogger.logDaoInfo(className, methodName,"Le statut de la commande numéro " + index + " a bien été modifié en " + status);
                st.close();
                cn.close();
                res = true;
            }
            catch( SQLException sqle) {
                res = false;
                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoError(className, methodName,"Probleme de modification de la base de donnée.",sqle);
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
        return res;
    }
}
