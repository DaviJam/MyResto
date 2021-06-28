<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/padding.css">
        <link rel="stylesheet" href="css/margin.css">
        <link rel="stylesheet" href="css/outline-hover.css">
        <title>Modifier un étudiant</title>
    </head>
    <body>
    <script>
    document.addEventListener('DOMContentLoaded', () => {
       var students = ${students_json};

        function getUserByID(id) {
          return students.filter(
              function(students){ return students.id == id }
          );
        }

        document.querySelector('#student_select').addEventListener("change", function() {
            document.querySelector(".success").innerText ="";
            document.querySelector(".warning").innerText ="";

            let user = getUserByID(this.value)
            console.log(user);
            if(Array.isArray(user) && user.length > 0)
            {
                $('input[name="Surname"]').val(user[0].lastname);
                $('input[name="Name"]').val(user[0].firstname);
                $('input[name="Email"]').val(user[0].mailAddress);
                $('input[name="BDate"]').val(user[0].dateOfBirth);
                $('input[name="address"]').val(user[0].address);
                $('input[name="tel"]').val(user[0].phoneNumber);
                $('input[name="ID"]').val(user[0].id);

            }
            else
            {
                $('form#userform :input').each( function() {
                    this.value ="";
                });
            }
        });

        $('#userDel').submit(function() {
            var retVal = false;
            if($('input[name="ID"]').val() != "")
            {
                $('input[name="id"]').val($('input[name="ID"]').val());
                retVal = true;
            }
            else
            {
                $('p[class="warning"]').text("Veuillez remplir tous les champs.");
            }
            return retVal;
        });
    });
    </script
        <div class="form-row">
            <div class="form-group col-md-12">
                <div class="container conform">
                    <div class="cst-form">
                        <div class="row">
                            <h1 class="form-title">Modifier un étudiant</h1>
                        </div>
                        <form class="container-fluid" action="/etablissement/students_update" id="userform">
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label for="student_select" form="userform" >Choisir un étudiant</label>
                                    <select class="form-control" name="students" id="student_select" required>
                                        <option value="">--Sélectionner un étudiant--</option>
                                        <c:forEach items="${students}" var="student">
                                            <option value="${student.getId()}"><c:out value="${student.getLastname()}"/> <c:out value="${student.getFirstname()}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>Nom</label>
                                    <input form="userform" name="Surname" class="form-control" type="text" placeholder="Nom" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Prenom</label>
                                    <input form="userform" name="Name" class="form-control" type="text" placeholder="Prenom" required>
                                    <input name="ID" class="form-control" type="number" placeholder="id" value="" hidden>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>Email</label>
                                    <input form="userform" name="Email" class="form-control" type="email" placeholder="Email" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Date de naissance</label>
                                    <input form="userform" name="BDate" class="form-control" type="text" placeholder="Date de naissance" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label>Adresse</label>
                                    <input form="userform" name="address" class="form-control" type="text" placeholder="Adresse" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-5">
                                    <label>Numéro de téléphone</label>
                                    <input form="userform" name="tel" class="form-control" type="tel" placeholder="+XX X XX XX XX XX" required>
                                </div>
                                <div class="form-group col-md-7" style="align-self: flex-end;">
                                    <p class="success" style="color:green;">${ success }</p>
                                </div>
                            </div>
                        </form>
                        <div class="row my-0 mar-l-2">
                            <p class="warning" style="color:red;">${ error }</p>
                        </div>
                        <div class="row mar-t-none">
                            <div class="col al-center">
                                <a href="/etablissement/students_menu" class="btn btn-primary">Annuler</a>
                            </div>
                            <div class="col al-center">
                                <button formmethod="post" form="userform" type="submit" class="btn btn-warning">Modifier</button>
                            </div>
                            <div class="col al-center">
                                <form action="/etablissement/students_remove" method="post" id="userDel">
                                    <input form="userDel" type="hidden" name="id" required/>
                                    <button id="delBtn" class="btn btn-danger" form="userDel" type="submit">Supprimer</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
        <script src="js/app.js"></script>
    </body>
</html>