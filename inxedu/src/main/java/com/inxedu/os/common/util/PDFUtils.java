//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.MyDocument;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.PDimension;
import org.icepdf.core.pobjects.Page;

public class PDFUtils {
    float scale = 2.0F;
    float rotation = 0.0F;

    public PDFUtils() {
    }

    public static Map<String, String> pdfToPng(String rootpath, String pdfurl, String width, String height) throws Exception {
        HashMap map = new HashMap();
        System.out.println("pdfToPng");
        String pngUrlStrs = "";
        String pngUrlStrsTB = "";
        String pngFileUrl = pdfurl.substring(0, pdfurl.lastIndexOf("."));
        String pngFileName = pngFileUrl.substring(pngFileUrl.lastIndexOf("/"), pngFileUrl.length());
        createPath(pngFileUrl);
        MyDocument document = new MyDocument();
        document.setFile(pdfurl);
        int pages = document.getNumberOfPages();

        for(int i = 0; i < pages; ++i) {
            float rotation = 0.0F;
            tranferPer(document, rotation, pngFileUrl + "" + pngFileName + "_pdf_" + i + ".png", i);
            if(i == 0) {
                pngUrlStrs = (pngFileUrl + "" + pngFileName + "_pdf_" + i + ".png").replace(rootpath, "");
                pngUrlStrsTB = zoomImage(rootpath, (pngFileUrl + "" + pngFileName + "_pdf_" + i + ".png").replace(rootpath, ""), Integer.valueOf(width).intValue(), Integer.valueOf(height).intValue());
            } else {
                pngUrlStrs = pngUrlStrs + "," + (pngFileUrl + "" + pngFileName + "_pdf_" + i + ".png").replace(rootpath, "");
                pngUrlStrsTB = pngUrlStrsTB + "," + zoomImage(rootpath, (pngFileUrl + "" + pngFileName + "_pdf_" + i + ".png").replace(rootpath, ""), Integer.valueOf(width).intValue(), Integer.valueOf(height).intValue());
            }
        }

        document.dispose();
        map.put("pngUrlStrs", pngUrlStrs);
        map.put("pngUrlStrsTB", pngUrlStrsTB);
        return map;
    }

    public static void tranferPer(Document document, float rotation, String imagepath, int index) throws Exception {
        float scale = 3.0F;
        Page page = document.getPageTree().getPage(index);
        page.init();
        PDimension sz = page.getSize(2, rotation, scale);
        int pageWidth = (int)sz.getWidth();
        int pageHeight = (int)sz.getHeight();
        BufferedImage image = new BufferedImage(pageWidth, pageHeight, 1);
        Graphics2D g = image.createGraphics();
        page.paint(g, 2, 2, rotation, scale);
        g.dispose();

        try {
            System.out.println("转换第 " + (index + 1) + " 页");
            File e = new File(imagepath);
            ImageIO.write(image, "jpg", e);
        } catch (Throwable var12) {
            var12.printStackTrace();
        }

        image.flush();
    }

    public static String zoomImage(String rootpath, String srcFileName, int width, int height) {
        String tagFileName = srcFileName.substring(0, srcFileName.lastIndexOf(".")) + "-small" + srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
        srcFileName = rootpath + srcFileName;

        try {
            BufferedImage e = ImageIO.read(new File(srcFileName));
            int srcWidth = e.getWidth();
            int srcHeight = e.getHeight();
            float _scale = Float.valueOf((float)width).floatValue() / (float)srcWidth;
            srcHeight = (int)(Float.valueOf((float)srcHeight).floatValue() * _scale);
            Image image = e.getScaledInstance(width, srcHeight, 4);
            CropImageFilter cropFilter = new CropImageFilter(0, 0, width, height);
            Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
            BufferedImage outputImage = new BufferedImage(width, height, 1);
            Graphics g = outputImage.getGraphics();
            g.drawImage(img, 0, 0, width, height, (ImageObserver)null);
            g.dispose();
            ImageIO.write(outputImage, getSuffix(rootpath + tagFileName), new File(rootpath + tagFileName));
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        return tagFileName;
    }

    public static void createPath(String filePath) {
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        if(!myFilePath.exists()) {
            myFilePath.mkdirs();
        }

    }

    private static String getSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }
}
