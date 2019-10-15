# 2019FallTeam16
Ubuntu Setup (Works on Windows Subsystem)

#### Install Java/JDK 11
http://ubuntuhandbook.org/index.php/2018/11/how-to-install-oracle-java-11-in-ubuntu-18-04-18-10/

#### Setup JAVA_HOME and PATH variables to point to /usr/lib/jvm/java-11-openjdk-amd64/  (or whatever JDK you have)
https://vitux.com/how-to-setup-java_home-path-in-ubuntu/ <br>
- Do not reinstall JDK, you should have installed it in the previous step or already have it installed

#### Install Gradle and Spring
*Only do section Install Spring Boot CLI*
https://www.linode.com/docs/development/java/how-to-deploy-spring-boot-applications-nginx-ubuntu-16-04/ <br>
- Could also use brew (ask Will) <br>

#### Install MySQL 
sudo apt-get update <br>
sudo apt-get install mysql-server <br>

#### Start MySQL
sudo /etc/init.d/mysql start <br>
Windows: cd "C:\Program Files\MySQL\MySQL Server *version* \bin\" <br>

#### Enter MySQL
sudo /usr/bin/mysql -u root -p <br>

#### Create a MySQL user and DB from the MySQL terminal (and then exit)
CREATE USER '*insert username*'@'localhost' IDENTIFIED BY '*insert password*'; <br>
GRANT ALL ON *.* TO '*insert username*'@'localhost' WITH GRANT OPTION; <br>
CREATE DATABASE team16f2019; <br>
exit; <br>

#### Alter server/src/main/resources/application.properties 
- include new DB credentials
- rename application.properties.template --> application.properties

#### Sign in to MySql
mysql -u *username* -p; (no semicolon for Windows) <br>
USE team16f2019;
  
#### Build Gradle (starting in project directory)
cd server <br>
./gradlew build <br>

#### Run Gradle
java -jar build/libs/scheduler-0.0.1-SNAPSHOT.jar <br>
