//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.controller;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FileUploadUtils;
import com.inxedu.os.common.util.PDFUtils;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping({"/image"})
public class ImageUploadController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    private List<String> fileTypeList;

    public ImageUploadController() {
    }

    private String getProjectRootDirPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    @RequestMapping(
        value = {"/gok4"},
        method = {RequestMethod.POST}
    )
    public String gok4(HttpServletRequest request, HttpServletResponse response, @RequestParam(
    value = "uploadfile",
    required = true
) MultipartFile uploadfile, @RequestParam(
    value = "param",
    required = false
) String param, @RequestParam(
    value = "fileType",
    required = true
) String fileType, @RequestParam(
    value = "pressText",
    required = false
) String pressText) {
        try {
            long e = 4096000L;
            System.out.println(uploadfile.getSize());
            if(uploadfile.getSize() > e) {
                return this.responseErrorData(response, 1, "上传的图片大小不能超过4M。");
            } else {
                String[] type = fileType.split(",");
                this.setFileTypeList(type);
                String ext = FileUploadUtils.getSuffix(uploadfile.getOriginalFilename());
                if(!fileType.contains(ext)) {
                    return this.responseErrorData(response, 1, "文件格式错误，上传失败。");
                } else {
                    String filePath = this.getPath(request, ext, param);
                    File file = new File(this.getProjectRootDirPath(request) + filePath);
                    if(!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }

                    uploadfile.transferTo(file);
                    return this.responseData(filePath, 0, "上传成功", response);
                }
            }
        } catch (Exception var13) {
            logger.error("gok4()--error", var13);
            return this.responseErrorData(response, 2, "系统繁忙，上传失败");
        }
    }

    @RequestMapping(
        value = {"/keupload"},
        method = {RequestMethod.POST}
    )
    public String kindEditorUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam(
    value = "param",
    required = false
) String param, @RequestParam(
    value = "fileType",
    required = true
) String fileType, @RequestParam(
    value = "pressText",
    required = false
) String pressText) {
        try {
            MultipartHttpServletRequest e = (MultipartHttpServletRequest)request;
            MultipartFile imgFile = e.getFile("imgFile");
            if(imgFile == null) {
                return this.responseData("", 1, "请选择上传的文件，上传失败", response);
            } else {
                long maxSize = 4096000L;
                System.out.println(imgFile.getSize());
                if(imgFile.getSize() > maxSize) {
                    return this.responseErrorData(response, 1, "上传的图片大小不能超过4M。");
                } else {
                    String[] type = fileType.split(",");
                    this.setFileTypeList(type);
                    String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
                    if(!fileType.contains(ext)) {
                        return this.responseErrorData(response, 1, "文件格式错误，上传失败。");
                    } else {
                        String filePath = this.getPath(request, ext, param);
                        File file = new File(this.getProjectRootDirPath(request) + filePath);
                        if(!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }

                        imgFile.transferTo(file);
                        return this.responseData(filePath, 0, "上传成功", response);
                    }
                }
            }
        } catch (Exception var14) {
            logger.error("kindEditorUpload()--error", var14);
            return this.responseErrorData(response, 2, "系统繁忙，上传失败。");
        }
    }

    @RequestMapping(
        value = {"/shrinkDiagram"},
        method = {RequestMethod.POST}
    )
    @ResponseBody
    public String kindEditorUploadShrinkDiagram(HttpServletRequest request, HttpServletResponse response, @RequestParam(
    value = "param",
    required = false
) String param, @RequestParam(
    value = "fileType",
    required = true
) String fileType, @RequestParam(
    value = "pressText",
    required = false
) String pressText) {
        try {
            MultipartHttpServletRequest e = (MultipartHttpServletRequest)request;
            MultipartFile imgFile = e.getFile("imgFile");
            if(imgFile == null) {
                return this.responseData("", 1, "请选择上传的文件，上传失败", response);
            } else {
                long maxSize = 1024000L;
                System.out.println(imgFile.getSize());
                if(imgFile.getSize() > maxSize) {
                    return this.responseErrorData(response, 1, "上传文件大小超过限制。");
                } else {
                    String[] type = fileType.split(",");
                    this.setFileTypeList(type);
                    String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
                    if(!fileType.contains(ext)) {
                        return this.responseErrorData(response, 1, "文件格式错误，上传失败。");
                    } else {
                        String filePath = this.getPath(request, ext, param);
                        File file = new File(this.getProjectRootDirPath(request) + filePath);
                        if(!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }

                        imgFile.transferTo(file);
                        String width = request.getParameter("width");
                        String height = request.getParameter("height");
                        String smallUrl = this.zoomImage(request, filePath, Integer.valueOf(width).intValue(), Integer.valueOf(height).intValue());
                        HashMap map = new HashMap();
                        map.put("url", filePath);
                        map.put("error", Integer.valueOf(0));
                        map.put("message", "上传成功");
                        map.put("smallurl", smallUrl);
                        String url = gson.toJson(map);
                        return url;
                    }
                }
            }
        } catch (Exception var19) {
            logger.error("kindEditorUpload()--error", var19);
            return this.responseErrorData(response, 2, "系统繁忙，上传失败。");
        }
    }

    @RequestMapping({"/saveface"})
    @ResponseBody
    public String saveface(HttpServletRequest request, HttpServletResponse response) {
        try {
            String e = request.getParameter("photoPath");
            int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
            int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
            FileUploadUtils.changeSize(this.getProjectRootDirPath(request) + e, this.getProjectRootDirPath(request) + e, imageWidth, imageHeight);
            int cutTop = Integer.parseInt(request.getParameter("txt_top"));
            int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
            int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
            int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
            String ext = FileUploadUtils.getSuffix(e);
            String newPath = this.getPath(request, ext, "customer");
            FileUploadUtils.cut(this.getProjectRootDirPath(request) + e, this.getProjectRootDirPath(request) + newPath, cutLeft, cutTop, dropWidth, dropHeight);
            File file = new File(this.getProjectRootDirPath(request) + e);
            if(file.exists()) {
                file.delete();
            }

            long maxSize = 4096000L;
            System.out.println(file.length());
            if(file.length() > maxSize) {
                return this.responseErrorData(response, 1, "上传的图片大小不能超过4M。");
            } else {
                HashMap map = new HashMap();
                map.put("src", newPath);
                map.put("status", "1");
                return gson.toJson(map);
            }
        } catch (Exception var16) {
            logger.error("saveface()---error", var16);
            return null;
        }
    }

    @RequestMapping({"/pdfUploadAndImg"})
    @ResponseBody
    public String pdfUploadAndImg(HttpServletRequest request, HttpServletResponse response, @RequestParam(
    value = "param",
    required = false
) String param) {
        HashMap map = new HashMap();

        try {
            String e = request.getParameter("width");
            String height = request.getParameter("height");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            MultipartFile imgFile = multipartRequest.getFile("fileupload");
            String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
            String filePath = this.getPath(request, ext, param);
            File file = new File(this.getProjectRootDirPath(request) + filePath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            imgFile.transferTo(file);
            Map pdfToPngmap = PDFUtils.pdfToPng(this.getProjectRootDirPath(request), this.getProjectRootDirPath(request) + filePath, e, height);
            map.put("pdfUrl", filePath);
            map.put("pngUrlStrs", pdfToPngmap.get("pngUrlStrs"));
            map.put("pngUrlStrsTB", pdfToPngmap.get("pngUrlStrsTB"));
            return gson.toJson(map);
        } catch (Exception var13) {
            logger.error("pdfUploadAndImg error", var13);
            return null;
        }
    }

    @RequestMapping({"/uploadfile"})
    @ResponseBody
    public String uploadfile(HttpServletRequest request, @RequestParam(
    value = "param",
    required = false
) String param) {
        try {
            MultipartHttpServletRequest e = (MultipartHttpServletRequest)request;
            MultipartFile imgFile = e.getFile("fileupload");
            String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
            String filePath = this.getPath(request, ext, param);
            File file = new File(this.getProjectRootDirPath(request) + filePath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            imgFile.transferTo(file);
            logger.info("++++upload img return:" + filePath);
            return filePath;
        } catch (Exception var8) {
            logger.error("uploadfile error", var8);
            return null;
        }
    }

    @RequestMapping(
        value = {"/deletefile"},
        method = {RequestMethod.POST}
    )
    @ResponseBody
    public Map<String, Object> deleteFile(HttpServletRequest request, @RequestParam(
    value = "filePath",
    required = true
) String filePath) {
        new HashMap();

        Map json;
        try {
            if(filePath != null && filePath.trim().length() > 0 && filePath.startsWith("/images/upload/")) {
                File e = new File(this.getProjectRootDirPath(request) + filePath);
                if(e.exists()) {
                    e.delete();
                    json = this.setJson(true, "文件删除成功", (Object)null);
                } else {
                    json = this.setJson(false, "文件不存在，删除失败", (Object)null);
                }
            } else {
                json = this.setJson(false, "删除失败", (Object)null);
            }
        } catch (Exception var5) {
            json = this.setJson(false, "系统繁忙，文件删除失败", (Object)null);
            logger.error("deleteFile()--error", var5);
        }

        return json;
    }

    private String getPath(HttpServletRequest request, String ext, String param) {
        String filePath = "/images/upload/";
        if(param != null && param.trim().length() > 0) {
            filePath = filePath + param;
        } else {
            filePath = filePath + CommonConstants.projectName;
        }

        filePath = filePath + "/" + DateUtils.toString(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + "." + ext;
        return filePath;
    }

    public String responseData(String path, int error, String message, HttpServletResponse response) throws IOException {
        HashMap map = new HashMap();
        map.put("url", path);
        map.put("error", Integer.valueOf(error));
        map.put("message", message);
        response.getWriter().write(gson.toJson(map));
        return null;
    }

    public String responseErrorData(HttpServletResponse response, int error, String message) {
        try {
            HashMap e1 = new HashMap();
            e1.put("error", Integer.valueOf(error));
            e1.put("message", message);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(gson.toJson(e1));
            response.getWriter().flush();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public void setFileTypeList(String[] type) {
        this.fileTypeList = new ArrayList();
        String[] var2 = type;
        int var3 = type.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String _t = var2[var4];
            this.fileTypeList.add(_t);
        }

    }

    public String zoomImage(HttpServletRequest request, String srcFileName, int width, int height) {
        String tagFileName = srcFileName.substring(0, srcFileName.lastIndexOf(".")) + "-small" + srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
        srcFileName = this.getProjectRootDirPath(request) + srcFileName;

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
            ImageIO.write(outputImage, FileUploadUtils.getSuffix(this.getProjectRootDirPath(request) + tagFileName), new File(this.getProjectRootDirPath(request) + tagFileName));
        } catch (IOException var15) {
            var15.printStackTrace();
        }

        return tagFileName;
    }
}
