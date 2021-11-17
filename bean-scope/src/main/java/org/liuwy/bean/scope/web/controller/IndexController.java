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
    private User user;  //CGLIB代理后对象(不变)
    @GetMapping("index.html")
    public String index(Model model) {
        // JSP EL变量搜索路径 page -> request -> session -> application(ServletContext)
        // userObject -> 渲染上下文
        // user 对象存在ServletContext，上下文名称：user
        model.addAttribute("userObject", user);
        return "index";
    }
}
