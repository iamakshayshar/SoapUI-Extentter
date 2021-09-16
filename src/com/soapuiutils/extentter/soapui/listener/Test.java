package com.soapuiutils.extentter.soapui.listener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

/*
 * Author : Akshay Sharma
 * Email : akshay.sharma979@gmail.com
 * Description : This class is created to export the main class while generating runnable jar file.
 */

public class Test {

	public static void main(String[] args) throws URISyntaxException, IOException {

		int timeout = 1000;
		if (InetAddress.getByName("http://192.168.182.237").isReachable(timeout)) {
			System.out.println("is reachable");
		}

	}
}