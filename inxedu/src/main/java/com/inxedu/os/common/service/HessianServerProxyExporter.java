//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;

public class HessianServerProxyExporter extends HessianServiceExporter {
    private static Logger logger = LoggerFactory.getLogger(HessianServerProxyExporter.class);
    public String hessianAuth = "hessianAuth";

    public HessianServerProxyExporter() {
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("++++ hessian request clientIp:" + request.getRemoteAddr() + "++++requestData:" + request.getRequestURL());
        String auth = request.getHeader("hessianAuth");
        if(auth != null && auth.equalsIgnoreCase(this.hessianAuth)) {
            super.handleRequest(request, response);
        } else {
            logger.info("+++++hessianAuth->fail :" + request.getRemoteAddr() + "," + request.getRequestURL());
        }
    }
}
