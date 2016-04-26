# Openpay Scruds

This project is intended to integrate a full web project for credit card, direct debit,
 convenience stores and bank reference as payment methods using [Openpay](http://www.openpay.mx/ "Openpay") provider

##Prerequisites
*   Java jdk 1.7+
*   Apache tomcat 7+
*   Maven 3.x



##Installation


###Follow the instructions 

* Check/update your tomcat credentials in $CATALINA_HOME/conf/tomcat-users.xml

 ```xml
 <role rolename="manager"/>
 <role rolename="admin"/>
 <role rolename="manager-gui"/>
 <role rolename="manager-script"/>
 <user username="admin" password="admin" roles="admin,manager,manager-gui,manager-script"/>
 ```

* Clone this repo and update the pom.xml using your tomcat credentials and hostname
 ```xml
 <configuration>
  <path>/${project.artifactId}</path>
  <update>true</update>
  <url>http://localhost:8080/manager/text</url>
  <username>admin</username>
  <password>admin</password>
 </configuration>
 ```

* Tomcat must be running first 
* From command line type:
 ```
 mvn clean install tomcat7:redeploy
 ```

* Start your browser in:

 ```
 http://{host}:{port}/openpay-scruds/openpay/choosepayment.html
 ```

* Test the examples and watch your browser console to see the outputs

* Here are some [credit card numbers](http://www.openpay.mx/docs/testing.html "card numbers")  for testing

* Test classes are included for unit scruds

* Full doc of openpay API here: http://www.openpay.mx/docs/api/

