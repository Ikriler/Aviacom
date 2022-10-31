package com.iproject.aviacom.handlers;

import com.iproject.aviacom.services.AuthService;
import com.iproject.aviacom.services.CurrentUserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public class RequestHandler extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            String role = AuthService.getRole();
            modelAndView.getModelMap().addAttribute("user", new CurrentUserService(role));
        }
    }
}
