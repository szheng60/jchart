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
    
    private ArrayList<Sheet> excelSheets;
    private String filePath;
    private ArrayList<Item> sheetItem;
    private ArrayList<Site> sheetSite;
    private ArrayList<Sales> sheetSales;
    private ArrayList<Supplier> sheetSupplier;
    private ArrayList<Receiving> sheetReceiving;
    private ArrayList<String> sheetName;
    
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
            //theItem[i] = new Item();
            Iterator cells = row.cellIterator(); 
            tempItem.setCode((int)((Cell) cells.next()).getNumericCellValue());
            tempItem.setDescription(((Cell) cells.next()).getStringCellValue());
            tempItem.setPurchasePrice(((Cell) cells.next()).getNumericCellValue());
            tempItem.setRetailPrice(((Cell) cells.next()).getNumericCellValue());
//            theItem[i].setCode((int)((Cell) cells.next()).getNumericCellValue());
//            theItem[i].setDescription(((Cell) cells.next()).getStringCellValue());
//            theItem[i].setPurchasePrice(((Cell) cells.next()).getNumericCellValue());
//            theItem[i].setRetailPrice(((Cell) cells.next()).getNumericCellValue());
            theItem.add(tempItem);
//            i++;
        }
        return theItem;
    }
    private Site[] initializeSite(Sheet site)
    {
        Site theSite[] = new Site[site.getLastRowNum()];
        Iterator rows = site.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            theSite[i] = new Site();
            Iterator cells = row.cellIterator();             
            theSite[i].setCode(((Cell) cells.next()).getStringCellValue().charAt(0));
            theSite[i].setDescription(((Cell) cells.next()).getStringCellValue());
            i++;
        }
        return theSite;
    }
    private Supplier[] initializeSupplier(Sheet supplier)
    {
        Supplier theSupplier[] = new Supplier[supplier.getLastRowNum()];
        Iterator rows = supplier.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            theSupplier[i] = new Supplier();
            Iterator cells = row.cellIterator();                 
            theSupplier[i].setCode((int)((Cell) cells.next()).getNumericCellValue());
            theSupplier[i].setDescription(((Cell) cells.next()).getStringCellValue());
            i++;
        }
        return theSupplier;
    }
    private Receiving[] initializeReceiving(Sheet receiving)
    {
        Receiving theReceiving[] = new Receiving[receiving.getLastRowNum()];
        Iterator rows = receiving.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            theReceiving[i] = new Receiving();
            Iterator cells = row.cellIterator(); 
            theReceiving[i].setItem((int)((Cell) cells.next()).getNumericCellValue());    
            theReceiving[i].setSite(((Cell) cells.next()).getStringCellValue().charAt(0));
            theReceiving[i].setVendor((int)((Cell) cells.next()).getNumericCellValue());
            theReceiving[i].setDate((Date)((Cell) cells.next()).getDateCellValue());
            theReceiving[i].setQuantity((int)((Cell) cells.next()).getNumericCellValue());
            i++;
        }
        return theReceiving;
    }
    private Sales[] initializeSales(Sheet sales)
    {
        Sales theSales[] = new Sales[sales.getLastRowNum()];
        Iterator rows = sales.rowIterator();
        rows.next();
        int i = 0;
        while (rows.hasNext()) {
            Row row = (Row) rows.next();  
            theSales[i] = new Sales();
            Iterator cells = row.cellIterator();                 
            theSales[i].setItem((int)((Cell) cells.next()).getNumericCellValue());
            theSales[i].setSite(((Cell) cells.next()).getStringCellValue().charAt(0));
            theSales[i].setDate((Date)((Cell) cells.next()).getDateCellValue());
            theSales[i].setQuantity((int)((Cell) cells.next()).getNumericCellValue());
            i++;
        }
        return theSales;
    }
    private Sheet[] readExcelFile() throws FileNotFoundException, IOException 
    {
        String fileName = this.getFilePath();
        Sheet[] sheet;
        try (InputStream in = new FileInputStream(fileName)) {
            String fileExtension = getFileExtension(fileName);
            Workbook wb_xssf;
            Workbook wb_hssf;
            sheet = null;
            if (fileExtension.equalsIgnoreCase(".xlsx")) {
                wb_xssf = new XSSFWorkbook(in);
                int numberOfSheets = wb_xssf.getNumberOfSheets();
                sheet = new Sheet[numberOfSheets];
                this.sheetName = new String[numberOfSheets];
                for (int i = 0; i < numberOfSheets; i++)
                {
                    this.sheetName[i] = wb_xssf.getSheetName(i);
                    sheet[i] = wb_xssf.getSheetAt(i);
                }                
            }
            else if (fileExtension.equalsIgnoreCase(".xls")) {
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb_hssf = new HSSFWorkbook(fs);
                int numberOfSheets = wb_hssf.getNumberOfSheets();
                sheet = new Sheet[numberOfSheets];
                for (int i = 0; i < numberOfSheets; i++)
                {
                    this.sheetName[i] = wb_hssf.getSheetName(i);
                    sheet[i] = wb_hssf.getSheetAt(i);
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
    public Sheet[] getExcelSheets() {
        return excelSheets;
    }

    /**
     * @param excelSheets the excelSheets to set
     */
    public void setExcelSheets(Sheet[] excelSheets) {
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
    public Item[] getSheetItem() {
        return sheetItem;
    }

    /**
     * @param sheetItem the sheetItem to set
     */
    public void setSheetItem(Item[] sheetItem) {
        this.sheetItem = sheetItem;
    }

    /**
     * @return the sheetSite
     */
    public Site[] getSheetSite() {
        return sheetSite;
    }

    /**
     * @param sheetSite the sheetSite to set
     */
    public void setSheetSite(Site[] sheetSite) {
        this.sheetSite = sheetSite;
    }

    /**
     * @return the sheetSales
     */
    public Sales[] getSheetSales() {
        return sheetSales;
    }

    /**
     * @param sheetSales the sheetSales to set
     */
    public void setSheetSales(Sales[] sheetSales) {
        this.sheetSales = sheetSales;
    }

    /**
     * @return the sheetSupplier
     */
    public Supplier[] getSheetSupplier() {
        return sheetSupplier;
    }

    /**
     * @param sheetSupplier the sheetSupplier to set
     */
    public void setSheetSupplier(Supplier[] sheetSupplier) {
        this.sheetSupplier = sheetSupplier;
    }

    /**
     * @return the sheetReceiving
     */
    public Receiving[] getSheetReceiving() {
        return sheetReceiving;
    }

    /**
     * @param sheetReceiving the sheetReceiving to set
     */
    public void setSheetReceiving(Receiving[] sheetReceiving) {
        this.sheetReceiving = sheetReceiving;
    }
}




