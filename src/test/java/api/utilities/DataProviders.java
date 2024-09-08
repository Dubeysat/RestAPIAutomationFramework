package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name= "AllData")
	public String [][] allDataProvider(){
		
		String fname = System.getProperty("user.dir")+ "\\TestData\\TestData.xlsx";
		
		int ttlRowCnt = ReadExcelFile.getRowCount(fname, "Sheet1");
		int ttlColCnt = ReadExcelFile.getColCount(fname, "Sheet1");
		
		String userData[][]= new String [ttlRowCnt-1][ttlColCnt];
		
		for (int rowNo=1; rowNo<ttlRowCnt; rowNo++)
		{
			for(int colNo=0; colNo<ttlColCnt; colNo++) {
				
				userData[rowNo-1][colNo] = ReadExcelFile.getCellValue(fname, "Sheet1", rowNo, colNo);
			}
		}
		return userData;
	}
	
	@DataProvider(name= "UserNamesData")
	public String [] userDataProvider(){
		
		String fname = System.getProperty("user.dir")+ "//TestData//TestData.xlsx";
		
		int ttlRowCnt = ReadExcelFile.getRowCount(fname, "Sheet1");
		//int ttlColCnt = ReadExcelFile.getColCount(fname, "Sheet1");
		
		String userData[]= new String [ttlRowCnt];
		
		for (int rowNo=1; rowNo<=ttlRowCnt; rowNo++)
		{	
				userData[rowNo-1] = ReadExcelFile.getCellValue(fname, "Sheet1", rowNo, 1);
		}
		return userData;
	}
}
	