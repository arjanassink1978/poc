##### **POC**
POC is a testsuite for testing the startup '“Internet Movie TestCoders Database”'
The testsuite uses Restassured,Lombok,Cucumber

**How to Build the project**

`run gradlew build`

**How to run a single feature/scenario**

* right-click on the feature-file and select run to run the entire scenario
  open feature file and click run at the left of the scenario to run a scenario

**How to run all Feature-files with the runner**

* in Intellij right-click the runner (CucumberJunitRunner) and select run in Jenkins use gradlewWrapper (make executable)
  and set task too `-test --rerun-tasks --warning-mode all`

**How to run a single feature/scenario**

* right-click on the feature-file and select run to run the entire scenario
  open feature file and click run at the left of the scenario to run a scenario

**How to run all Feature-files with the runner**

* in Intellij right-click the runner (CucumberJunitRunner) and select run in Jenkins use gradlewWrapper (make executable)
  and set task too `-test --rerun-tasks --warning-mode all`

**Configuration**
* The project uses a config.properties file. Where you can set the correct url's
* There is also the option to use enviroment properties. The PropertyHelper class first checks for enviroment properties
  if they are not set it will use the config.properties file.

   