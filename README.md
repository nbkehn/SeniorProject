# 2019FallTeam16
Ubuntu Setup (Works on Windows Subsystem)

Install Java/JDK 11
http://ubuntuhandbook.org/index.php/2018/11/how-to-install-oracle-java-11-in-ubuntu-18-04-18-10/

Setup JAVA_HOME and PATH variables to point to /usr/lib/jvm/java-11-openjdk-amd64/  (or whatever JDK you have)
https://vitux.com/how-to-setup-java_home-path-in-ubuntu/
- Do not reinstall JDK, you should have installed it in the previous step or already have it installed

Install Gradle and Spring
Only do section Install Spring Boot CLI
https://www.linode.com/docs/development/java/how-to-deploy-spring-boot-applications-nginx-ubuntu-16-04/
- Could also use brew (ask Will)

Install MySQL 
sudo apt-get update
sudo apt-get install mysql-server

Start MySQL
sudo /etc/init.d/mysql start

Enter MySQL
sudo /usr/bin/mysql -u root -p

Create a MySQL user and DB from the MySQL terminal (and then exit)
CREATE USER '<insert username>'@'localhost' IDENTIFIED BY '<insert password>';
GRANT ALL ON *.* TO '<insert username>'@'localhost' WITH GRANT OPTION;
CREATE DATABASE team16f2019;
exit;

Alter server/src/main/resources/application.properties to include new DB credentials
application.properties.template --> application.properties

Build Gradle (starting in project directory)
cd server
./gradlew build

Run Gradle
java -jar build/libs/scheduler-0.0.1-SNAPSHOT.jar
