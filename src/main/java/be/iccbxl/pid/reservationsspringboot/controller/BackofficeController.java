package be.iccbxl.pid.reservationsspringboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class BackofficeController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/backoffice")
    public String dashboard(Model model) {
        model.addAttribute("title", "Back-office");
        return "backoffice/index";
    }
}


