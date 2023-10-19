## :pushpin: SOAPUI-Extentter (Important)
The pro version for this utility have some more features like Automatic Email Reporting. please email for further details. [Akshay Sharma](mailto:akshay.sharma979@gmail.com)

## :pushpin: Not Interested in Reading ? Watch video and setup
[<img src="https://img.youtube.com/vi/IAefMoBb0nI/0.jpg" width="100%">](https://www.youtube.com/watch?v=IAefMoBb0nI "Integrate Extent Report with SOAPUI")


## :pushpin: SOAPUI-Extentter
This is an easy utility create to generate extent report based on SOAPUI project execution. This utility supports SOAPUI PROJECT execution report, SOAPUI TESTSUITE execution report and you can also generate SOAPUI Individual TestCase execution report.

## :pushpin: Sample Extent Report
https://iamakshayshar.github.io/

## :pushpin: What is Extent Report & Klov Report?
Extent Report is a HTML based reporting library which is used for making excellent execution reports and simple to understand. This can be used with TestNG automation framework when using Selenium WebDriver for JAVA projects. In this utility I have utilized the listener capability of SOAPUI to generate Extent Report based on PROJECT execution and TESTSUITE execution. Extent Report provide good detailed execution report with graphical representation of Pass and Failed testcases.

As an Automation Test Engineer we are supposed to find the defects and report it to the team in a simple way which should be easy to understand. In Automation Testing, importance of reporting is so high. Extent Report provides the easy way to report the Pass and Failed testcases.

Klov – ExtentReports reporting library by ExtentReports. Extent Report team extended this library for historical reporting. Extent Report is a web application using MongoDB and Spring Framework. You can track your builds, their execution results aligned with it by using the web application and compare the builds with older builds.

Lets understand more on what is required to use this utility and how does it works

## :pushpin: Pre-requisite to use SOAPUI-Extentter:
1. Java should be installed (by default it comes with SOAPUI installation)
2. You should have the admin access of SOAPUI installation folder.
3. Klov should be setup correctly and should be up and running along with MongoDB.

## :pushpin: How SOAPUI-Extentter works:
SOAPUI doesn’t support reporting by default and thus with all the good features what SOAPUI provides there is always a limitation that we can't have good report of our execution. With SOAPUI-Extentter library, one can generate extent report with just placing one jar file. All the code/libraries required to generate extent report is handled in this repository.

This utility leverages the listener which is provided by SOAPUI along with the installation. SOAPUI provides three main listeners as below,

* ProjectRunListener
* TestSuiteRunListener
* TestRunListener

ProjectRunListener listens to execution at project level and provides methods as beforeRun, afterRun, beforeTestSuite and afterTestSuite. All we have to do is to understand when this methods are called when we are executing the overall project. 
Similarly TestSuiteRunListener listens to execution at TestSuite level and provides methods as afterRun, beforeRun, beforeTestCase and afterTestCase. 
Lastly TestRunListener listens to execution at TestCase level and provides methods as beforeRun, afterRun, beforeStep and afterStep. I have implemented these methods to generate extent report from Project, TestSuite and TestCase execution.

## :pushpin: SOAPUI Extenter jar file
Download Jar & Listener-XML file from latest release from below location,
1. Refer to the latest release in GIT,
	https://github.com/iamakshayshar/SoapUI-Extentter/releases

## :pushpin: Steps to use SOAPUI-Extentter utility:

- If you only want to generate ExtentReport & Klov Report, Follow steps below based on what you are looking for:
	
	* If one wants to use this as it is follow the steps below,

		1. Copy the jar file downloaded from above google drive and place it under "${SOAPUI_HOME}/bin/ext" folder.
		2. Copy the xml file from "Listener-XML" folder and place it under "${SOAPUI_HOME}/bin/listeners" folder.
		3. Execute the project or testsuite and you will have extent report generate in "Reports" folder where your project XML is saved.

	* If one wants to customize this utility then follow the steps below,

		1. Complete the change as what is required.
		2. Build the project
		3. Generate runnable jar of the project.
		4. Copy the generate jar file and place it under "${SOAPUI_HOME}/bin/ext" folder.
		5. Execute the project or testsuite to validate the change.


	* If one wants to generate Klov report along with ExtentReport, follow the steps below,

		1. Update the project properties with the Name and Values as Below,
		2. Copy the jar file downloaded from above google drive and place it under "${SOAPUI_HOME}/bin/ext" folder.
		3. Copy the xml file from "Listener-XML" folder and place it under "${SOAPUI_HOME}/bin/listeners" folder.
		4. Execute the project or testsuite and you will have extent report generate in "Reports" folder where your project XML is saved.
	
![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/KlovConfig.JPG?raw=true)
		
	*Please update the values for three (MongoDBIP, MongoDBPort & KlovServerUrl) properties.	

## :pushpin: Additional Steps to configure in SOAPUI project (If Required):
Although there is zero configuration required in SOAPUI project, but if you have added an Author for each TestSuite as Custom Property, [Author (Key) : AuthorName (Value)] it will be added in Extent Report itself. No other change is required.

1. Add Author Name in TestSuite Custom Property. (It should be "Author" [Case Sensitive])

![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/SoapUI-TestSuite.JPG?raw=true)

2. Verify the Author information in Extent Report,

![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/ExtentReport-Author.JPG?raw=true)

3. Detailed Report in Extent Report

![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/ExtentReport-Detailed.JPG?raw=true)

If you want the project properties should not be added to Extent Report Dashboard, then configure project properties and add a property with the name "AddDataToReport" and assign value to it as FALSE. The value for this property accepts only Boolean value and if the value is neither TRUE nor FALSE, then default it is considered as TRUE.

If the value if TRUE, then the project properties are added to Extent Report Dashboard. 

![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/AddDataToReport_Properties-True.JPG?raw=true)

If the value if FALSE, then the project properties are NOT added to Extent Report Dashboard. 

![alt text](https://github.com/iamakshayshar/SoapUI-Extentter/blob/master/Images/AddDataToReport_Properties-False.JPG?raw=true)

## :pushpin: AddOn with this utility:
As an addOn to this utility, I have also added code for data driven testing support inside SOAPUI. This git repo also have one sample project (calculator-soapui-project_Updated.xml) which have all the groovy code for data driven testing.

## :pushpin: Support:
* This utlity is tested with SOAPUI version 5.3.0, 5.4.0, 5.5.0, 5.6.0, 5.7.0 & 5.7.1
* With version 4.0, this utility is supported and tested with ReadyAPI version 3.9.0, 3.9.1 & 3.9.2
* This utlity is testing on JAVA version "1.8.0_221".
* This utlity is build on top of ExtentReport v5.0.9
* This utility supports Klov Docker release 1.0.1

## :pushpin: License
SoapUI-Extentter is an Open Source Plugin and 4.0 and later will be released under Apache-2.0
