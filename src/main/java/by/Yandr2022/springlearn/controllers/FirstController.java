package by.Yandr2022.springlearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    //    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.println("Hello "+ name + " " + surname);
//        return "first/hello";
//    }
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a") int a,
                             @RequestParam(value = "b") int b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        double result;
        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "division":
                result = b == 0 ? 0 : (double) a / b;
                break;
            default:
                result = -1.;
        }
        model.addAttribute("result", Math.round(result * 100) / 100.);
        return "first/calculator";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
