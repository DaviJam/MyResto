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
        <title>Liste des étudiants</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="cst-form ">
                <div class="row">
                    <h1 class="form-title">Liste des étudiants</h1>
                    <a href="/etablissement/students_menu" class="btn btn-info" style="position: absolute; left: 90%;">Retour</a>
                </div>
                <div class="container-fluid">
                    <div class="table-responsive">
                        <table class="table table-striped table-sm" action="/etablissement/students_update" id="userform">
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col" class="table-item">
                                        <label class="mar-none" >Nom</label>
                                    </th>
                                    <th scope="col" class="table-item">
                                        <label class="mar-none" >Prénom</label>
                                    </th>
                                    <th scope="col" class="table-item ">
                                        <label class="mar-none" >Mail</label>
                                    </th>
                                    <th scope="col" class="table-item">
                                       <label class="mar-none" >Date de naissance</label>
                                    </th>
                                    <th scope="col" class="table-item">
                                       <label class="mar-none" >Adresse</label>
                                    </th>
                                    <th scope="col" class="table-item">
                                       <label class="mar-none" >Téléphone</label>
                                    </th>
                                    <th scope="col" colspan="2" class="table-item">
                                        <label class="mar-none" >Action</label>
                                        <a>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${students}"  var="student">
                                <tr>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getLastname()}" /></label>
                                        <input type="hidden" name="id" value='<c:out value="${student.getId()}" />'/>
                                    </td>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getFirstname()}" /></label>
                                    </td>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getMailAddress()}" /></label>
                                        <input type="hidden" name="email" value='<c:out value="${student.getMailAddress()}" />'/>
                                    </td>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getDateOfBirth()}" /></label>
                                    </td>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getAddress()}" /></label>
                                    </td>
                                    <td class="table-item">
                                        <label class="table-item mar-none" ><c:out value="${student.getPhoneNumber()}" /></label>
                                    </td>
                                    <td class="table-item">
                                        <a href="/etablissement/students_update" class="btn btn-warning">Modifier</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>