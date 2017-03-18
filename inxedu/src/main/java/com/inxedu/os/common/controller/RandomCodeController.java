//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RandomCodeController {
    public RandomCodeController() {
    }

    @RequestMapping({"/ran/random"})
    public void genericRandomCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/png");
        HttpSession session = request.getSession();
        byte width = 85;
        byte height = 28;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        Random random = new Random();
        g.setColor(new Color(231, 231, 231));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Microsoft YaHei", 2, 24));
        String sRand = "";

        for(int var12 = 0; var12 < 4; ++var12) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(121, 143, 96));
            g.drawString(rand, 13 * var12 + 16, 23);
        }

        session.setAttribute("COMMON_RAND_CODE", sRand);
        g.dispose();
        ServletOutputStream var121 = response.getOutputStream();
        ImageIO.write(image, "png", var121);
        var121.close();
    }
}
