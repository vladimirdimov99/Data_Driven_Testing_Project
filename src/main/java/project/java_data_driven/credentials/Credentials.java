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

    public String excelPath = "C:/Users/Vladimir/Desktop/DataDrivenTesting.xlsx";
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public Credentials (){
        try{
            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet("Sheet1");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Credentials excel = new Credentials();
        excel.getRowCount();
        excel.getCellDataString(1,1);
    }

    public void ReadExcelFile(){
        try {
            FileInputStream file = new FileInputStream(excelPath);
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
                        //System.out.print(cellValue+ " ");
                    }
                    //System.out.println();
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

    public String getCellDataString(int rowNum, int cellNum){
        String cellData = null;
        try{
            cellData = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
            //System.out.println(cellData);
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cellData;
    }

    public void getCellDataNumber(int rowNum, int cellNum){
        try{
            double value = sheet.getRow(rowNum).getCell(cellNum).getNumericCellValue();
            //System.out.println(value);
        }
        catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int getRowCount(){
        int rowCount = 0;
        try{
            rowCount = sheet.getPhysicalNumberOfRows();
            //System.out.println("Num of Rows: " + rowCount);
        }
        catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rowCount;
    }

    public int getColumnCount() {
        int columnCount = 0;
        try {
            columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
            //System.out.println("Num of Columns: " + columnCount);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return columnCount;
    }

    public String getUsername() {
        try{
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
            FileInputStream file = new FileInputStream(excelPath);
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
            FileInputStream file = new FileInputStream(excelPath);
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
            FileInputStream file = new FileInputStream(excelPath);
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
            FileInputStream file = new FileInputStream(excelPath);
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

