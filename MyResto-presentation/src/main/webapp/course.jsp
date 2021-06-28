<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="css/margin.css">
    <link rel="stylesheet" href="css/padding.css">
    <title>Cours</title>
</head>
    <body>
        <div class="container-md">
            <div class="cst-form">
                <div class="row col-xl-12 col-md-12 m-1 p-1">
                    <div class="col-xl-9 col-md-9">
                        <h1 class="form-title">Gestion des cours</h1>
                    </div>
                    <div class="col-xl-3 col-md-3 al_middle">
                        <a href="/etablissement/accueil" class="btn btn-info " >Retour</a>
                    </div>
                </div>
                <form class="container-fluid" action="/etablissement/course_add" id="courseform">
                    <div class="row m-0">
                        <h3 class="form-title">Créer un cours</h3>
                    </div>
                    <div class="form-row mb-3">
                        <div class="form-group col-md-6">
                            <label>Thème du cours</label>
                            <input name="theme" class="form-control" type="text" placeholder="Thème du cours" form="courseform" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label >Nombre d'heures</label>
                            <input name="nbHours" class="form-control" type="number" placeholder="Nombre d'heures" form="courseform" required>
                        </div>
                        <div class="form-group col-md-2 al_bottom">
                            <button formmethod="post" form="courseform" type="submit" class="btn btn-success">Créer</button>
                        </div>
                    </div>
                </form>
                <form class="container-fluid" action="/etablissement/course_link" id="studentform">
                    <div class="row m-0">
                        <h3 class="form-title">Associer un étudiant à un cours</h3>
                    </div>
                    <div class="form-row mb-3">
                        <div class="form-group col-md-5">
                            <label for="student_id">Choisir un étudiant</label>
                            <select class="form-control" name="student_id" id="student_select" form="studentform" required>
                                <option value="">--Sélectionner un étudiant--</option>
                                <c:forEach items="${students}" var="student">
                                    <option value="${student.getId()}"><c:out value="${student.getLastname()}"/> <c:out value="${student.getFirstname()}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-5">
                            <label>Choisir un cours</label>
                            <select class="form-control" name="course_id" id="course_select" form="studentform" required>
                                <option value="">--Sélectionner un cours--</option>
                                <c:forEach items="${courses}" var="course">
                                    <option value="${course.getId()}"><c:out value="${course.getCourseSubject()}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-2 al_bottom">
                             <button formmethod="post" form="studentform" type="submit" class="btn btn-warning">Associer</button>
                        </div>
                    </div>
                </form>
                <div class="row ml-4 mar-none">
                    <p class="warning" style="color:red;">${ error }</p>
                    <p class="success" style="color:green;">${ success }</p>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>