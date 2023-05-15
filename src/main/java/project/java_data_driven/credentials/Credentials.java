package project.java_data_driven.credentials;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class Credentials {

    private String username;
    private String password;
    private String expected;

    private static final String NAME = "C:/Users/Vladimir/Desktop/DataDrivenTesting.xlsx";

    public static void main(String[] args) { }

    public static void ReadExcelFile(){
        try {
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Sheet> sheets = workbook.sheetIterator();
            while(sheets.hasNext()) {
                Sheet sh = sheets.next();
//                System.out.println("Sheet name is "+sh.getSheetName());
//                System.out.println("---------");
                Iterator<Row> iterator = sh.iterator();
                while(iterator.hasNext()) {
                    Row row = iterator.next();
                    Iterator<Cell> cellIterator = row.iterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = dataFormatter.formatCellValue(cell);

                        if(cell.getCellType() == CellType.STRING) {

                        }
                        System.out.print(cellValue+ " ");
                    }
                    System.out.println();
                }
            }
            workbook.close();
        }
        catch(Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getCellData(){
        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            String value = sheet.getRow(1).getCell(0).getStringCellValue();
            System.out.println(value);
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getRowCount(){
        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Num of Rows: " + rowCount);
        }
        catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getUsername() {

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            username = sheet.getRow(1).getCell(0).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return username;
    }

    public String getPassword() {

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            password = sheet.getRow(1).getCell(1).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return password;
    }
    public String getExpected() {

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            expected = sheet.getRow(1).getCell(2).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return expected;
    }

    public String getInvalidUsername(){

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            username = sheet.getRow(2).getCell(0).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return username;
    }

    public String getInvalidPassword(){

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            password = sheet.getRow(2).getCell(1).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return password;
    }

    public String getExpectedErrorMessage(){

        try{
            FileInputStream file = new FileInputStream(NAME);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            expected = sheet.getRow(2).getCell(2).getStringCellValue();
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return expected;
    }
}
