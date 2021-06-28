package eu.ensup.gestionetablissement.dao;

import static eu.ensup.gestionetablissement.dao.Connect.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ensup.gestionetablissement.business.Director;
import eu.ensup.gestionetablissement.business.Manager;
import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.business.Student;
import eu.ensup.gestionetablissement.business.Teacher;

/**
 * The type Dao.
 */
public class PersonDao implements IPersonDao
{
    /**
     * The Cn.
     */
// initialisation des variables java permettant de dialoguer avec la bdd
    // connecter a la base de données
    Connection cn = null;
    /**
     * The St.
     */
// executer la requete
    PreparedStatement st = null;
    /**
     * The Rs.
     */
// récupérer le résultat
    ResultSet rs = null;
    /**
     * The Res.
     */
// nombre de mises à jour
    int res = 0;
    // nom de la classe
    String className = getClass().getName();

    /**
     * Instantiates a new Dao person.
     */
    public PersonDao()
    {

    }

    /**
     * Create person. Person could be of type Teacher, Director, Student or Manager
     *
     * @param entity the person object
     * @return Result of the request, if an exception was catched, returns -1
     */
    @Override
    public int create(Person entity) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requete
             */
            String sql_request = "INSERT INTO Person(" +
                    "firstname," +
                    "lastname," +
                    "email," +
                    "address,"+
                    "phone,"+
                    "role,"+
                    "password,"+
                    "dateofbirth,"+
                    "subjecttaught," +
                    "average) "+
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            st = cn.prepareStatement(sql_request);
            st.setString(1, entity.getFirstname());
            st.setString(2, entity.getLastname());
            st.setString(3, entity.getMailAddress());
            st.setString(4, entity.getAddress());
            st.setString(5, entity.getPhoneNumber());
            st.setInt   (6, entity.getRole().getNum());
            st.setString(7, entity.getPassword());

            if(entity instanceof Student)
            {
                st.setDate  (8, new java.sql.Date(((Student) entity).getDateOfBirth().getTime()));
                st.setString (9, null);
                st.setDouble(10, ((Student) entity).getAverage());
            }else if(entity instanceof Teacher)
            {
                st.setDate  (8, null);
                st.setString (9, ((Teacher) entity).getSubjectTaught());
            }else
            {
                st.setDate  (8, null);
                st.setString (9, null);
            }

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
            DaoLogger.logDaoInfo(className, methodName,"L'utilisateur " + entity.getLastname() +" "+entity.getFirstname() + " " + entity.getMailAddress() + " a été créé.");

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
    public int update(Person entity) throws ExceptionDao{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            StringBuilder sql_request = new StringBuilder();
            sql_request.append("UPDATE Person SET firstname = ?, lastname = ?, address =  ?," +
                                " phone = ?, role = ?, ");
            if(!entity.getPassword().isEmpty())
            {
                sql_request.append("password = ?, ");
            }
            sql_request.append("dateofbirth = ?, subjecttaught = ?, average = ?, email = ? WHERE id = ?");

            st = cn.prepareStatement(sql_request.toString());
            st.setString(1, entity.getFirstname());
            st.setString(2, entity.getLastname());
            st.setString(3, entity.getAddress());
            st.setString(4, entity.getPhoneNumber());
            st.setInt   (5, entity.getRole().getNum());
            if(!entity.getPassword().isEmpty())
            {
                st.setString(6, entity.getPassword());
            }
            if(entity instanceof Student)
            {
                st.setDate  ((!entity.getPassword().isEmpty()) ? 7 : 6, new java.sql.Date(((Student) entity).getDateOfBirth().getTime()));
                st.setString((!entity.getPassword().isEmpty()) ? 8 : 7, null);
                System.out.println("Student updated "+entity.getId()+" with average " + ((Student)entity).getAverage());

                st.setFloat((!entity.getPassword().isEmpty()) ? 9 : 8, (float)((Student) entity).getAverage());
            }else if(entity instanceof Teacher)
            {
                st.setDate  ((!entity.getPassword().isEmpty()) ? 7 : 6, null);
                st.setString((!entity.getPassword().isEmpty()) ? 8 : 7, ((Teacher) entity).getSubjectTaught());
                st.setDouble((!entity.getPassword().isEmpty()) ? 9 : 8, (Double)null);
            }else
            {
                st.setDate  ((!entity.getPassword().isEmpty()) ? 7 : 6, null);
                st.setString((!entity.getPassword().isEmpty()) ? 8 : 7, null);
                st.setDouble((!entity.getPassword().isEmpty()) ? 9 : 8, (Double)null);
            }
            st.setString ((!entity.getPassword().isEmpty()) ? 10 : 9,  entity.getMailAddress());
            st.setInt ((!entity.getPassword().isEmpty()) ? 11 : 10,  entity.getId());

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();

            if( res == 0)
            {
                DaoLogger.logDaoError(className, methodName,"Echec de la mise à jour de l'utilisateur" + entity.getLastname() + " " +entity.getFirstname() + " " + entity.getMailAddress());
                throw new ExceptionDao("La mise à jour a échoué. L'utilisateur n'existe pas en base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + entity.getLastname() +" "+entity.getFirstname() + " " + entity.getMailAddress() + " ont bien été modifié.");
            /*
             * Fermer la connexion
             */
            cn.close();

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
    public Person get(int index) throws ExceptionDao {
        Person p1 = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM Person WHERE id = ?";
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
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int role = rs.getInt("role");
                String password = rs.getString("password");
                Object dateofbirth = rs.getObject("dateofbirth");
                Object subjecttaught = rs.getObject("subjecttaught");
                float average = rs.getFloat("average");

                if(role == Role.DIRECTOR.getNum())
                {
                    p1 = new Director(lastname, email, address, phone, id, firstName, password);
                }
                else if(role == Role.MANAGER.getNum())
                {
                    p1 = new Manager(lastname, email, address, phone, id, firstName, password);
                }
                else if(role == Role.TEACHER.getNum())
                {
                    if(subjecttaught != null) {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, (String)subjecttaught);
                    }
                    else {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, null);
                    }
                }
                else if(role == Role.STUDENT.getNum())
                {
                    if(dateofbirth != null) {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, (Date)dateofbirth, average);
                    } else {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, null, average);
                    }
                }

                DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + lastname +" "+firstName + " " + email + " ont été récupérer de la base de donnée.");
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
    public Person get(String email) throws ExceptionDao {
        Person p1 = null;
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM Person WHERE email = ?";
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
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int role = rs.getInt("role");
                String password = rs.getString("password");
                Object dateofbirth = rs.getObject("dateofbirth");
                Object subjecttaught = rs.getObject("subjecttaught");
                float average = rs.getFloat("average");

                if(role == Role.DIRECTOR.getNum())
                {
                    p1 = new Director(lastname, email, address, phone, id, firstName, password);
                }
                else if(role == Role.MANAGER.getNum())
                {
                    p1 = new Manager(lastname, email, address, phone, id, firstName, password);
                }
                else if(role == Role.TEACHER.getNum())
                {
                    if(subjecttaught != null) {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, (String)subjecttaught);
                    }
                    else {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, null);
                    }
                }
                else if(role == Role.STUDENT.getNum())
                {
                    if(dateofbirth != null) {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, (Date)dateofbirth, average);
                    } else {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, null, average);
                    }
                }
                DaoLogger.logDaoInfo(className, methodName,"Les information de l'utilisateur " + lastname +" "+firstName + " " + email + " ont été récupérer de la base de donnée.");
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
    public List<Person> getAll() throws ExceptionDao {
        List<Person> listPerson = new ArrayList<Person>();
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "SELECT * FROM Person";
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
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int role = rs.getInt("role");
                String password = rs.getString("password");
                Object dateofbirth = rs.getObject("dateofbirth");
                Object subjecttaught = rs.getObject("subjecttaught");
                float average = rs.getFloat("average");
                Person p1 = null;

                if(rs.getInt("role") == Role.DIRECTOR.getNum())
                {
                    p1 = new Director(lastname, email, address, phone, id,  firstName, password);
                }
                else if(rs.getInt("role") == Role.MANAGER.getNum())
                {
                    p1 = new Manager(lastname, email, address, phone, id, firstName, password);
                }
                else if(rs.getInt("role") == Role.TEACHER.getNum())
                {
                    if(subjecttaught != null) {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, (String)subjecttaught);
                    }
                    else {
                        p1 = new Teacher(lastname, email, address, phone, id, firstName, password, null);
                    }
                }
                else if(rs.getInt("role") == Role.STUDENT.getNum())
                {
                    if(dateofbirth != null) {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, (Date)dateofbirth, average);
                    }
                    else {
                        p1 = new Student(lastname, email, address, phone, id, firstName, password, null, average);
                    }
                }
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
    @Override
    public int delete(int index) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM Person WHERE id = ?";
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
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "DELETE FROM Person WHERE email = ?";
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

    /**
     * Link to course int.
     *
     * @param entity the entity
     * @param course the course
     * @return Result of the request
     */
    public int linkToCourse(int entity, int course) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        try {
            /*
             * CrÃ©er la connexion
             */
            cn = openConnection();

            /*
             * CrÃ©er la requÃªte
             */
            String sql_request = "INSERT INTO Course_Person(idPerson, idCourse) VALUES (?, ?)";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, entity);
            st.setInt(2, course);

            /*
             * ExÃ©cuter la requÃªte
             */
            res = st.executeUpdate();
            if(res == 0)
            {
                DaoLogger.logDaoError(className, methodName, "Echec lors de la liaision de l'utilisateur au cours demandé.");
                throw new ExceptionDao("Échec lors de la tentative de création de lien entre cette personne et le cours demandé. Le cours ou l'étudiant n'existe pas.");
            }
            else {
                DaoLogger.logDaoInfo(className, methodName, "Le lien entre l'utilisateur et le cours a bien été créé.");
            }
            /*
             * Fermer la connexion
             */
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction INSERT dans la méthode LinkToCourse a échouée.",e);
            throw new ExceptionDao("Impossible de lier l'utilisateur à ce cours. Veuillez contacter votre administrateur.");
        }
        return 0;
    }
}
