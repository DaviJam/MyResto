package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;
import org.javatuples.Triplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static eu.ensup.myresto.dao.Connect.openConnection;
public class ProductDao implements IDao<Product> {

    /**
     * The Cn.
     * initialisation des variables java permettant de dialoguer avec la bdd
     */
    Connection cn = null;
    /**
     * The St.
     * executer la requete
     */
    PreparedStatement st = null;
    /**
     * The Rs.
     * récupérer le résultat
     */
    ResultSet rs = null;
    /**
     * The Res.
     * nombre de mises à jour
     */
    int res = 0;

    // nom de la classe
    String className = getClass().getName();

    /**
     * Create an T in the database.
     *
     * @param entity T to be created
     * @return type of the result
     * @throws ExceptionDao the exception dao
     */
    @Override
    public int create(Product entity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            cn = openConnection();
            String request = "INSERT INTO `product`(`name`, `description`, `price`, `allergen`, `image`, `stock`, `id_category`) VALUES (?,?,?,?,?,?,?)";

            st = cn.prepareStatement(request);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.setString(4, entity.getAllergen());
            st.setString(5, entity.getImage());
            st.setInt(6, entity.getStock());
            st.setInt(7, entity.getCategory().getNum());

            st.executeUpdate();

            cn.close();
            DaoLogger.logDaoInfo(className, methodName,"Le produit " + entity.getName() + " a été créé.");

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"Un problème est survenue lors de l'ajout du produit dans la base de donnée.",e);
            throw new ExceptionDao("Impossible de créer le produit. Celui-ci existe déja!");
        }
        return 0;
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
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            Connection cn = openConnection();
            String request = "UPDATE `product` SET `name`=?,`description`=?,`price`=?,`allergen`=?,`image`=?, `stock`=?, `id_category`=? WHERE id_product=?";

            PreparedStatement st = cn.prepareStatement(request);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDescription());
            st.setDouble(3, entity.getPrice());
            st.setString(4, entity.getAllergen());
            st.setString(5, entity.getImage());
            st.setInt(6, entity.getStock());
            st.setInt(7, entity.getCategory().getNum());
            st.setInt(8, entity.getId());
            st.executeUpdate();

            /*if(res == 0)
            {
                DaoLogger.logDaoError(className, methodName,"Echec de la mise à jour du produit : " + entity.getName());
                throw new ExceptionDao("La mise à jour a échoué. Le produit n'existe pas en base de donnée.");
            }*/

            DaoLogger.logDaoInfo(className, methodName,"Les information du produit " + entity.getName() + " ont bien été modifié.");

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction UPDATE dans la méthode update a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
        return res;
    }

    /**
     * list all T of the database.
     *
     * @return list of all T
     * @throws ExceptionDao the exception dao
     */
    @Override
    public List<Product> getAll() throws ExceptionDao {
        List<Product> listProduct = new ArrayList<>();
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM product";
            st = cn.prepareStatement(sql_request);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();

            /*
             * Créer une personne
             */
            while(rs.next())
            {
                int id = rs.getInt("id_product");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                String allergen = rs.getString("allergen");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");
                Category category = Category.getCategoryByNum(rs.getInt("id_category"));

                Product p1 = null;
                p1 = new Product(id, name, desc, price, allergen, image, stock, category);

                listProduct.add(p1);
            }

            if(listProduct.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les produits.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les produits a réussie.");

            /*
             * Fermer la connexion
             */
            cn.close();
        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Impossible de récupérer les informations demandées. Veuillez contacter votre administrateur.");
        }
        return listProduct;
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
        Product product = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM product WHERE id_product = ?";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, index);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();

            /*
             * Créer une personne
             */
            if(rs.next())
            {
                int id = rs.getInt("id_product");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                String allergen = rs.getString("allergen");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");
                Category category = Category.getCategoryByNum(rs.getInt("id_category"));

                product = new Product(id, name, desc, price, allergen, image, stock, category);
                DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant le produit a réussie.");
            }  else {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant le produit. Ce dernier n'existe pas en base de donnée.");
                throw new ExceptionDao("Impossible de récupérer les informations de ce produit. Veuillez contacter votre administrateur.");
            }

            /*
             * Fermer la connexion
             */
            cn.close();
        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Impossible de récupérer les informations demandées. Veuillez contacter votre administrateur.");
        }
        return product;
    }

    public Product get(String pname) throws ExceptionDao {
        Product product = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM product WHERE name = ?";
            st = cn.prepareStatement(sql_request);
            st.setString(1, pname);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();

            /*
             * Créer une personne
             */
            if(rs.next())
            {
                int id = rs.getInt("id_product");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                String allergen = rs.getString("allergen");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");
                Category category = Category.getCategoryByNum(rs.getInt("id_category"));

                product = new Product(id, name, desc, price, allergen, image, stock, category);

                DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant le produit a réussie.");
            }  else {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant le produit. Ce dernier n'existe pas en base de donnée.");
                throw new ExceptionDao("Impossible de récupérer les informations de ce produit. Veuillez contacter votre administrateur.");
            }


            /*
             * Fermer la connexion
             */
            cn.close();
        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Impossible de récupérer les informations demandées. Veuillez contacter votre administrateur.");
        }
        return product;
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
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM product WHERE name = ?";
            st = cn.prepareStatement(sql_request);
            st.setString(1, entity.getName());

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if (res != 0) {
                DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression du produit. Ce dernier n'existe pas dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La suppression du produit a réussie.");
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction Delete dans la méthode delete a échouée.",e);
            throw new ExceptionDao("Impossible de supprimer les informations de ce produit. Veuillez contacter votre administrateur.");
        }
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
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM product WHERE id_product = ?";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, index);

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if (res != 0) {
                DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression du produit. Ce dernier n'existe pas dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La suppression du produit a réussie.");
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction Delete dans la méthode delete a échouée.",e);
            throw new ExceptionDao("Impossible de supprimer les informations de ce produit. Veuillez contacter votre administrateur.");
        }
        return 0;
    }

    public Product getProductById(int index) throws ExceptionDao {
        Connection cn = null;
        /**
         * The St.
         * executer la requete
         */
        PreparedStatement st = null;
        /**
         * The Rs.
         * récupérer le résultat
         */
        ResultSet rs = null;
        /**
         * The Res.
         * nombre de mises à jour
         */
        int res = 0;

        // nom de la classe
        String className = "ProductDao";

        Product product = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM product WHERE id_product = ?";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, index);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();

            /*
             * Créer une personne
             */
            if(rs.next())
            {
                int id = rs.getInt("id_product");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                String allergen = rs.getString("allergen");
                String image = rs.getString("image");
                int stock = rs.getInt("stock");
                Category category = Category.getCategoryByNum(rs.getInt("id_category"));

                product = new Product(id, name, desc, price, allergen, image, stock, category);
                DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant le produit a réussie.");
            }  else {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant le produit. Ce dernier n'existe pas en base de donnée.");
                throw new ExceptionDao("Impossible de récupérer les informations de ce produit. Veuillez contacter votre administrateur.");
            }

            /*
             * Fermer la connexion
             */
            cn.close();
        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Impossible de récupérer les informations demandées. Veuillez contacter votre administrateur.");
        }
        return product;
    }

    public Boolean updateStock(int id_product, int newQuantity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {

            Connection cn = openConnection();
            String request = "UPDATE `product` SET `stock`=? WHERE id_product=?";
            // Update TableA a Set Valeur_Total = (select sum(Valeur) from TableA where id1 = A.ID1 and ID2 = A.ID2)
            PreparedStatement st = cn.prepareStatement(request);
            st.setInt(1, newQuantity);
            st.setInt(2, id_product);

            st.executeUpdate();

            cn.close();
            /*if(res == 0)
            {
                DaoLogger.logDaoError(className, methodName,"Echec de la mise à jour du produit : " + entity.getName());
                throw new ExceptionDao("La mise à jour a échoué. Le produit n'existe pas en base de donnée.");
            }*/

            DaoLogger.logDaoInfo(className, methodName,"Les information du produit numéro " + id_product + " ont bien été modifié.");
            return true;
        } catch (SQLException | ExceptionDao e) {
            DaoLogger.logDaoError(className, methodName,"La transaction UPDATE dans la méthode update a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
    }

    public List<Triplet<Integer, Integer, Integer>> getOrderProducts(int orderId) throws ExceptionDao{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        List<Triplet<Integer, Integer, Integer>> list = new ArrayList<>();
        try {

            Connection cn = openConnection();
            String request = "SELECT list.id_product, list.quantity, product.stock FROM `list`, `product` WHERE list.id_product = product.id_product AND list.id_order = ?";
            PreparedStatement st = cn.prepareStatement(request);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            while(rs.next()){
                int id_product = rs.getInt("id_product");
                int stock = rs.getInt("stock");
                int quantity = rs.getInt("quantity");
                list.add(new Triplet<>(id_product, stock, quantity));
            }

            if(list.isEmpty()){
                DaoLogger.logDaoInfo(className, methodName,"La récupération des informations des produit pour la commande " +orderId + " a échoué.");
            } else {
                DaoLogger.logDaoInfo(className, methodName,"Les information du produit pour la commande " +orderId + " ont bien été récupérés.");
            }

            cn.close();


            return list;
        } catch( SQLException | ExceptionDao e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode update a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
    }

}
