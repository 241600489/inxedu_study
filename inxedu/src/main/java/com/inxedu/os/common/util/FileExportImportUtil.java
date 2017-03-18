//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.FileUtils;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class FileExportImportUtil {
    InputStream os;
    List<List<String>> list = new ArrayList();
    public HSSFWorkbook demoWorkBook = new HSSFWorkbook();
    public HSSFSheet demoSheet;

    public FileExportImportUtil() {
        this.demoSheet = this.demoWorkBook.createSheet("Sheet1");
    }

    public void createTableRow(List<String> cells, short rowIndex) {
        HSSFRow row = this.demoSheet.createRow(rowIndex);

        for(short i = 0; i < cells.size(); ++i) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue((String)cells.get(i));
        }

    }

    public void createExcelSheeet() throws SQLException {
        for(int i = 0; i < this.list.size(); ++i) {
            this.createTableRow((List)this.list.get(i), (short)i);
        }

    }

    public InputStream exportExcel(HSSFSheet sheet) throws IOException {
        sheet.setGridsPrinted(true);
        HSSFFooter footer = sheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " + HSSFFooter.numPages());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            this.demoWorkBook.write(baos);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        byte[] ba = baos.toByteArray();
        this.os = new ByteArrayInputStream(ba);
        return this.os;
    }

    public InputStream export(List<List<String>> zlist) {
        InputStream myos = null;

        Object e1;
        try {
            this.list = zlist;
            this.createExcelSheeet();
            myos = this.exportExcel(this.demoSheet);
            InputStream e = myos;
            return e;
        } catch (Exception var14) {
            JOptionPane.showMessageDialog((Component)null, "表格导出出错，错误信息 ：" + var14 + "\n错误原因可能是表格已经打开。");
            var14.printStackTrace();
            e1 = null;
        } finally {
            try {
                this.os.close();
                if(myos != null) {
                    myos.close();
                }
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

        return (InputStream)e1;
    }

    public HSSFWorkbook getHSSFWorkbook(List<List<String>> zlist) {
        try {
            this.list = zlist;
            this.createExcelSheeet();
            this.demoSheet.setGridsPrinted(true);
            HSSFFooter e = this.demoSheet.getFooter();
            e.setRight("Page " + HSSFFooter.page() + " of " + HSSFFooter.numPages());
            return this.demoWorkBook;
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "表格导出出错，错误信息 ：" + var3 + "\n错误原因可能是表格已经打开。");
            var3.printStackTrace();
            return null;
        }
    }

    public static void createRar(HttpServletResponse response, String dir, List<File> srcfile, String expName) {
        if(!(new File(dir)).exists()) {
            (new File(dir)).mkdirs();
        }

        File zipfile = new File(dir + "/" + expName + ".rar");
        FileUtils.deleteFile(zipfile);

        for(int e = 0; e < srcfile.size(); ++e) {
            FileUtils.deleteFile(new File(dir + "/" + expName + e + ".xls"));
        }

        zipFiles(srcfile, zipfile);

        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(zipfile.getName().getBytes("UTF-8"), "ISO8859-1"));
            response.addHeader("Content-Length", "" + zipfile.length());
            BufferedInputStream var9 = new BufferedInputStream(new FileInputStream(zipfile));
            byte[] buffer = new byte[var9.available()];
            var9.read(buffer);
            var9.close();
            BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public static File createExcel(String[] headName, List<List<String>> list, String expName, String dir) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short)0);

        int file;
        for(file = 0; file < headName.length; ++file) {
            cell = row.createCell((short)file);
            cell.setCellValue(headName[file]);
        }

        for(file = 0; file < list.size(); ++file) {
            row = sheet.createRow(file + 1);
            List fos = (List)list.get(file);

            for(int i = 0; i < fos.size(); ++i) {
                cell = row.createCell((short)i);
                cell.setCellValue((String)fos.get(i));
            }
        }

        File var11 = new File(dir + "/" + expName + ".xls");
        if(!(new File(dir)).exists()) {
            (new File(dir)).mkdirs();
        }

        FileOutputStream var12 = new FileOutputStream(var11);
        workbook.write(var12);
        var12.close();
        return var11;
    }

    public static void zipFiles(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        String ZIP_ENCODEING = "GBK";

        try {
            ZipOutputStream e = new ZipOutputStream(new FileOutputStream(zipfile));
            e.setEncoding(ZIP_ENCODEING);

            for(int i = 0; i < srcfile.size(); ++i) {
                File file = (File)srcfile.get(i);
                FileInputStream in = new FileInputStream(file);
                e.putNextEntry(new ZipEntry(file.getName()));

                int len;
                while((len = in.read(buf)) > 0) {
                    e.write(buf, 0, len);
                }

                e.closeEntry();
                in.close();
            }

            e.close();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }
}
