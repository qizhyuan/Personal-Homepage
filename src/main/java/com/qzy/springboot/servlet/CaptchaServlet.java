package com.qzy.springboot.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CaptchaServlet extends HttpServlet {
    private String letters = "234578ABCDEFGHJKMNPQRSTWXYZ";
    private Random myRand = new Random();
    private static final int width = 80;
    private static final int height = 36;
    private static final double pi = 3.1415926;

    private char getRandChar() {
        int index = myRand.nextInt(letters.length());
        return letters.charAt(index);
    }
    public Color getRandColor() {
        int r = myRand.nextInt(256);
        int g = myRand.nextInt(256);
        int b = myRand.nextInt(256);
        return new Color(r, g, b);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //Turn off captcha caching
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "No-cache");
        resp.setDateHeader("Expires", 0);

        //Set content type of response
        resp.setContentType("image/jpeg");
        //Generate captcha string
        String captchaValue = generateCaptcha();
        //Generate captcha image
        BufferedImage bufferedImage = getCaptchaImage(captchaValue);

        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("captcha", captchaValue);
        ImageIO.write(bufferedImage, "JPEG", resp.getOutputStream());
    }

    //Generate captcha string
    private String generateCaptcha() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            stringBuilder.append(String.valueOf(getRandChar()));
        }
        return stringBuilder.toString();
    }

    //Generate captcha image
    private BufferedImage getCaptchaImage(String captchaValue) {
        //Create image
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Set font
        Font font = new Font("Arial", Font.BOLD, 18);
        graphics.setFont(font);

        //Draw image background
        graphics.setColor(new Color(245, 245, 245));
        graphics.fillRect(0, 0, width, height);

        //Draw random lines
        for (int i = 0; i < 200; ++i) {
            //Set line color
            if (i % 40 == 0) graphics.setColor(getRandColor());
            int x = myRand.nextInt(width - 1);
            int y = myRand.nextInt(height - 1);
            int deltaX = myRand.nextInt(11) + 1;
            int deltaY = myRand.nextInt(7) + 1;
            BasicStroke basicStroke = new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
            Line2D line2D = new Line2D.Double(x, y, x + deltaX, y + deltaY);
            graphics2D.setStroke(basicStroke);
            graphics2D.draw(line2D);
        }

        //Draw letters
        for (int i = 0; i < 4; ++i) {
            //Set captcha color
            graphics.setColor(new Color(36, 33, 28));
            //Rotate letter
            int ang = myRand.nextInt(60) - 30;
            AffineTransform transform = new AffineTransform();
            transform.rotate(ang * pi / 180, 20 * i + 6, 23);
            //Scale letter
            float scaleX = 0.2f * myRand.nextFloat() + 0.9f;
            float scaleY = 0.2f * myRand.nextFloat() + 0.9f;
            transform.scale(scaleX, scaleY);
            ((Graphics2D) graphics).setTransform(transform);
            graphics.drawString(captchaValue.substring(i, i + 1), 20 * i + 6, 23);
        }
        return bufferedImage;
    }
}
