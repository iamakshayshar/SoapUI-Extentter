package com.soapuiutils.extentter.soapui.service;

import java.util.List;

import com.eviware.soapui.model.propertyexpansion.PropertyExpansionContext;
import com.eviware.soapui.model.testsuite.*;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This interface is created for making all the listener methods available in sync with each other. 
 */

public interface SoapUIService {

	void startReporting(String reportPath, String reportName);

	void finishReporting();

	void startTestSuiteLogging(String testSuiteName, String testDesc, String testSuiteId);

	void finishTestSuiteLogging(TestSuiteRunner testSuiteContext);

	void startTestCaseLogging(String testCaseName, String testSuiteId, String testCaseId);

	void finishTestCaseLogging(TestCaseRunner testCaseContext, PropertyExpansionContext propertyContext);

	void startTestStepLogging(TestStep testStep, String testCaseId);

	void finishTestStepLogging(TestStepResult testStepContext, String testSuiteId, String testCaseId);

	public void addEnvDetails(List<TestProperty> properties);
}
