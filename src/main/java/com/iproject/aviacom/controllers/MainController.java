package com.iproject.aviacom.controllers;

import com.iproject.aviacom.services.CurrentUserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.iproject.aviacom.enums.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/success")
    public void loginRedirectPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().toString().replaceAll("[\\[\\]]", "");
        CurrentUserService currentUser = new CurrentUserService(role);
        if(currentUser.isAdmin().isPersonnel().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/employee");
        }
        else if(currentUser.isBooking().isCashier().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/client");
        }
        else if(currentUser.isAirdrome().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/voyage");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

}
