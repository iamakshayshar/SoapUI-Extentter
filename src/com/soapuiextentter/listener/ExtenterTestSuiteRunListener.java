package com.soapuiextentter.listener;

import java.io.File;
import java.util.HashMap;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestSuiteRunContext;
import com.eviware.soapui.model.testsuite.TestSuiteRunListener;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;
import com.soapuiextentter.service.SoapUIService;
import com.soapuiextentter.service.SoapUIServiceImpl;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This class implements methods of TestSuiteRunListener of SOAPUI. This will listen to TestSuite level actions.
 */

public class ExtenterTestSuiteRunListener implements TestSuiteRunListener {

	public static SoapUIService TSservice = null;
	public String reportPath;

	public void afterRun(TestSuiteRunner runner, TestSuiteRunContext context) {
		// SoapUI.log("Inside AfterRun in TestSuiteRunListener - 10");
		try {
			if (ExtenterProjectRunListener.Projservice == null) {
				TSservice.finishReporting();
				TSservice = null;
				SoapUI.log("Reports are published at " + reportPath);
			}
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  beforeTestCase of TestSuiteRunListener " + t.getMessage());
		}
	}

	public void beforeRun(TestSuiteRunner runner, TestSuiteRunContext context) {
		// SoapUI.log("Inside BeforeRun in TestSuiteRunListener - 3");
		try {
			if (ExtenterProjectRunListener.Projservice == null) {
				TSservice = new SoapUIServiceImpl();

				String projectXmlPath = context.getTestSuite().getProject().getPath();
				int index = projectXmlPath.lastIndexOf(File.separator);
				reportPath = projectXmlPath.substring(0, index);
				reportPath = reportPath + File.separator + "Reports";

				String testSuiteName = context.getTestSuite().getName();
				String testSuiteDesc = context.getTestSuite().getDescription();
				String testSuiteId = context.getTestSuite().getId();

				HashMap<String, String> klovConfig = new HashMap<String, String>();
				klovConfig.put("MongoDBIP", "");
				klovConfig.put("MongoDBPort", "");
				klovConfig.put("KlovServerUrl", "");

				TSservice.startReporting(reportPath, testSuiteName, klovConfig);
				TSservice.startTestSuiteLogging(testSuiteName, testSuiteDesc, testSuiteId);
			}
		} catch (Exception t) {
			SoapUI.log("SOAPUI Extentter plugin cannot be initialized. " + t.getMessage());
		}
	}

	public void beforeTestCase(TestSuiteRunner paramTestSuiteRunner, TestSuiteRunContext paramTestSuiteRunContext,
			TestCase paramTestCase) {
		// SoapUI.log("Inside BeforeTestCase in TestSuiteRunListener - 4");
		try {
			if (ExtenterProjectRunListener.Projservice != null) {
				String testCaseName = paramTestCase.getName();
				String testSuiteId = paramTestSuiteRunContext.getTestSuite().getId();
				String testCaseId = paramTestCase.getId();
				ExtenterProjectRunListener.Projservice.startTestCaseLogging(testCaseName, testSuiteId, testCaseId);
			} else {
				String testCaseName = paramTestCase.getName();
				String testSuiteId = paramTestSuiteRunContext.getTestSuite().getId();
				String testCaseId = paramTestCase.getId();
				TSservice.startTestCaseLogging(testCaseName, testSuiteId, testCaseId);
			}
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  beforeTestCase of TestSuiteRunListener " + t.getMessage());
		}
	}

	public void afterTestCase(TestSuiteRunner paramTestSuiteRunner, TestSuiteRunContext paramTestSuiteRunContext,
			TestCaseRunner paramTestCaseRunner) {
		// SoapUI.log("Inside AfterTestCase in TestSuiteRunListener - 9");
	}
}