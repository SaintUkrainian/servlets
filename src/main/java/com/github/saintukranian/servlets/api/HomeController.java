package com.github.saintukranian.servlets.api;

import com.github.saintukranian.servlets.entities.Function;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("function", new Function());
    return "home";
  }

  @PostMapping("/calculate")
  public String calculate(@ModelAttribute Function function, Model model) {
    Double result = "Tan".equals(function.getType()) ?
        Math.tan(function.getValue()) :
        Math.sin(function.getValue());
    model.addAttribute("functionResult", result);
    model.addAttribute("functionType", function.getType());
    model.addAttribute("functionValue", function.getValue());
    return "result";
  }
}
