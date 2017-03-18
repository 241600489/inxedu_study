//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.controller;

import com.inxedu.os.common.controller.BaseController;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DialogController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DialogController.class);
    private static final String getDialogHtml = "/common/dialog";

    public DialogController() {
    }

    @RequestMapping({"/common/dialog"})
    public String getDialog(HttpServletRequest request, Model model) {
        try {
            HashMap e = new HashMap();
            e.put("title", request.getParameter("title"));
            e.put("context", request.getParameter("context"));
            e.put("height", request.getParameter("height") == null?Integer.valueOf(157):request.getParameter("height"));
            model.addAttribute("dialog", e);
            return "/common/dialog";
        } catch (Exception var4) {
            logger.error("getDialog", var4);
            return this.setExceptionRequest(request, var4);
        }
    }
}
