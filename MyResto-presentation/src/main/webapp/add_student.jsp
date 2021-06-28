<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <title>Title</title>
    </head>
    <body>
        <div class="container conform">
            <div class="cst-form">
                <div class="row">
                    <h1 class="form-title">Ajouter un étudiant</h1>
                </div>
                <form class="container-fluid" action="/etablissement/students_add" id="userform">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Nom</label>
                            <input name="Surname" class="form-control" type="text" placeholder="Nom" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Prenom</label>
                            <input name="Name" class="form-control" type="text" placeholder="Prenom" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Email</label>
                            <input name="Email" class="form-control" type="email" placeholder="Email" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Date de naissance</label>
                            <input name="BDate" class="form-control" type="date" placeholder="Date de naissance" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label>Adresse</label>
                            <input name="address" class="form-control" type="text" placeholder="Adresse" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-5">
                            <label>Numéro de téléphone</label>
                            <input name="tel" class="form-control" type="tel" placeholder="+XX XX XX XX XX" required>
                        </div>
                    </div>
                </form>
                <div class="row mar-b-none">
                    <p class="warning" style="color:red;">${ error }</p>
                </div>
                <div class="row mar-t-none">
                    <div class="col al-center">
                        <a href="/etablissement/students_menu" class="btn btn-primary">Annuler</a>
                    </div>
                    <div class="col al-center">
                        <button formmethod="post" form="userform" type="submit" class="btn btn-outline-primary">Ajouter</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>