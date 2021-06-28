cd  C:\xampp\tomcat\bin
start C:\xampp\tomcat\bin\startup.bat
cd C:\Users\daori\Documents\ECOLE\Dada\Gestion_Etablissement
mvn clean
mvn install -Pclient 
Copy-Item -Path C:\Users\daori\Documents\ECOLE\Dada\Gestion_Etablissement\GestionEtablissement-presentation\target\etablissement.war -Destination C:\xampp\tomcat\webapps -PassThru
timeout /T 10 /NOBREAK 
start chrome http://localhost:8080/etablissement