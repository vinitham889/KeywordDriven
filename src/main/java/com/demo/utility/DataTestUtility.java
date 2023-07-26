package com.demo.utility;

import java.util.ArrayList;
public class DataTestUtility {
	static XlsReader reader;

	public static ArrayList<Object[]> getDataFromExcel() throws Exception{

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new XlsReader("C:\\Users\\vinitham\\eclipse-workspace\\DataTest\\src\\main\\java\\com\\demo\\excelsheetdata\\Register_form (2).xlsx");
		}catch(Exception e) {
			e.printStackTrace();
		}

		int rowCount = reader.getRowCount("Sheet1");

		for(int rowNum=2;rowNum<=rowCount;rowNum++) {
			String firstname = reader.getCellData("Sheet1","firstname", rowNum);
			String lastname = reader.getCellData("Sheet1","lastname", rowNum);
			String address = reader.getCellData("Sheet1","address", rowNum);
			String city = reader.getCellData("Sheet1","city", rowNum);
			String country = reader.getCellData("Sheet1","country", rowNum);
			String email = reader.getCellData("Sheet1","email", rowNum);
			String dateofmonth = reader.getCellData("Sheet1","dateofmonth", rowNum);
			String dateofyear = reader.getCellData("Sheet1","dateofyear", rowNum);
			String date = reader.getCellData("Sheet1","date", rowNum);
			String convenient_timeHH = reader.getCellData("Sheet1","convenient_timeHH", rowNum);
			String convenient_timeMM = reader.getCellData("Sheet1","convenient_timeMM", rowNum);
			String mobilenumber = reader.getCellData("Sheet1","mobilenumber", rowNum);
			String enter_your_query = reader.getCellData("Sheet1","enter_your_query", rowNum);
			String verification = reader.getCellData("Sheet1","verification", rowNum);
			String gender = reader.getCellData("Sheet1","gender", rowNum);
			
			
			Object ob[] = {firstname, lastname, address, city, country, email, dateofmonth, dateofyear, date, convenient_timeHH, convenient_timeMM,
					mobilenumber, enter_your_query, verification, gender};
			myData.add(ob);
			}

			return myData;

		}


}
