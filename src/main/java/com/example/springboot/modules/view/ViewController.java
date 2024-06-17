package com.example.springboot.modules.view;

import com.example.springboot.modules.report.services.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping
    public String home(Model model) {
        return "index";
    }

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        return "register";
    }

    @GetMapping("upload-data")
    public String uploadData(Model model) {
        return "upload-data";
    }
}
