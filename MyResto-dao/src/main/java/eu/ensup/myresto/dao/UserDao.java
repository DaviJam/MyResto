package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Dao.
 */
public class UserDao implements IDao<User>
{
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
     * Instantiates a new Dao person.
     */
    public UserDao()
    {

    }

    /**
     * Create user.
     *
     * @param entity the person object
     * @return Result of the request, if an exception was catched, returns -1
     */
    @Override
    public int create(User entity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requete
             */
            String sql_request = "INSERT INTO users(" +
                    "surname," +
                    "firstname," +
                    "email," +
                    "password,"+
                    "address,"+
                    "id_role) "+
                    "VALUES(?, ?, ?, ?, ?, ?) ";
            st = cn.prepareStatement(sql_request);
            st.setString(1, entity.getSurname());
            st.setString(2, entity.getFirstname());
            st.setString(3, entity.getEmail());
            st.setString(4, entity.getPassword());
            st.setString(5, entity.getAddress());
            st.setInt   (6, entity.getRole().getNum());

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            /*
             * Fermer la connexion
             */
            cn.close();

            /**
             * Log to file
             */
            DaoLogger.logDaoInfo(className, methodName,"L'utilisateur " + entity.getSurname() +" "+entity.getFirstname() + " " + entity.getEmail() + " a été créé.");

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une personne à la base de donnée.",e);
            throw new ExceptionDao("Impossible de créer l'utilisateur. Celui-ci existe déja!");
        }
        return res;
    }

    /**
     * Update person. Person could be of type Teacher, Director, Student or Manager
     *
     * @param entity the person object
     * @return Result of the request, if an exception was catched, returns -1
     */
    @Override
    public int update(User entity) throws ExceptionDao{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            StringBuilder sql_request = new StringBuilder();
            sql_request.append("UPDATE users SET surname = ?, firstname = ?, email = ?, ");
            if(!entity.getPassword().isEmpty())
            {
                sql_request.append("password = ?, ");
            }
            sql_request.append("address =  ?, id_role = ?, WHERE id_user = ?");

            st = cn.prepareStatement(sql_request.toString());
            st.setString(1, entity.getSurname());
            st.setString(2, entity.getFirstname());
            st.setString(3, entity.getEmail());
            if(!entity.getPassword().isEmpty())
            {
                st.setString(4, entity.getPassword());
            }
            st.setString(5, entity.getAddress());
            st.setInt   (6, entity.getRole().getNum());

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            /*
             * Fermer la connexion
             */
            cn.close();

            if(res == 0)
            {
                DaoLogger.logDaoError(className, methodName,"Echec de la mise à jour de l'utilisateur" + entity.getSurname() + " " +entity.getFirstname() + " " + entity.getEmail());
                throw new ExceptionDao("La mise à jour a échoué. L'utilisateur n'existe pas en base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + entity.getSurname() +" "+entity.getFirstname() + " " + entity.getEmail() + " ont bien été modifié.");

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction UPDATE dans la méthode update a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
        return res;
    }

    /**
     * Get person.
     *
     * @param index the person index in the database
     * @return Result of the request, if an exception was catched, returns -1
     */
    @Override
    public User get(int index) throws ExceptionDao {
        User p1 = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM Users WHERE id_user = ?";
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
                int id = rs.getInt("id_user");
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Role role = Role.getRoleByNum(rs.getInt("id_role"));
                String password = rs.getString("password");

                p1 = new User(id, surname, firstname, role, email, address, password);

                DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + surname +" "+firstname + " " + email + " ont été récupérer de la base de donnée.");
            } else {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant l'utilisateur. Ce dernier n'existe pas en base de donnée.");
                throw new ExceptionDao("Impossible de récupérer les informations de cette personne. Veuillez contacter votre administrateur.");
            }

            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
        return p1;
    }

    /**
     * Get person by email.
     *
     * @param email the person email in the database
     * @return Person or throw ExceptionDao
     */
    public User get(String email) throws ExceptionDao {
        User p1 = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM users WHERE email = ?";
            st = cn.prepareStatement(sql_request);
            st.setString(1, email);

            /*
             * ExÃ©cuter la requÃªte
             */
            rs = st.executeQuery();

            /*
             * Créer une personne
             */
            if(rs.next())
            {
                int id = rs.getInt("id_user");
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String address = rs.getString("address");
                Role role = Role.getRoleByNum(rs.getInt("id_role"));
                String password = rs.getString("password");

                p1 = new User(id, surname, firstname, role, email, address, password);

                DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + surname +" "+firstname + " " + email + " ont été récupérer de la base de donnée.");
            } else {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant l'utilisateur. Ce dernier n'existe pas en base de donnée.");
                throw new ExceptionDao("Impossible de récupérer les informations de cette personne. Veuillez contacter votre administrateur.");
            }

            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée. Veuillez contacter votre administrateur.");
        }
        return p1;
    }

    /**
     * Get all person.
     *
     * @return List of Person, if an exception was catched, returns -1
     */
    @Override
    public List<User> getAll() throws ExceptionDao {
        List<User> listPerson = new ArrayList<>();
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM users";
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
                int id = rs.getInt("id_role");
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Role role = Role.getRoleByNum(rs.getInt("id_role"));
                String password = rs.getString("password");

                User p1 = null;
                p1 = new User(id, surname, firstname, role, email, address, password);

                listPerson.add(p1);
            }

            if(listPerson.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les utilisateurs.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les utilisateurs a réussie.");
            /*
             * Fermer la connexion
             */

            cn.close();
        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Impossible de récupérer les informations demandées. Veuillez contacter votre administrateur.");
        }
        return listPerson;
    }

    /**
     * Delete person.
     *
     * @param index index of the person in the database
     * @return int, 0 if success.  if an exception was catched, returns -1
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
            String sql_request = "DELETE FROM users WHERE id_user = ?";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, index);

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if (res != 0) {
                DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression de l'utilisateur. Ce dernier n'existe pas dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La suppression de l'utilisateur a réussie.");
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction Delete dans la méthode delete a échouée.",e);
            throw new ExceptionDao("Impossible de supprimer les informations de cette personne. Veuillez contacter votre administrateur.");
        }
        return 0;
    }

    /**
     * Delete person.
     *
     * @param email email of the person in the database
     * @return int, 0 if success, if an exception was catched, returns -1
     */
    public int delete(String email) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM users WHERE email = ?";
            st = cn.prepareStatement(sql_request);
            st.setString(1, email);

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if (res != 0) {
                DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression de l'utilisateur. Ce dernier n'existe pas dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La suppression de l'utilisateur a réussie.");
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction Delete dans la méthode delete a échouée.",e);
            throw new ExceptionDao("Impossible de supprimer les informations de cette personne. Veuillez contacter votre administrateur.");
        }
        return 0;
    }

    @Override
    public int delete(User entity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = Connect.openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM users WHERE id_user = ?";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, entity.getId());

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if (res != 0) {
                DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression de l'utilisateur. Ce dernier n'existe pas dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La suppression de l'utilisateur a réussie.");
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction Delete dans la méthode delete a échouée.",e);
            throw new ExceptionDao("Impossible de supprimer les informations de cette personne. Veuillez contacter votre administrateur.");
        }
        return 0;
    }
}
