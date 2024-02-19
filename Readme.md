#Identify New Bikes

**Project Overview**
 
This Selenium automation testing project focuses on automating tasks related to the zigwheels.com Website. The primary objectives includes searching of upcoming bikes manufactured by Honda with price less than 4Lakks, showing all the Bike models availables in page along with price and Launch date. Again Navigating through the website, search for the used cars in the Chennai Location, and capture all the popular model available in page. At last try to 'Login' with google, give invalid account details & capture the error message. This project uses various dependencies and libraries to facilitate automation. The whole project is developed using a framework which contains several page objects under which there are classes which describes the functionality of the program.
 
**Project Structure**
 
1.Maven Repository
 
- Version: 3.12.1
- Purpose: Used for project Management and dependency resolution.
 
2.Dependencies
  
TestNG
- Version: 7.9.0
- Purpose: Framework for test automation that allows for parallel execution and flexible test configuration.
 
Extent Report
- Version: 5.1.1
- Purpose: Generates interactive and detailed HTML reports to enhance test result analysis.
 
Selenium
- Version: 4.15.0
- Purpose: Enables interaction with web elements, navigation, and form submission in the browser.

 
**Automation Test Flow**
 
1. Open Chrome/Edge browser

- Search for the zigwheels.com website.
 
2. Checking if the correct website of zigwheels.com opened or not 

- Close all the popUps and alert available in the website.

3. Hover on the New Bikes 

- Move tooltip on the NewBikes Dropdown.
- Capture screenshot of options available under dropdown.
- Click on the upcoming bikes option.

4. Check the New bikes page opened with details

- Click on the manufacturer as Honda 
- Print the number of available upcoming bikes under Honda in New bikes page.
- Capture all the details displayed.
- Details of Model name, price and Launch date should print.
 
5. Hover on the Used Cars

- Move tooltip on the Used cars Dropdown.
- Capture screenshot of options available under Used cars dropdown.
- Select location as Chennai.
- Print all the popular brand available in used car page.

- Click on the home page button.
- Navigate to login/signUp link
- Click on the Login 
- Provide Invalid email-Id and capture the error message


**Screenshots**

- Taking the screenshot after clicking on every dropdown available in the Header of Be.cognizant.

**How to Run the Tests**
 
1. Open Eclipse IDE:
- Launch Eclipse IDE on your machine.
 
2. Import Project:
- Select 'File' -> 'Import' from the menu.
- Choose 'Existing Maven Projects' and click 'Next'.
- Browse to the directory where you cloned the repository and select the project.
 
3. Update Maven Project:
- Right-click on the project in the Project Explorer.
- Choose 'Maven' -> 'Update Project'.
- Click 'OK' to update dependencies.
 
4. Run Test Suite:
- Locate the test suite file (e.g., 'src/test/java/TestSuite.java').
- Right-click on the file and choose 'Run As' -> 'TestNG Suite'.
 
6. View Reports:
- After execution, open the 'report' folder.
- Find the Extent Report HTML file ('myReport.html') for detailed test reports.
 
**Author Information**
 
- Yashodha Goravar(2303516)


**Disclaimer**
 
This project is intended for educational and testing purposes only. The authors are not responsible for any unauthorized use or modification of the code. Use at your own risk.
