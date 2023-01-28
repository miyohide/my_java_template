package com.github.miyohide.my_java_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
  @Autowired private MemberRepository memberRepository;

  @GetMapping("/members")
  public String index(Model model) {
    model.addAttribute("members", memberRepository.findAll());
    return "index";
  }
}
