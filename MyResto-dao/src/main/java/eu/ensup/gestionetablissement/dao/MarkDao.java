package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.business.Mark;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Course dao.
 */
public class MarkDao implements IMarkDao
{
    String className = getClass().getName();

    @Override
    public List<Mark> getAll() throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Mark> allMark = new ArrayList<Mark>();

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark");

            while( res.next() )
            {
                Mark cours = new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment"));

                allMark.add(cours);
            }
            if(allMark.isEmpty()) {
            }

            // TODO:  Add logger failed and successfull
            if(allMark.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les Mark.");
                throw new ExceptionDao("Aucun mark disponible dans la base de donnée.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les mark a réussie.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return allMark;
    }

    @Override
    public List<Mark> getAllMarkByStudentId( int index ) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Mark> allMark = new ArrayList<Mark>();

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark WHERE idStudent="+index);

            while( res.next() )
                allMark.add(new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment")));

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoInfo(className, methodName,"Les information " + index + " ont été récupérer de la base de donnée.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return allMark;
    }

    @Override
    public Mark get( int index )  throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        Mark mark = null;

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark WHERE id="+index);
            if(!res.next()){
                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant le mark. Ce dernier n'existe pas en base de donnée.");
                throw  new ExceptionDao("Le cours n'existe pas dans la base de donnée.");
            }

            while( res.next() )
                mark = new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment"));

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoInfo(className, methodName,"Les information " + mark.toString() + " ont été récupérer de la base de donnée.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode get a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return mark;
    }

    @Override
    public int create( Mark mark ) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 1;
        Connection cn = Connect.openConnection();
        PreparedStatement pstmt = null;

        try
        {
            //Vérifie qu'il n'y a pas de double
            if( mark.getId() != -1 )
            {
                pstmt = cn.prepareStatement("INSERT INTO Mark (idStudent, idCourse, mark, assessment) VALUES ( ?, ?, ?, ? )");

                int index = 1;
                pstmt.setInt(index++, mark.getIdStudent());
                pstmt.setInt(index++, mark.getIdCourse());
                pstmt.setFloat(index++, mark.getMark());
                pstmt.setString(index++, mark.getAssessment());

                pstmt.execute();
                DaoLogger.logDaoInfo(className, methodName, mark.toString() + " a �t� cr��.");
                pstmt.close();
            }else{

                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoInfo(className, methodName, mark.toString() +" existe d�ja dans la base.");
                throw  new ExceptionDao("Cette note existe d�ja!");
            }
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"Problème d'ajout d'une note � la base de donnée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return res;
    }

    @Override
    public int update(Mark mark) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 1;
        Mark preMark = get(mark.getId());
        String update = "";

        if( (Float)mark.getMark() != null && mark.getMark() != preMark.getMark() )
            update += "mark="+mark.getMark();

        if( mark.getAssessment() != null && ! mark.getAssessment().equals(preMark.getAssessment()) )
            update += (update != "" ? "," : "")+"assessment='"+mark.getAssessment()+"'";

        if( update != "" )
        {
            Connection cn = Connect.openConnection();
            Statement st = null;
            try {
                st = cn.createStatement();
                st.execute("UPDATE Mark SET "+update+" WHERE id="+mark.getId());

                DaoLogger.logDaoInfo(className, methodName,"Les information " + mark.toString() +" ont bien été modifié.");
                st.close();
                cn.close();
            }
            catch( SQLException sqle) {

                // TODO:  Add logger failed and successfull
                DaoLogger.logDaoError(className, methodName,"Probl�me de modification de la base de donn�e.",sqle);
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
            finally {
                res = 0;
            }
        }
        return res;
    }

    @Override
    public int delete( int index ) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        int res = 1;
        if( index != -1 && indexExist(index) )
        {
            Connection cn = Connect.openConnection();

            Statement st = null;
            try
            {
                st = cn.createStatement();
                st.execute("DELETE FROM Mark WHERE id="+index);

                DaoLogger.logDaoInfo(className, methodName,"La suppression de la note a réussie.");
                st.close();
                cn.close();
            }
            catch (SQLException e) {
                // TODO:  Add logger failed and successfull
                throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
            }
        }
        if (res != 0) {
            DaoLogger.logDaoError(className, methodName,"Echec lors de la suppression de la note. Ce dernier n'existe pas dans la base de donnée.");
        }

        return res;
    }

    @Override
    public int delete( Mark mark ) throws ExceptionDao
    {
        return delete(mark.getId());
    }
    @Override
    public boolean indexExist(int index) throws ExceptionDao {
        boolean existe = false;

        List<Mark> alMark = getAll();
        for( Mark mark : alMark )
            if( index == mark.getId() )
                existe = true;

        return existe;
    }
}
