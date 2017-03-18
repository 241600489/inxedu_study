//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

public class FileUploadUtils {
    public FileUploadUtils() {
    }

    public static void main(String[] args) {
        scaler("F:/image/4.png", "F:/image/ex/12.png", 70, false);
    }

    public static boolean scaler(String oldPath, String newPath, int scale, boolean flag) {
        try {
            BufferedImage e = ImageIO.read(new File(oldPath));
            int width = e.getWidth();
            int height = e.getHeight();
            float _scale = Float.valueOf((float)scale).floatValue() / 100.0F;
            if(flag) {
                width = (int)(Float.valueOf((float)width).floatValue() / _scale);
                height = (int)(Float.valueOf((float)height).floatValue() / _scale);
            } else {
                width = (int)(Float.valueOf((float)width).floatValue() * _scale);
                height = (int)(Float.valueOf((float)height).floatValue() * _scale);
            }

            Image image = e.getScaledInstance(width, height, 4);
            BufferedImage outputImage = new BufferedImage(width, height, 1);
            Graphics graphics = outputImage.getGraphics();
            graphics.drawImage(image, 0, 0, (ImageObserver)null);
            graphics.dispose();
            ImageIO.write(outputImage, getSuffix(newPath), new File(newPath));
            System.out.println("处理成功");
            return true;
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        }
    }

    public static boolean changeSize(String oldPath, String newPath, int newWidth, int newHeight) {
        try {
            BufferedImage e = ImageIO.read(new File(oldPath));
            Image image = e.getScaledInstance(newWidth, newHeight, 4);
            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, 1);
            Graphics graphics = outputImage.getGraphics();
            graphics.drawImage(image, 0, 0, (ImageObserver)null);
            graphics.dispose();
            File file = new File(newPath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            ImageIO.write(outputImage, getSuffix(newPath), new File(newPath));
            System.out.println("处理成功");
            return true;
        } catch (Exception var9) {
            var9.printStackTrace();
            return false;
        }
    }

    public static void cut(String oldPath, String newPath, int x, int y, int width, int height) {
        try {
            BufferedImage e = ImageIO.read(new File(oldPath));
            int srcHeight = e.getHeight();
            int srcWidth = e.getWidth();
            if(srcWidth > 0 && srcHeight > 0) {
                Image image = e.getScaledInstance(srcWidth, srcHeight, 4);
                CropImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(width, height, 1);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, (ImageObserver)null);
                g.dispose();
                File file = new File(newPath);
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                ImageIO.write(tag, getSuffix(newPath), file);
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    public static void pressText(String pressText, String oldPath, String newPath, String fontName, int fontStyle, Color color, float alpha) {
        try {
            File e = new File(oldPath);
            BufferedImage src = ImageIO.read(e);
            int width = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image = new BufferedImage(width, height, 1);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, (ImageObserver)null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, (int)((double)width * 0.11D)));
            g.setComposite(AlphaComposite.getInstance(10, alpha));
            g.drawString(pressText, (int)((double)width * 0.5D), (int)((double)height * 0.98D));
            g.dispose();
            ImageIO.write(image, getSuffix(newPath), new File(newPath));
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    public static void pressImage(String pressImg, String oldPath, String newPath, int x, int y, float alpha) {
        try {
            File e = new File(oldPath);
            BufferedImage src = ImageIO.read(e);
            int wideth = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image = new BufferedImage(wideth, height, 1);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, (ImageObserver)null);
            BufferedImage src_biao = ImageIO.read(new File(pressImg));
            int wideth_biao = src_biao.getWidth((ImageObserver)null);
            int height_biao = src_biao.getHeight((ImageObserver)null);
            g.setComposite(AlphaComposite.getInstance(10, alpha));
            g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao, (ImageObserver)null);
            g.dispose();
            ImageIO.write(image, getSuffix(newPath), new File(newPath));
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    public static void convert(String oldPath, String formatName, String newPath) {
        try {
            File e = new File(oldPath);
            e.canRead();
            e.canWrite();
            BufferedImage src = ImageIO.read(e);
            ImageIO.write(src, formatName, new File(newPath));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void gray(String oldPath, String newPath) {
        try {
            BufferedImage e = ImageIO.read(new File(oldPath));
            ColorSpace cs = ColorSpace.getInstance(1003);
            ColorConvertOp op = new ColorConvertOp(cs, (RenderingHints)null);
            e = op.filter(e, (BufferedImage)null);
            ImageIO.write(e, getSuffix(newPath), new File(newPath));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static String getSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }
}
