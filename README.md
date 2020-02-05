# NoteBookInterpreter

This is a solution that interprets a python code 

1- Prerequites:
Java 8
Tomcat 
MySql

2- Before Starting:
User Have to install an mysql instance named : springSession that stores the user session information

3- Installation:

After pulling the project from the git URL you have to build it :
mvn clean package

You will retrieve the jar in the target folder, then you can run it:

java -jar NoteBookInterpreter-0.0.1-SNAPSHOT.jar

4- Test Cases in Postman:

1- Echo Method : http://localhost:8080/echo

{"code":"%python a = 5","idSession":"noSession"}
will give : {    "resultat": "" }

{"code":"%python print a + 15","idSession":"noSession"} 
will give : {    "resultat": "30" }

{"code":"%python print 5 + 15","idSession":"noSession"}
will give : {    "resultat": "20"}

2- Excecute method http://localhost:8080/execute

{"code":"%python cder = 15","idSession":"7362"}
will give : {    "resultat": "" }

{"code":"%python print 6 + cder","idSession":"7362"}
will give : {    "resultat": "21"}

Now if we try to test with other idSession 
{"code":"%python print 6 + cder","idSession":"9362"}
We'll have a BAD_REQUEST



