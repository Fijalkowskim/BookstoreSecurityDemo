package com.example.bookstore.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("code", statusCode);

            if (statusCode == 403) return "error/403";
            if (statusCode == 404) return "error/404";
            if (statusCode == 500) return "error/500";
        }

        return "error/default";
    }
}
