package com.soapuiutils.extentter.soapui.listener;

import java.io.File;
import java.util.List;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.testsuite.ProjectRunContext;
import com.eviware.soapui.model.testsuite.ProjectRunListener;
import com.eviware.soapui.model.testsuite.ProjectRunner;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;
import com.soapuiutils.extentter.soapui.service.SoapUIService;
import com.soapuiutils.extentter.soapui.service.SoapUIServiceImpl;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This class implements methods of ProjectRunListener of SOAPUI. This will listen to project level actions.
 */

public class ExtenterProjectRunListener implements ProjectRunListener {

	public static SoapUIService Projservice = null;
	public String reportPath;
	
	public void beforeRun(ProjectRunner runner, ProjectRunContext context) {
		// SoapUI.log("Inside BeforeRun in ProjectRunListener - 1");
		try {
			List<TestProperty> properties = context.getProject().getPropertyList();
			Projservice = new SoapUIServiceImpl();
			String projectXmlPath = context.getProject().getPath();
			int index = projectXmlPath.lastIndexOf("\\");
			reportPath = projectXmlPath.substring(0, index);
			reportPath = reportPath + File.separator + "Reports";

			String reportName = context.getProject().getName();
			Projservice.startReporting(reportPath, reportName);
			Projservice.addEnvDetails(properties);
		} catch (Exception t) {
			SoapUI.log("SOAPUI Extentter plugin cannot be initialized. " + t.getMessage());
		}
	}

	public void afterRun(ProjectRunner paramProjectRunner, ProjectRunContext paramProjectRunContext) {
		// SoapUI.log("Inside AfterRun in ProjectRunListener - 12");
		try {
			Projservice.finishReporting();
			Projservice = null;
			SoapUI.log("Reports are published at " + reportPath);
		} catch (Exception t) {
			SoapUI.log("SOAPUI Extentter plugin cannot be initialized. " + t.getMessage());
		}
	}

	public void beforeTestSuite(ProjectRunner paramProjectRunner, ProjectRunContext paramProjectRunContext,
			TestSuite paramTestSuite) {
		// SoapUI.log("Inside BeforeTestSuite in ProjectRunListener - 2");
		try {
			String testSuiteName = paramTestSuite.getName();
			String testSuiteDesc = paramTestSuite.getDescription();
			String testSuiteId = paramTestSuite.getId();
			Projservice.startTestSuiteLogging(testSuiteName, testSuiteDesc, testSuiteId);
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  beforeTestSuite of ProjectRunListener " + t.getMessage());
		}
	}

	public void afterTestSuite(ProjectRunner paramProjectRunner, ProjectRunContext paramProjectRunContext,
			TestSuiteRunner paramTestSuiteRunner) {
		// SoapUI.log("Inside AfterTestSuite in ProjectRunListener - 11");
	}
}