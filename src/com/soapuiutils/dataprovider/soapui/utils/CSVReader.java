package com.soapuiutils.dataprovider.soapui.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CSVReader {

	public static void main(String[] args) {

		String csvFile = "C://sample.csv";
		int count = 1;

		System.out.println(readCSVHeader(csvFile));
		System.out.println(readCSVData(csvFile, count));
	}

	private static Object readCSVData(String csvFile, int count) {
		String line = "";
		String csvSplitBy = ",";
		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {

				String[] csvData = line.split(csvSplitBy);
				
				for (int i = 0; i < csvData.length; i++) {
					hm.put(i, csvData[i]);
				}
				
				String str = Integer.toString(count);
				if (hm.get(0).equalsIgnoreCase(str)) {
					return hm;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Object readCSVHeader(String csvFile) {

		String line = "";
		String csvSplitBy = ",";
		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine();

			String[] csvHeader = line.split(csvSplitBy);

			for (int i = 0; i < csvHeader.length; i++) {
				hm.put(i, csvHeader[i]);
			}
			return hm;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hm;
	}
}
