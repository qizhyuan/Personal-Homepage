package com.qzy.springboot.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzy.springboot.entities.Email;
import com.qzy.springboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class EmailServlet extends HttpServlet {
    @Autowired
    private EmailService emailService;

    //Inject EmailService (Otherwise java.lang.NullPointerException Error)
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String inputCaptcha = req.getParameter("captcha");

        HttpSession httpSession = req.getSession();
        String trueCaptcha = (String) httpSession.getAttribute("captcha");
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        if (inputCaptcha.equalsIgnoreCase(trueCaptcha)) {
            Email email = new Email(req.getParameter("name"), req.getParameter("email"), req.getParameter("text"));
            emailService.sendMessage(email);
            map.put("Result","Succeed");
        }else {
            map.put("Result","Fail");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(objectMapper.writeValueAsString(map));
        printWriter.flush();
        printWriter.close();
    }
}
