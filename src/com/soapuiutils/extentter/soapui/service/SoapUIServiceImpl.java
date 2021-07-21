package com.soapuiutils.extentter.soapui.service;

import java.util.List;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.model.propertyexpansion.PropertyExpansionContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;
import com.soapuiutils.extentter.soapui.reporter.Report;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This class implements SoapUIService and this is where the actual logic for report generation is written. 
 */

public class SoapUIServiceImpl implements SoapUIService {

	public Report report;

	public void startReporting(String reportPath, String reportName) {
		try {
			report = new Report(reportPath, reportName);
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  startLaunch of StepBasedSoapUIServiceImpl " + t.getMessage());
		}
	}

	public void finishReporting() {
		report.endTestLog();
	}

	public void startTestSuiteLogging(String testSuiteName, String testDesc, String testSuiteId) {
		report.startTestLog(testSuiteName, testDesc, testSuiteId);
	}

	public void finishTestSuiteLogging(TestSuiteRunner testSuiteContext) {
		// TODO Auto-generated method stub

	}

	public void startTestCaseLogging(String testCaseName, String testSuiteId, String testCaseId) {
		try {
			report.createNode(testCaseName, testSuiteId, testCaseId);
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  startTestCase of StepBasedSoapUIServiceImpl " + t.getMessage());
		}
	}

	public void finishTestCaseLogging(TestCaseRunner testCaseContext, PropertyExpansionContext propertyContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startTestStepLogging(TestStep testStep, String testCaseId) {
		try {

		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  startTestStep of StepBasedSoapUIServiceImpl " + t.getMessage());
		}
	}

	@Override
	public void finishTestStepLogging(TestStepResult testStepContext, String testSuiteId, String testCaseId) {
		try {
			if (TestStepStatus.FAILED.equals(testStepContext.getStatus())) {
				report.logFail(testStepContext, testSuiteId, testCaseId);
			} else if (TestStepStatus.OK.equals(testStepContext.getStatus())) {
				report.logPass(testStepContext, testSuiteId, testCaseId);
			} else {
				report.logInfo(testStepContext, testSuiteId, testCaseId);
			}
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  finishTestStep of StepBasedSoapUIServiceImpl " + t.getMessage());
		}
	}

	public void addEnvDetails(List<TestProperty> properties) {
		try {
			report.addEnvironmentDetails(properties);
		} catch (Throwable t) {
			SoapUI.log("SOAPUI Extentter Error in  addEnvDetails of StepBasedSoapUIServiceImpl " + t.getMessage());
		}
	}
}