//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.util;

import com.inxedu.os.common.util.FileUtilException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtils {
    public FileUtils() {
    }

    public static void createPath(String filePath) {
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        if(!myFilePath.exists()) {
            myFilePath.mkdirs();
        }

    }

    public static void createFile(String filePath) throws FileUtilException {
        try {
            filePath = filePath.toString();
            File e = new File(filePath);
            if(!e.exists()) {
                e.createNewFile();
            }

        } catch (IOException var2) {
            var2.printStackTrace();
            throw new FileUtilException("创建文件错误!");
        }
    }

    public static void deleteFile(String filePath) throws FileUtilException {
        try {
            filePath = filePath.toString();
            File e = new File(filePath);
            if(e.exists()) {
                e.delete();
            }

        } catch (Exception var2) {
            var2.printStackTrace();
            throw new FileUtilException("删除文件错误!");
        }
    }

    public static void copyFile(String sourceFilePath, String distFilePath) throws FileUtilException {
        try {
            int e = 0;
            boolean byteread = false;
            FileInputStream inStream = new FileInputStream(sourceFilePath);
            FileOutputStream fs = new FileOutputStream(distFilePath);
            byte[] buffer = new byte[1444];

            int byteread1;
            while((byteread1 = inStream.read(buffer)) != -1) {
                e += byteread1;
                fs.write(buffer, 0, byteread1);
            }

            inStream.close();
            fs.close();
        } catch (Exception var7) {
            var7.printStackTrace();
            throw new FileUtilException("文件拷贝错误!");
        }
    }

    public static void copyFile(File file, String distFilePath, String fileName) throws FileUtilException {
        try {
            File e = new File(distFilePath);
            if(!e.exists()) {
                e.mkdirs();
            }

            int bytesum = 0;
            boolean byteread = false;
            FileInputStream inStream = new FileInputStream(file);
            FileOutputStream fs = new FileOutputStream(distFilePath + fileName);
            byte[] buffer = new byte[1444];

            int byteread1;
            while((byteread1 = inStream.read(buffer)) != -1) {
                bytesum += byteread1;
                fs.write(buffer, 0, byteread1);
            }

            inStream.close();
            fs.close();
        } catch (Exception var9) {
            var9.printStackTrace();
            throw new FileUtilException("文件拷贝错误!");
        }
    }

    public static void copyFilePath(String sourceFilePath, String distFilePath) throws FileUtilException {
        try {
            (new File(distFilePath)).mkdirs();
            File[] e = (new File(sourceFilePath)).listFiles();

            for(int i = 0; i < e.length; ++i) {
                if(e[i].isFile()) {
                    e[i].toString();
                    FileInputStream input = new FileInputStream(e[i]);
                    FileOutputStream output = new FileOutputStream(distFilePath + "/" + e[i].getName().toString());
                    byte[] b = new byte[5120];

                    int len;
                    while((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }

                    output.flush();
                    output.close();
                    input.close();
                }
            }

        } catch (Exception var8) {
            var8.printStackTrace();
            throw new FileUtilException("文件夹拷贝错误!");
        }
    }

    public static InputStream fileToStream(String filePath) throws FileUtilException {
        try {
            File e = new File(filePath);
            return e.exists()?new FileInputStream(e):null;
        } catch (Exception var2) {
            var2.printStackTrace();
            throw new FileUtilException("文件转化输出流错误!");
        }
    }

    public static byte[] fileToByte(File file) throws FileUtilException {
        FileInputStream is = null;

        byte[] var3;
        try {
            byte[] e = null;
            if(file.exists()) {
                is = new FileInputStream(file);
                e = new byte[is.available()];
                is.read(e);
            }

            var3 = e;
        } catch (Exception var12) {
            var12.printStackTrace();
            throw new FileUtilException("文件转化字节数组错误!");
        } finally {
            try {
                is.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return var3;
    }

    public static byte[] fileToByte(String filePath) throws FileUtilException {
        FileInputStream is = null;

        byte[] var4;
        try {
            File e = new File(filePath);
            byte[] dist = null;
            if(e.exists()) {
                is = new FileInputStream(e);
                dist = new byte[is.available()];
                is.read(dist);
            }

            var4 = dist;
        } catch (Exception var13) {
            var13.printStackTrace();
            throw new FileUtilException("文件转化字节数组错误!");
        } finally {
            try {
                is.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return var4;
    }

    public static void writeFile(String filePath, String fileName, String[] args) throws IOException {
        FileWriter fw = new FileWriter(filePath + fileName);
        PrintWriter out = new PrintWriter(fw);

        for(int i = 0; i < args.length; ++i) {
            out.write(args[i]);
            out.println();
            out.flush();
        }

        fw.close();
        out.close();
    }

    public static void writeFile(String filePath, String fileName, String args) throws IOException {
        FileWriter fw = new FileWriter(filePath + fileName);
        fw.write(args);
        fw.close();
    }

    public static void writeFile(String filePath, String args) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(args);
        fw.close();
    }

    public static void writeFile(String filePath, String args, boolean isUTF8) throws IOException {
        if(isUTF8) {
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
            fw.write(args);
            fw.flush();
            fw.close();
        } else {
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write(args);
            fw1.close();
        }

    }

    public static void writeFile(String filePath, String fileName, String args, boolean isUTF8) throws IOException {
        File f = new File(filePath);
        if(!f.exists()) {
            f.mkdirs();
        }

        if(isUTF8) {
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filePath + fileName), "UTF-8");
            fw.write(args);
            fw.flush();
            fw.close();
        } else {
            FileWriter fw1 = new FileWriter(filePath + fileName);
            fw1.write(args);
            fw1.close();
        }

    }

    public static boolean createAndDeleteFile(String filePath, String fileName) throws IOException {
        boolean result = false;
        File file = new File(filePath, fileName);
        if(file.exists()) {
            file.delete();
            result = true;
        } else {
            file.createNewFile();
            result = true;
        }

        return result;
    }

    public static boolean createAndDeleteFolder(String folderName, String filePath) {
        boolean result = false;

        try {
            File ex = new File(filePath + folderName);
            if(ex.exists()) {
                ex.delete();
                System.out.println("目录已经存在，已删除!");
                result = true;
            } else {
                ex.mkdir();
                System.out.println("目录不存在，已经建立!");
                result = true;
            }
        } catch (Exception var4) {
            result = false;
            System.out.println("CreateAndDeleteFolder is error:" + var4);
        }

        return result;
    }

    public static void readFolderByFile(String filePath) {
        File file = new File(filePath);
        File[] tempFile = file.listFiles();

        for(int i = 0; i < tempFile.length; ++i) {
            if(tempFile[i].isFile()) {
                System.out.println("File : " + tempFile[i].getName());
            }

            if(tempFile[i].isDirectory()) {
                System.out.println("Directory : " + tempFile[i].getName());
            }
        }

    }

    public static boolean fileIsNull(String filePath, String fileName) throws IOException {
        boolean result = false;
        FileReader fr = new FileReader(filePath + fileName);
        if(fr.read() == -1) {
            result = true;
            System.out.println(fileName + " 文件中没有数据!");
        } else {
            System.out.println(fileName + " 文件中有数据!");
        }

        fr.close();
        return result;
    }

    public static void readAllFile(String filePath, String fileName) throws IOException {
        FileReader fr = new FileReader(filePath + fileName);
        int count = fr.read();

        while(count != -1) {
            count = fr.read();
            if(count == 13) {
                fr.skip(1L);
            }
        }

        fr.close();
    }

    public static void readLineFile(String filePath, String fileName) throws IOException {
        FileReader fr = new FileReader(filePath + fileName);
        BufferedReader br = new BufferedReader(fr);

        for(String line = br.readLine(); line != null; line = br.readLine()) {
            ;
        }

        br.close();
        fr.close();
    }

    public static String readLineFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        for(String line = br.readLine(); line != null; line = br.readLine()) {
            sb.append(line);
        }

        br.close();
        fr.close();
        return sb.toString();
    }

    public static ByteArrayInputStream file2ByteArrayInputStream(String fileName) throws Exception {
        try {
            File e = new File(fileName);
            return file2ByteArrayInputStream(e);
        } catch (Exception var2) {
            throw new Exception("将文件转换为流的过程中出现错误!");
        }
    }

    public static ByteArrayInputStream file2ByteArrayInputStream(File file) throws Exception {
        try {
            FileInputStream e = new FileInputStream(file);
            byte[] b = new byte[e.available()];
            e.read(b);
            e.close();
            return new ByteArrayInputStream(b);
        } catch (Exception var3) {
            throw new Exception("将文件转换为流的过程中出现错误!");
        }
    }

    public String readFromURL(String strURL) {
        try {
            URL e = new URL(strURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(e.openStream()));

            String str;
            String rtnStr;
            for(rtnStr = ""; (str = in.readLine()) != null; rtnStr = rtnStr + new String(str.getBytes(), "GB2312")) {
                ;
            }

            in.close();
            return rtnStr;
        } catch (MalformedURLException var6) {
            var6.printStackTrace();
            return null;
        } catch (IOException var7) {
            var7.printStackTrace();
            return null;
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public String readFromIS(InputStream is) throws Exception {
        Object length;
        try {
            String e = "";
            int length1 = is.available();

            for(byte[] buf = new byte[length1]; is.read(buf, 0, length1) != -1; e = e + new String(buf, 0, length1, "GB2312")) {
                ;
            }

            String var5 = e;
            return var5;
        } catch (IOException var9) {
            var9.printStackTrace();
            length = null;
        } finally {
            is.close();
        }

        return (String)length;
    }

    public static void clearFile(String staticPath) {
        File file = new File(staticPath);
        deleteFile(file);
        file.mkdirs();
    }

    public static void deleteFile(File file) {
        if(file.exists()) {
            if(file.isFile()) {
                file.delete();
            } else if(file.isDirectory()) {
                File[] files = file.listFiles();

                for(int i = 0; i < files.length; ++i) {
                    deleteFile(files[i]);
                }
            }

            file.delete();
        } else {
            System.out.println("所删除的文件不存在！\n");
        }

    }
}
