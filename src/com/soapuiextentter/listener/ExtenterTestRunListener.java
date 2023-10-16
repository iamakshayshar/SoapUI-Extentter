package com.soapuiextentter.listener;

import java.io.File;
import java.util.HashMap;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunListener;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.soapuiextentter.service.SoapUIService;
import com.soapuiextentter.service.SoapUIServiceImpl;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This class implements methods of TestRunListener of SOAPUI. This will listen to TestStep level actions.
 */

public class ExtenterTestRunListener implements TestRunListener {

	public static SoapUIService TCservice = null;
	public String reportPath;

	@Override
	public void beforeRun(TestCaseRunner runner, TestCaseRunContext context) {
		// SoapUI.log("Inside BeforeRun in TestRunListener - 1");
		try {
			if (ExtenterProjectRunListener.Projservice == null && ExtenterTestSuiteRunListener.TSservice == null) {
				TCservice = new SoapUIServiceImpl();

				String projectXmlPath = context.getTestCase().getTestSuite().getProject().getPath();
				int index = projectXmlPath.lastIndexOf(File.separator);
				reportPath = projectXmlPath.substring(0, index);
				reportPath = reportPath + File.separator + "Reports";

				String testCaseName = context.getTestCase().getName();
				String testCaseDesc = context.getTestCase().getDescription();
				String testCaseId = context.getTestCase().getId();
				HashMap<String, String> klovConfig = new HashMap<String, String>();
				klovConfig.put("MongoDBIP", "");
				klovConfig.put("MongoDBPort", "");
				klovConfig.put("KlovServerUrl", "");
				TCservice.startReporting(reportPath, testCaseName, klovConfig);
				TCservice.startTestSuiteLogging(testCaseName, testCaseDesc, testCaseId);
			}
		} catch (Exception t) {
			SoapUI.log("SOAPUI Extentter plugin cannot be initialized. " + t.getMessage());
		}
	}

	@Override
	public void afterRun(TestCaseRunner runner, TestCaseRunContext context) {
		// SoapUI.log("Inside AfterRun in TestRunListener - 4");
		try {
			if (ExtenterProjectRunListener.Projservice == null && ExtenterTestSuiteRunListener.TSservice == null) {
				TCservice.finishReporting();
				TCservice = null;
				SoapUI.log("Reports are published at " + reportPath);
			}
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  beforeTestCase of TestSuiteRunListener " + t.getMessage());
		}
	}

	@Override
	public void beforeStep(TestCaseRunner arg0, TestCaseRunContext arg1) {
		// This one is not using in current soapui
	}

	@Override
	public void afterStep(TestCaseRunner paramTestCaseRunner, TestCaseRunContext paramTestCaseRunContext,
			TestStepResult paramTestStepResult) {
		// SoapUI.log("Inside AfterStep in TestRunListener - 3");
		try {
			if (ExtenterProjectRunListener.Projservice != null) {
				String testCaseId = paramTestCaseRunContext.getTestCase().getId();
				String testSuiteId = paramTestCaseRunContext.getTestCase().getTestSuite().getId();
				ExtenterProjectRunListener.Projservice.finishTestStepLogging(paramTestStepResult, testSuiteId,
						testCaseId);
			} else if (ExtenterTestSuiteRunListener.TSservice != null) {
				String testCaseId = paramTestCaseRunContext.getTestCase().getId();
				String testSuiteId = paramTestCaseRunContext.getTestCase().getTestSuite().getId();
				ExtenterTestSuiteRunListener.TSservice.finishTestStepLogging(paramTestStepResult, testSuiteId,
						testCaseId);
			} else {
				String testCaseId = paramTestCaseRunContext.getTestCase().getId();
				String testSuiteId = paramTestCaseRunContext.getTestCase().getId();
				TCservice.finishTestStepLogging(paramTestStepResult, testSuiteId, testCaseId);
			}
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  beforeStep of TestRunListener " + t.getMessage());
		}
	}

	@Override
	public void beforeStep(TestCaseRunner paramTestCaseRunner, TestCaseRunContext context, TestStep testStep) {
		// SoapUI.log("Inside beforeStep in TestRunListener - 2");
	}

}