package com.gx.poi.utils;
 
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.gx.web.test.ExcelLibrary;
import com.gx.web.test.NumberLibrary;
import com.mysql.jdbc.PreparedStatement;
 
/**
 * A rudimentary XLSX -> CSV processor modeled on the
 * POI sample program XLS2CSVmra from the package
 * org.apache.poi.hssf.eventusermodel.examples.
 * As with the HSSF version, this tries to spot missing
 * rows and cells, and output empty entries for them.
 * <p/>
 * Data sheets are read using a SAX parser to keep the
 * memory footprint relatively small, so this should be
 * able to read enormous workbooks.  The styles table and
 * the shared-string table must be kept in memory.  The
 * standard POI styles table class is used, but a custom
 * (read-only) class is used for the shared string table
 * because the standard POI SharedStringsTable grows very
 * quickly with the number of unique strings.
 * <p/>
 * For a more advanced implementation of SAX event parsing
 * of XLSX files, see {@link XSSFEventBasedExcelExtractor}
 * and {@link XSSFSheetXMLHandler}. Note that for many cases,
 * it may be possible to simply use those with a custom
 * {@link SheetContentsHandler} and no SAX code needed of
 * your own!
 */
public class XLSX2CSV {
    /**
     * Uses the XSSF Event SAX helpers to do most of the work
     * of parsing the Sheet XML, and outputs the contents
     * as a (basic) CSV.
     */
    private class SheetConvert implements SheetContentsHandler {
        private boolean firstCellOfRow = false;
        private int currentRow = -1;
        private int currentCol = -1;
        private List<String> lineArray = new ArrayList<String>();
        
        private ExcelLibrary el;
        
 
        private void outputMissingRows(int number) {
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < minColumns; j++) {
                    output.append(',');
                }
                output.append('\n');
            }
        }
 
        @Override
        public void startRow(int rowNum) {
            // If there were gaps, output the missing rows
            outputMissingRows(rowNum - currentRow - 1);
            // Prepare for this row
            firstCellOfRow = true;
            currentRow = rowNum;
            currentCol = -1;
            lineArray.clear();
            el = new ExcelLibrary();
        }
 
        @Override
        public void endRow(int rowNum) {
            // Ensure the minimum number of columns
            for (int i = currentCol; i < minColumns; i++) {
                output.append(',');
            }
            output.append('\n');
            //TODO 本类文件是一行一行的读取数据，lineArray存储当前读取行数据，可在此处一行一行的对数据进行加工，如存入数据库等
        	//System.out.println("lineArray :" + lineArray.toString());
        }
 
        @Override
        public void cell(String cellReference, String formattedValue,
                         XSSFComment comment) {
            if (firstCellOfRow) {
                firstCellOfRow = false;
            } else {
                output.append(',');
            }
 
            // gracefully handle missing CellRef here in a similar way as XSSFCell does
            if (cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }
 
            // Did we miss any cells?
            int thisCol = (new CellReference(cellReference)).getCol();
            int missedCols = thisCol - currentCol - 1;
            for (int i = 0; i < missedCols; i++) {
                output.append(',');
                lineArray.add("");//如果读的单元格为空，则在行列表里添加空值
            }
            currentCol = thisCol;
            // Number or string?根据需求可按数据类型处理各个数据单元
            try {
                Double.parseDouble(formattedValue);
                output.append(formattedValue);
            } catch (NumberFormatException e) {
                output.append('"');
                output.append(formattedValue);
                output.append('"');
            }
            lineArray.add(formattedValue);
            if(currentRow>382436) {
	            if(lineArray.size()==1)
	            	el.setId(Integer.parseInt(formattedValue));
	            if(lineArray.size()==2)
	            	el.setPref(Integer.parseInt(formattedValue));
	            if(lineArray.size()==3)
	            	el.setPhone(Integer.parseInt(formattedValue));
	            if(lineArray.size()==4)
	            	el.setProvince(formattedValue);
	            if(lineArray.size()==5)
	            	el.setCity(formattedValue);
	            if(lineArray.size()==6)
	            	el.setIsp(formattedValue);
	            if(lineArray.size()==7)
	            	el.setPost_code(Integer.parseInt(formattedValue));
	            if(lineArray.size()==8)
	            	el.setArea_code(Integer.parseInt(formattedValue));
	            if(lineArray.size()==9) {
	            	el.setTypes(formattedValue);
	            	insert(el);
	            }
	            
	            
	            
	            
            
            } 
            
            
            
            
            
            
        }
 
        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
            // Skip, no headers or footers in CSV
        }
    }
 
 
    ///////////////////////////////////////
 
    
    
    
    
    
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ai_phone_num";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    
    private static int insert(ExcelLibrary excelLibrary) {
        Connection conn = getConn();
        int i = 0;
        String sql ="INSERT INTO `ai_excel_library` (`id`, `pref`, `phone`, `province`, `city`, `isp`, `post_code`, `area_code`, `types`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, excelLibrary.getId());
            pstmt.setInt(2, excelLibrary.getPref());
            pstmt.setInt(3, excelLibrary.getPhone());
            pstmt.setString(4, excelLibrary.getProvince());
            pstmt.setString(5, excelLibrary.getCity());
            pstmt.setString(6, excelLibrary.getIsp());
            pstmt.setInt(7, excelLibrary.getPost_code());
            pstmt.setInt(8, excelLibrary.getArea_code());
            pstmt.setString(9, excelLibrary.getTypes());
            
            
            
            
            
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    
    private final OPCPackage xlsxPackage;
 
    /**
     * Number of columns to read starting with leftmost
     */
    private final int minColumns;
 
    /**
     * Destination for data
     */
    private final PrintStream output;
 
    /**
     * Creates a new XLSX -> CSV converter
     *
     * @param pkg        The XLSX package to process
     * @param output     The PrintStream to output the CSV to
     * @param minColumns The minimum number of columns to output, or -1 for no minimum
     */
    public XLSX2CSV(OPCPackage pkg, PrintStream output, int minColumns) {
        this.xlsxPackage = pkg;
        this.output = output;
        this.minColumns = minColumns;
    }
 
    /**
     * Parses and shows the content of one sheet
     * using the specified styles and shared-strings tables.
     *
     * @param styles
     * @param strings
     * @param sheetInputStream
     */
    public void processSheet(
            StylesTable styles,
            ReadOnlySharedStringsTable strings,
            SheetContentsHandler sheetHandler,
            InputStream sheetInputStream)
            throws IOException, ParserConfigurationException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
            
            
            
//            sheetParser.getEntityResolver();
            
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }
 
    /**
     * Initiates the processing of the XLS workbook file to CSV.
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void process()
            throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            String sheetName = iter.getSheetName();
            this.output.println();
            this.output.println(sheetName + " [index=" + index + "]:");
            processSheet(styles, strings, new SheetConvert(), stream);
            stream.close();
            ++index;
        }
    }
 
    public static void main(String[] args) throws Exception {
        File xlsxFile = new File("C:\\Users\\dell\\Desktop\\归属地数据库_Excel格式.xlsx");
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }
 
        int minColumns = -1;
        if (args.length >= 2)
            minColumns = Integer.parseInt(args[1]);
        
        // The package open is instantaneous, as it should be.
        OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out, minColumns);
        /*//若想把数据输入文件，可如下调用
        PrintStream ps = new PrintStream(new File("e:/test/a.txt"));
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, ps, minColumns);*/
        xlsx2csv.process();
        p.close();
    }
}
