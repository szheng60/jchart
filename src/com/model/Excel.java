package com.model;

import static com.view.Main.theCM;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Excel {
    
    private ArrayList<Sheet> excelSheets = new ArrayList<>();
    private String filePath;
    private ArrayList<Item> sheetItem = new ArrayList<>();
    private ArrayList<Site> sheetSite = new ArrayList<>();
    private ArrayList<Sales> sheetSales = new ArrayList<>();
    private ArrayList<Supplier> sheetSupplier = new ArrayList<>();
    private ArrayList<Receiving> sheetReceiving = new ArrayList<>();
    private ArrayList<String> sheetName = new ArrayList<>();
    
    public Excel(String filePath) throws IOException
    {
        this.filePath = filePath;      
    }
    public void initialize() throws IOException {
        this.setExcelSheets(readExcelFile());
        for (int i = 0; i < this.excelSheets.size(); i++)
        {    
            initializeSheet(sheetName.get(i), this.excelSheets.get(i));
        }
    }
    private void initializeSheet(String sheetName, Sheet sheet)
    {
        switch (sheetName.toUpperCase())
        {
            case "ITEM":
                this.sheetItem = initializeItem(sheet);
                //theCM.setTheItemControl(this.sheetItem);
                break;
            case "SITE":
                this.sheetSite = initializeSite(sheet);
                break;
            case "SUPPLIER":
                this.sheetSupplier = initializeSupplier(sheet);
                break;
            case "RECEIVING":
                this.sheetReceiving = initializeReceiving(sheet);
                theCM.setTheReceivingControl(this.sheetReceiving);
                break;
            case "SALES":
                this.sheetSales = initializeSales(sheet);
                break;
            default:
                break;
        }
        
    }
    private ArrayList<Item> initializeItem(Sheet item)
    {
        ArrayList<Item> theItem = new ArrayList<>();//Item[item.getLastRowNum()];
        Iterator rows = item.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            Item tempItem = new Item();
            Iterator cells = row.cellIterator(); 
            tempItem.setCode((int)((Cell) cells.next()).getNumericCellValue());
            tempItem.setDescription(((Cell) cells.next()).getStringCellValue());
            tempItem.setPurchasePrice(((Cell) cells.next()).getNumericCellValue());
            tempItem.setRetailPrice(((Cell) cells.next()).getNumericCellValue());
            theItem.add(tempItem);
        }
        return theItem;
    }
    private ArrayList<Site> initializeSite(Sheet site)
    {
        //Site theSite[] = new Site[site.getLastRowNum()];
        ArrayList<Site> theSite = new ArrayList<>();
        Iterator rows = site.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            Site tempSite = new Site();
            Iterator cells = row.cellIterator();             
            tempSite.setCode(((Cell) cells.next()).getStringCellValue().charAt(0));
            tempSite.setDescription(((Cell) cells.next()).getStringCellValue());
            theSite.add(tempSite);
        }
        return theSite;
    }
    private ArrayList<Supplier> initializeSupplier(Sheet supplier)
    {
        ArrayList<Supplier> theSupplier = new ArrayList<>();// = new Supplier[supplier.getLastRowNum()];
        Iterator rows = supplier.rowIterator();
        rows.next();
        //int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            Supplier tempSupplier = new Supplier();
            //theSupplier[i] = new Supplier();
            Iterator cells = row.cellIterator();                 
            tempSupplier.setCode((int)((Cell) cells.next()).getNumericCellValue());
            tempSupplier.setDescription(((Cell) cells.next()).getStringCellValue());
            theSupplier.add(tempSupplier);
        }
        return theSupplier;
    }
    private ArrayList<Receiving> initializeReceiving(Sheet receiving)
    {
    	ArrayList<Receiving> theReceiving = new ArrayList<>();
        Iterator rows = receiving.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            Receiving tempReceiving = new Receiving();
            Iterator cells = row.cellIterator(); 
            tempReceiving.setItem((int)((Cell) cells.next()).getNumericCellValue());    
            tempReceiving.setSite(((Cell) cells.next()).getStringCellValue().charAt(0));
            tempReceiving.setVendor((int)((Cell) cells.next()).getNumericCellValue());
            tempReceiving.setDate((Date)((Cell) cells.next()).getDateCellValue());
            tempReceiving.setQuantity((int)((Cell) cells.next()).getNumericCellValue());
            theReceiving.add(tempReceiving);
            //i++;
            
        }
        return theReceiving;
    }
    private ArrayList<Sales> initializeSales(Sheet sales)
    {
        //Sales theSales[] = new Sales[sales.getLastRowNum()];
        ArrayList<Sales> theSales = new ArrayList<>();
        Iterator rows = sales.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            Sales tempSales = new Sales();
            Iterator cells = row.cellIterator();                 
            tempSales.setItem((int)((Cell) cells.next()).getNumericCellValue());
            tempSales.setSite(((Cell) cells.next()).getStringCellValue().charAt(0));
            tempSales.setDate((Date)((Cell) cells.next()).getDateCellValue());
            tempSales.setQuantity((int)((Cell) cells.next()).getNumericCellValue());
            theSales.add(tempSales);
        }
        return theSales;
    }
    private ArrayList<Sheet> readExcelFile() throws FileNotFoundException, IOException 
    {
        String fileName = this.getFilePath();
        ArrayList<Sheet> sheet = new ArrayList<>();
        try (InputStream in = new FileInputStream(fileName)) {
            String fileExtension = getFileExtension(fileName);
            Workbook wb_xssf;
            Workbook wb_hssf;
            if (fileExtension.equalsIgnoreCase(".xlsx")) {
                wb_xssf = new XSSFWorkbook(in);
                int numberOfSheets = wb_xssf.getNumberOfSheets();
                                
               // sheet = new Sheet[numberOfSheets];
               // this.sheetName = new String[numberOfSheets];
                for (int i = 0; i < numberOfSheets; i++)
                {
                    this.sheetName.add(wb_xssf.getSheetName(i));
                    //sheet[i] = wb_xssf.getSheetAt(i);
                    sheet.add(wb_xssf.getSheetAt(i));
                }                
            }
            else if (fileExtension.equalsIgnoreCase(".xls")) {
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb_hssf = new HSSFWorkbook(fs);
                int numberOfSheets = wb_hssf.getNumberOfSheets();
//                sheet = new Sheet[numberOfSheets];
                for (int i = 0; i < numberOfSheets; i++)
                {
                    this.sheetName.add(wb_hssf.getSheetName(i));
                    //sheet[i] = wb_hssf.getSheetAt(i);
                    sheet.add(wb_hssf.getSheetAt(i));
                }
            }
        }
        return sheet;
    }
        
    private String getFileExtension(String fname) 
    {
        String ext = fname.substring(fname.lastIndexOf("."), fname.length());
        return ext;
    }

    /**
     * @return the excelSheets
     */
    public ArrayList<Sheet> getExcelSheets() {
        return excelSheets;
    }

    /**
     * @param excelSheets the excelSheets to set
     */
    public void setExcelSheets(ArrayList<Sheet> excelSheets) {
        this.excelSheets = excelSheets;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the sheetItem
     */
    public ArrayList<Item> getSheetItem() {
        return sheetItem;
    }

    /**
     * @param sheetItem the sheetItem to set
     */
    public void setSheetItem(ArrayList<Item> sheetItem) {
        this.sheetItem = sheetItem;
    }

    /**
     * @return the sheetSite
     */
    public ArrayList<Site> getSheetSite() {
        return sheetSite;
    }

    /**
     * @param sheetSite the sheetSite to set
     */
    public void setSheetSite(ArrayList<Site> sheetSite) {
        this.sheetSite = sheetSite;
    }

    /**
     * @return the sheetSales
     */
    public ArrayList<Sales> getSheetSales() {
        return sheetSales;
    }

    /**
     * @param sheetSales the sheetSales to set
     */
    public void setSheetSales(ArrayList<Sales> sheetSales) {
        this.sheetSales = sheetSales;
    }

    /**
     * @return the sheetSupplier
     */
    public ArrayList<Supplier> getSheetSupplier() {
        return sheetSupplier;
    }

    /**
     * @param sheetSupplier the sheetSupplier to set
     */
    public void setSheetSupplier(ArrayList<Supplier> sheetSupplier) {
        this.sheetSupplier = sheetSupplier;
    }

    /**
     * @return the sheetReceiving
     */
    public ArrayList<Receiving> getSheetReceiving() {
        return sheetReceiving;
    }

    /**
     * @param sheetReceiving the sheetReceiving to set
     */
    public void setSheetReceiving(ArrayList<Receiving> sheetReceiving) {
        this.sheetReceiving = sheetReceiving;
    }
}




