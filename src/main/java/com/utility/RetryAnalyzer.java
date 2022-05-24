package com.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

		 private int count = 0;
		    private static int maxTry = 2;
		 
		    @Override
		    public boolean retry(ITestResult result) {
		        if (count < maxTry) {
		            result.getTestContext().getFailedTests().removeResult(result);
		            count++;
		            try {
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
		            return true;
		        }
		        return false;
		    }
	

}
