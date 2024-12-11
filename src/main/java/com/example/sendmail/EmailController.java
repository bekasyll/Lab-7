package com.example.sendmail;

import com.example.sendmail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String showForm() {
        return "email-form";
    }

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("message") String message,
            Model model
    ) {
        try {
            emailService.sendEmail(email, "Welcome letter", name, message);
            model.addAttribute("success", "The email was successfully sent!");
        } catch (Exception e) {
            logger.error("Error sending an email", e);
            model.addAttribute("error", "Error sending an email: " + e.getMessage());
        }
        return "email-form";
    }
}


