package org.liuwy.bean.scope.web.controller;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ImOkkkk
 * @date 2021/11/1 22:06
 * @since 1.0
 */

@Controller
public class IndexController {
    @Autowired
    private User user;

    @GetMapping("index.html")
    public String index(Model model) {
        model.addAttribute("user", user);
        return "index";
    }
}
