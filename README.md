WEB
=======================
Prerequisite
- Java is installed and environment variable is set
- Maven environment variable

READ BELOW GUIDE TO SETUP VARIABLES
https://www.mkyong.com/maven/how-to-install-maven-in-windows/


GO TO PROJECT REPOSITORY USING TERMINAL ....

Basic Commands:
>>>mvn clean verify -Denv=prod

### Anything else?

You can specify which environment to use by using one of the following switches:

- -Denv=prod
- -Denv=sandbox
- -Denv=dev


### Test Execution Reports
Test execution reports can be found in the folder 'target' after test execution and the simple report can be found here
- -target/surefire-reports/emailable-report.html 
