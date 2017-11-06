package com.shop.retman.web.controller;

import com.shop.retman.domain.Category;
import com.shop.retman.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping({"/"})
public class HomeController extends com.shop.retman.web.controller.Controller {

    @Value("${server.port}")
    private String port;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String process404(Model model) {
        addBasicConfigurationToModel(model);
        return "404";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String processError(Model model) {
        addBasicConfigurationToModel(model);
        return "error";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String processHomePageGet(Model model) {
        return processHomePage(model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processHomePagePost(Model model) {
        return processHomePage(model);
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String login() {
        return "loginPage";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String denied(Model model) {
        addBasicConfigurationToModel(model);
        return "denied";
    }

    @RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
    public String processFavicon() {
        return "forward:/" + applicationName + "/resources/image/favicon.ico";
    }

    @RequestMapping(value = "/logoutUrl", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    private String processHomePage(Model model) {
        List<Category> categoryList = categoryService.findAll();

        addBasicConfigurationToModel(model);
        model.addAttribute("port", port);
        model.addAttribute("categoryList", categoryList);
        provideModelWithTopDownloadedApps(model);
        return "home";
    }

}
