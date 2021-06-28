<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="css/accueil-style.css">
        <title>Gestion d'établissement Ensup</title>
    </head>
    <body>
        <c:choose>
          <c:when test="${not empty sessionScope}">
             <c:set var="classVal" value="" scope="application" />
             <c:set var="areaVal" value="" scope="application" />
             <c:set var="styleVal" value="none" scope="application" />
             <c:set var="hrefVal" value="/etablissement/logout" scope="application" />
             <c:set var="userVal" value="content" scope="application" />
             <c:set var="textVal" value="Deconnexion" scope="application" />
          </c:when>
          <c:otherwise>
             <c:set var="classVal" value="disabled" scope="application" />
             <c:set var="areaVal" value="true" scope="application" />
             <c:set var="styleVal" value="content" scope="application" />
             <c:set var="hrefVal" value="/etablissement/login" scope="application" />
             <c:set var="userVal" value="none" scope="application" />
             <c:set var="textVal" value="Connexion" scope="application" />
          </c:otherwise>
        </c:choose>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a href="/etablissement/accueil">
                <img class="logo" src="img/logo.png" alt="LOGO">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">

                            <a class="nav-link ${classVal}" aria-disabled="${areaVal}" href="#">Organisation</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${classVal}" aria-disabled="${areaVal}" href="#">Planning</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Gestion
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item ${classVal}" aria-disabled="${areaVal}" href="/etablissement/students_menu">Gérer les étudiants</a>
                                <a class="dropdown-item ${classVal}" aria-disabled="${areaVal}" href="/etablissement/average_menu">Gérer les notes</a>
                                <a class="dropdown-item ${classVal}" aria-disabled="${areaVal}" href="/etablissement/course_menu">Gérer les cours</a>
                                <a class="dropdown-item ${classVal}" aria-disabled="${areaVal}" href="/etablissement/statistics">Voir les satistiques</a>
                            </div>
                        </li>
                    </ul>
                </div>


                <div class="my-2 my-lg-0 right">
                    <img class="userlogo" style="display: ${userVal};" src="https://img.icons8.com/ios-filled/50/000000/user-male-circle.png"/>
                    <a href="${ hrefVal }" class="btn btn-outline-info my-2 my-sm-0" type="submit">${textVal}</a>
                    <button class="btn btn-outline-dark ml-2 my-2 my-sm-0" style="display: ${styleVal};" type="submit">Créer un compte</button>
                </div>
            </div>
        </nav>


        <h1 class="mainTitle">ACCUEIL</h1>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>
