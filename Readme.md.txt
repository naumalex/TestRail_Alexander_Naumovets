**Product description**
The repository containes Selenium tests for TestRail. TestRail is a popular test case managment tool that helps to manage test cases, plans and runs.

#**Tools, Libraries, Frameworks used** 
#Technology Used: Java, Selenium, Maven, TestNG, Allure, REST Assured library, java faker, maven surifier plugin, lombok. 
Patterns Used: Page Object Model, Steps, Decorator, Factory, Data Provider, Value Object, Builder, Loadable Component, Chain of Invocation. 
Prerequisites Installed: Java 18+, Maven, Allure.

#**Checklist**
	1. Add project using Add Project button on the Dashboard page. Verify that correct message about successful adding is shown. 
Verify that added project is shown on the Projects page.
	2. Edit a project. Verify that correct message about successful update is shown. Verify that the name of the modified project has been updated in the Projects list. 
Verify that entered data was correctly saved. 
	3. Delete project. Verify that correct message about successful deleting is shown. Verify that the project disappeared from the Projects page." 
	4. Add a test case using Text template. Verify that correct message about successful adding is shown. 
Verify that correct data is shown on the Test Case Details page.
	5. Add a test case using Steps template. Verify that correct message about successful adding is shown. 
Verify that correct data is shown on the Test Case Details page. 
	6. Add a test case using Explaroraty session template. Verify that correct message about successful adding is shown. 
Verify that correct data is shown on the Test Case Details page.
	7. Edit a text test case. Verify that correct message about successful updating is shown. Verify that correct data is shown on the Test Case Details page.
	8. Delete a test case. Verify that deleted test cases disappeared from the test cases list.
	9. Add a Test run. Verify that all the test cases in the project has been included to the test run.
	10. Add test result to a test run. Verify that summary panel is correctly updated.

#**Test Suits**
project.xml
test_case.xml
test_run.xml

#**How to run tests**
run all the tests
mvn clean test

run project suite
mvn clean test -DsuiteXmlFile="project.xml"

run test_case suite
mvn clean test -DsuiteXmlFile="test_case.xml"

run test_run suite
mvn clean test -DsuiteXmlFile="test_run.xml"

#**Supported Browsers**
Chrome
Firefox

run tests in Firefox
mvn clean test -Dbrowser="firefox"

 

  