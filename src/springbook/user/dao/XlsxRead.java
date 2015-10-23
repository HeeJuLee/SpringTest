package springbook.user.dao;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.Group;
import springbook.user.domain.GroupMember;

public class XlsxRead {
	static ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args) {
		//insertGroupData();
		//insertMemberData();
		
	}
		
		
	private static void insertGroupData() {
		XSSFRow row;
		XSSFCell cell;

		GroupDao dao = context.getBean("groupDao", GroupDao.class);
		
		try {
			FileInputStream inputStream = new FileInputStream("bin/권한그룹별권한정보.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			//sheet수 취득
			int sheetCn = workbook.getNumberOfSheets();
			System.out.println("sheet수 : " + sheetCn);
			
			for(int cn = 0; cn < sheetCn; cn++){
				System.out.println("취득하는 sheet 이름 : " + workbook.getSheetName(cn));
				System.out.println(workbook.getSheetName(cn) + " sheet 데이터 취득 시작");
				
				//0번째 sheet 정보 취득
				XSSFSheet sheet = workbook.getSheetAt(cn);
				
				//취득된 sheet에서 rows수 취득
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println(workbook.getSheetName(cn) + " sheet의 row수 : " + rows);
				
				//취득된 row에서 취득대상 cell수 취득
				int cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //
				System.out.println(workbook.getSheetName(cn) + " sheet의 row에 취득대상 cell수 : " + cells);
				
				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r); // row 가져오기
					if (row != null) {
						String groupName = null;
						String toolName = null;
						String roleName = null;
						for (int c = 0; c < cells; c++) {
							cell = row.getCell(c);
							String value = null;
							if (cell != null) {
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									value = cell.getCellFormula();
									break;
								case XSSFCell.CELL_TYPE_NUMERIC:
									value = "" + cell.getNumericCellValue();
									break;
								case XSSFCell.CELL_TYPE_STRING:
									value = "" + cell.getStringCellValue();
									break;
								case XSSFCell.CELL_TYPE_BLANK:
									value = "[공백]";
									break;
								case XSSFCell.CELL_TYPE_ERROR:
									value = "" + cell.getErrorCellValue();
									break;
								default:
								}
								System.out.print(value + "\t");
							} else {
								System.out.print("[null]\t");
								value = "[NULL]";
							}
							
							if(c == 0)
								groupName = value;
							else if(c == 1)
								toolName = value;
							else if(c == 2)
								roleName = value;
						} // for(c) 문
						System.out.print("\n");
						
						Group group = new Group(1, groupName, toolName, roleName);
						dao.add(group);
					}
				} // for(r) 문
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void insertMemberData() {
		XSSFRow row;
		XSSFCell cell;

		GroupMemberDao dao = context.getBean("groupMemberDao", GroupMemberDao.class);
		
		try {
			FileInputStream inputStream = new FileInputStream("bin/그룹별사용자정보.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			//sheet수 취득
			int sheetCn = workbook.getNumberOfSheets();
			System.out.println("sheet수 : " + sheetCn);
			
			for(int cn = 0; cn < sheetCn; cn++){
				System.out.println("취득하는 sheet 이름 : " + workbook.getSheetName(cn));
				System.out.println(workbook.getSheetName(cn) + " sheet 데이터 취득 시작");
				
				//0번째 sheet 정보 취득
				XSSFSheet sheet = workbook.getSheetAt(cn);
				
				//취득된 sheet에서 rows수 취득
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println(workbook.getSheetName(cn) + " sheet의 row수 : " + rows);
				
				//취득된 row에서 취득대상 cell수 취득
				int cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //
				System.out.println(workbook.getSheetName(cn) + " sheet의 row에 취득대상 cell수 : " + cells);
				
				String groupName = null;
				String objectGuid = null;
				String count = null;
				String member = null;
				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r); // row 가져오기
					if (row != null) {
						
						for (int c = 0; c < cells; c++) {
							cell = row.getCell(c);
							String value = null;
							if (cell != null) {
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									value = cell.getCellFormula();
									break;
								case XSSFCell.CELL_TYPE_NUMERIC:
									value = "" + cell.getNumericCellValue();
									break;
								case XSSFCell.CELL_TYPE_STRING:
									value = "" + cell.getStringCellValue();
									break;
								case XSSFCell.CELL_TYPE_BLANK:
									value = "[공백]";
									break;
								case XSSFCell.CELL_TYPE_ERROR:
									value = "" + cell.getErrorCellValue();
									break;
								default:
								}
								System.out.print(value + "\t");
								
								if(c == 0)
									groupName = value;
								else if(c == 1)
									objectGuid = value;
								else if(c == 2)
									count = value;
								else if(c == 3)
									member = value;
							} else {
								System.out.print("[null]\t");
								// 이전 라인의 값을 그대로 사용
							}
							
						} // for(c) 문
						System.out.print("\n");
						
						GroupMember groupMember = new GroupMember(1, groupName, objectGuid, member);
						dao.add(groupMember);
					}
				} // for(r) 문
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
