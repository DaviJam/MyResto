<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="fs" lang="en">
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
        <div class="container-md pt-2" style="position: relative; height:80vh; width:100vw">
            <a href="/etablissement/accueil" class="btn btn-info " style="position:absolute; right:5%;">Retour</a>
            <div row>
                <canvas id="myChart" width="400px" height="400px"></canvas>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.2.1/dist/chart.min.js" integrity="sha256-uVEHWRIr846/vAdLJeybWxjPNStREzOlqLMXjW/Saeo=" crossorigin="anonymous"></script>
        <script>

            const labels = [
              'Entre 0 et 8 de moyenne',
              'Entre 8 et 12 de moyenne',
              'Entre 12 et 17 de moyenne',
              'Entre 17 et 20 de moyenne',
            ];

            const data = {
                labels: labels,
                datasets: [
                {
                    label: 'Mauvais',
                    data: ['${mauvais}', 0, 0, 0],
                    backgroundColor:  'rgba(240,173,78,0.9)',
                    borderColor: 'rgba(240,173,78,1)',
                    borderWidth: 1,
                },
                {
                    label: 'Moyen',
                    data: [0, '${moyen}', 0, 0],
                    backgroundColor:  'rgba(217,83,79,0.9)',
                    borderColor: 'rgba(217,83,79,1)',
                    borderWidth: 1,
                },
                {
                    label: 'bon',
                    data: [0, 0, '${bon}', 0],
                    backgroundColor:  'rgba(92,184,92,0.9)',
                    borderColor: 'rgba(92,184,92,1)',
                    borderWidth: 1,
                },
                {
                    label: 'excellent',
                    data: [0, 0, 0, '${excellent}'],
                    backgroundColor:  'rgba(2,117,216,0.9)',
                    borderColor: 'rgba(2,117,216,1)',
                    borderWidth: 1,
                }
            ]
            };

            var config = {
                type : 'bar',
                data : data,
                options : {
                    responsive: true,
                    plugins : {
                        title : {
                            text : 'Classement des étudiants par moyenne',
                            color : 'white',
                            display : true,
                            font : {
                                size : 20,
                            }
                        },
                        legend : {
                            display : true,
                            labels : {
                                color : 'white',
                            }
                        }
                    },
                    scales : {
                        y : {
                            beginAtZero: true,
                            grid : {
                                color : 'rgba(255,255,255,0.4)',
                            },
                            ticks: {
                                color : 'white',
                            },
                            title: {
                                text: 'Pourcentage',
                                color: 'white',
                                font : {
                                    size: 15,
                                },
                                display : true,
                            }
                        },
                        x : {
                            grid : {
                                color : 'rgba(255,255,255,0.4)',
                            },
                            ticks: {
                                color : 'white',
                            },
                            title: {
                                text: 'Niveau',
                                color: 'white',
                                font : {
                                    size: 15,
                                },
                                display : true,
                            }
                        },

                    }
                }
            };
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, config);

        </script>
    </body>
</html>