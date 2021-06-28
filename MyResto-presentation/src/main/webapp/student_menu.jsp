<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <title>Gestion étudiants</title>
    </head>
    <body>
        <div class="container">
            <div class="cst-form">
                    <div class="studenttitle row">
                        <h2 class="form-title">Gestion des étudiants</h2>
                    </div>
                <div class="container-fluid">
                    <div class="row">
                        <a href="/etablissement/students_add" class="btn btn-info form-control">Ajouter un étudiant</a>
                    </div>
                    <div class="row">
                        <a href="/etablissement/students_update" class="btn btn-info form-control">Modifier un étudiant</a>
                    </div>
                    <c:if test="${usrole==1}">
                    <div class="row">
                        <a href="/etablissement/students_list" class="btn btn-info form-control">Liste des étudiants</a>
                    </div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col al-center">
                        <a href="/etablissement/accueil" class="btn btn-secondary">Retour</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>