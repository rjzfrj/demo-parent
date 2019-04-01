package org.springsession.demo.xml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springsession.demo.xml.commons.ConstString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuliugen on 2017/2/25.
 */
@Controller
@RequestMapping(value = "/spring/session", produces = {ConstString.APP_JSON_UTF_8})
public class SpringSessionDemoController {

    @RequestMapping(value = "/setSession.do", method = RequestMethod.GET)
    public void setSession(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        request.getSession().setAttribute(name, value);
    }

    @RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
    public String getInterestPro(HttpServletRequest request, HttpServletResponse response, Model model) {
        String name = request.getParameter("name");
       String str= (String) request.getSession().getAttribute(name);
        System.out.println("------" + request.getSession().getAttribute(name));
        model.addAttribute("name",str);
        return "display";
    }

    @RequestMapping(value = "/removeSession.do", method = RequestMethod.GET)
    public void removeSession(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        request.getSession().removeAttribute(name);
    }
}
