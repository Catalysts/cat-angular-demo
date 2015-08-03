package cc.catalysts.angular.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
@Controller
@RequestMapping("")
public class MainController {

    @RequestMapping("")
    public String main() {
        return "main";
    }
}
